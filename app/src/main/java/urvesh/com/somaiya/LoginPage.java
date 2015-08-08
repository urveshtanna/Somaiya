package urvesh.com.somaiya;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.listeners.ActionClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class LoginPage extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final int RC_SIGN_IN = 0;
    //Google Client to connect with Google
    private GoogleApiClient googleApiClient;

    private boolean mIntentInProgress;
    private boolean signInUser;
    private ConnectionResult mConnectionResult;
    private SignInButton signInButton;
    private ImageView profileImage;
    private TextView username, email;
    private LinearLayout profileDetails, siginDetails;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        signInButton = (SignInButton) findViewById(R.id.signin);
        signInButton.setOnClickListener(this);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        profileImage = (ImageView) findViewById(R.id.profileImage);
        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);

        profileDetails = (LinearLayout) findViewById(R.id.profileContent);
        siginDetails = (LinearLayout) findViewById(R.id.signInFrame);

        googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Plus.API, Plus.PlusOptions.builder().build()).addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }

    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    private void resolveSiginInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                googleApiClient.connect();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        signInUser = false;
        getProfileInformation();
    }

    private void updateProfile(boolean isSignIn) {
        if (isSignIn) {
            siginDetails.setVisibility(View.GONE);
            profileDetails.setVisibility(View.VISIBLE);
            Snackbar.with(this).text("Connected").textColor(Color.WHITE).color(Color.rgb(66, 66, 66)).show(this);
        } else {
            siginDetails.setVisibility(View.VISIBLE);
            profileDetails.setVisibility(View.GONE);
        }
    }

    private void getProfileInformation() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(googleApiClient) != null) {
                Person person = Plus.PeopleApi.getCurrentPerson(googleApiClient);
                String personName = person.getDisplayName();
                String personPhotoUrl = person.getImage().getUrl();
                String emailId = Plus.AccountApi.getAccountName(googleApiClient);

                username.setText(personName);
                email.setText(emailId);
                new LoadProfileImage(profileImage).execute(personPhotoUrl);
                //Update frame Layout on new Google account
                updateProfile(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
        updateProfile(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin:
                signIn();
                break;
        }
    }

    public void signIn() {
        if (haveNetworkConnection() == true) {
            googlePlusLogin();
        }
        else{
            Snackbar.with(this).text("No Internet Connection").textColor(Color.WHITE).color(Color.rgb(66, 66, 66)).actionLabel("Retry").actionColor(Color.rgb(255, 193, 7)).actionListener(new ActionClickListener() {
                @Override
                public void onActionClicked(Snackbar snackbar) {
                    signIn();
                }
            }).show(this);
        }
    }

    public void logout() {
        googlePlusLogout();
    }

    private void googlePlusLogin() {
        if (!googleApiClient.isConnecting()) {
            signInUser = true;
            resolveSiginInError();
        }
    }

    private void googlePlusLogout() {
        if (googleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(googleApiClient);
            googleApiClient.disconnect();
            googleApiClient.connect();
            updateProfile(false);
            Snackbar.with(this).text("Disconnected").textColor(Color.WHITE).color(Color.rgb(66, 66, 66)).show(this);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (!connectionResult.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 0).show();
            return;
        }
        if (!mIntentInProgress) {
            //store connectionResult
            mConnectionResult = connectionResult;
            if (signInUser) {
                resolveSiginInError();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode == RESULT_OK) {
                    signInUser = false;

                }
                mIntentInProgress = false;
                if (!googleApiClient.isConnecting()) {
                    googleApiClient.connect();
                }
                break;
        }
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    //Downloading profile Picture
    private class LoadProfileImage extends AsyncTask {
        ImageView imageView;

        public LoadProfileImage(ImageView image) {
            this.imageView = image;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            String url = (String) params[0];
            Bitmap icon = null;
            try {
                InputStream inputStream = new java.net.URL(url).openStream();
                icon = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return icon;
        }

        @Override
        protected void onPostExecute(Object o) {
            imageView.setImageBitmap((Bitmap) o);
        }
    }
}
