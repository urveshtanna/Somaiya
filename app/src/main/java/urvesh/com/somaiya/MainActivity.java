package urvesh.com.somaiya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private static final int RESULT_SETTING = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        showUserSettings();
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout),toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startNew(int position){
        switch (position){
            case 0:
                Toast.makeText(getApplicationContext(),"On Click "+position,Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(),"On Click "+position,Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(),"On Click "+position,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Course.class));
                break;
            case 3:
                Toast.makeText(getApplicationContext(),"On Click "+position,Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(getApplicationContext(),"On Click "+position,Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(getApplicationContext(),"On Click "+position,Toast.LENGTH_SHORT).show();
                break;
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_settings) {
            Toast.makeText(getApplicationContext(),"On Progress!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,UserSettingActivity.class);
            startActivityForResult(intent,RESULT_SETTING);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SETTING:
                showUserSettings();
                break;
        }
    }
    private void showUserSettings(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        StringBuilder stringBuilder = new StringBuilder();
        //
        // stringBuilder.append("\nUserName: "+sharedPreferences.getString("profUsername", null));
        stringBuilder.append("\nSend Report: "+sharedPreferences.getBoolean("prefSendReport", false));
        stringBuilder.append("\nSync Frequency: "+sharedPreferences.getString("prefSyncFrequency", null));
        TextView settingText = (TextView) findViewById(R.id.userSettings);
        settingText.setText(stringBuilder.toString());
    }
}
