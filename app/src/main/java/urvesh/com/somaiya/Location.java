package urvesh.com.somaiya;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location extends AppCompatActivity {

    private FloatingActionButton locationBtn;
    private Spinner citySpinner;
    private LocationManager locationManager;
    private TextView locationDetails;
    private ImageView cityImage;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        locationBtn = (FloatingActionButton) findViewById(R.id.fab);
        locationDetails = (TextView) findViewById(R.id.locationDetails);
        cityImage = (ImageView) findViewById(R.id.cityImage);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                if (isNetworkEnabled) {
                    Criteria criteria = new Criteria();
                    criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                    locationManager.requestSingleUpdate(criteria, new LocationListener() {
                        @Override
                        public void onLocationChanged(android.location.Location location) {
                            double longitude = location.getLongitude();
                            double latitude = location.getLatitude();
                            locationDetails.setText("" + latitude+" " + longitude+" ");
                            Geocoder geocoder = new Geocoder(Location.this, Locale.getDefault());
                            try {
                                List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);
                                if (addresses.size() > 0){
                                    city = addresses.get(0).getLocality().trim();
                                    locationDetails.append(city);
                                    setCitySpinner(city);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {
                            locationDetails.setText("Available: status");
                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                            locationDetails.setText("Available: enabled");
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                            locationDetails.setText("Available: disable");
                        }
                    }, null);
                } else {
                    boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    if (isGPSEnabled) {
                        Criteria criteria = new Criteria();
                        criteria.setAccuracy(Criteria.ACCURACY_FINE);
                        locationManager.requestSingleUpdate(criteria, new LocationListener() {
                            @Override
                            public void onLocationChanged(android.location.Location location) {
                                double longitude = location.getLongitude();
                                double latitude = location.getLatitude();
                                locationDetails.setText("" + latitude+" " + longitude+" ");
                                Geocoder geocoder = new Geocoder(Location.this, Locale.getDefault());
                                try {
                                    List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);
                                    if (addresses.size() > 0){
                                        city = addresses.get(0).getLocality().trim();
                                        locationDetails.append(city);
                                        setCitySpinner(city);

                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {
                                locationDetails.setText("Available: status");
                            }

                            @Override
                            public void onProviderEnabled(String provider) {
                                locationDetails.setText("Available: enabled");
                            }

                            @Override
                            public void onProviderDisabled(String provider) {
                                locationDetails.setText("Available: disable");
                            }
                        }, null);
                    }


                }
            }
        });
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(citySpinner.getSelectedItemPosition()==0){
                    cityImage.setImageResource(R.drawable.city_delhi_1);
                }
                else if(citySpinner.getSelectedItemPosition()==1){
                    cityImage.setImageResource(R.drawable.city_mumbai_1);
                }
                else if(citySpinner.getSelectedItemPosition()==2){
                    cityImage.setImageResource(R.drawable.city_bangalore_1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
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

    public void setCitySpinner(String cityName){
        switch (cityName){
            case "Delhi":
                citySpinner.setSelection(0);
                break;
            case "Mumbai":
                citySpinner.setSelection(1);
                break;
            case "Bangalore":
                citySpinner.setSelection(2);
                break;
    }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

    }
}
