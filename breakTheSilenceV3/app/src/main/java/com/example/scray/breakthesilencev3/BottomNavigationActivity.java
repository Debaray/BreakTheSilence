package com.example.scray.breakthesilencev3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.example.scray.breakthesilencev3.Constants.ERROR_DIALOG_REQUEST;
import static com.example.scray.breakthesilencev3.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static com.example.scray.breakthesilencev3.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;

public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView navigationView;
    private ProgressDialog mRegProgressDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private boolean mLocationPermissionGranted = false;
    private String TAG = "mainAct";
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        mRegProgressDialog = new ProgressDialog(this);

//        Toolbar mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Bottom Navigation");

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isEmailVerified()) {

            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        }
        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        displayScreen(R.id.navigation_tts);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkMapServices()) {
            if (mLocationPermissionGranted) {
                getLatLong();
            } else {
                getLocationPermission();
            }
        }
    }

    private boolean checkMapServices() {
        if (isServicesOK()) {
            if (isMapsEnabled()) {
                return true;
            }
        }
        return false;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(BottomNavigationActivity.this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isMapsEnabled() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(BottomNavigationActivity.this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            getLatLong();
        } else {
            ActivityCompat.requestPermissions(BottomNavigationActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(BottomNavigationActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(BottomNavigationActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(BottomNavigationActivity.this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if (mLocationPermissionGranted) {
                    getLatLong();
                } else {
                    getLocationPermission();
                }
            }
        }

    }

    private void getLatLong() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)

            return;
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            double lat = location.getLatitude();
                            double lng = location.getLongitude();
                            String latitude = String.valueOf(lat);
                            String longitude = String.valueOf(lng);
                            //Toast.makeText(getApplicationContext(), "The lat:" + lat + "The long:" + lng + "got it", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "Lat: " + String.valueOf(lat) + " Long : " + String.valueOf(lng));
                            HashMap<String, Object> userLoc = new HashMap<>();
                            userLoc.put("Latitude",latitude);
                            userLoc.put("Longitude",longitude);
                            mUserRef.updateChildren(userLoc).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        //Toast.makeText(BottomNavigationActivity.this, "Location Successfully added", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(BottomNavigationActivity.this, "Location Not Available", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            getAddress(getApplicationContext(),lat,lng);

                        } else {
                            Toast.makeText(getApplicationContext(), "Location not available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null && !currentUser.isEmailVerified())
        {
            Toast.makeText(BottomNavigationActivity.this,"Please Check your mail and verify your mail id.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(BottomNavigationActivity.this,VerificationActivity.class);
            startActivity(intent);
            finish();
        }
        if (currentUser == null) {
            sendToStart();
        } else {

            mUserRef.child("online").setValue(100000);
            mUserRef.child("status").setValue("true");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            mUserRef.child("online").setValue(ServerValue.TIMESTAMP);
            mUserRef.child("status").setValue("false");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.main_logout_button) {
            mUserRef.child("online").setValue(ServerValue.TIMESTAMP);
            mUserRef.child("status").setValue("false");
            FirebaseAuth.getInstance().signOut();
            mRegProgressDialog.setTitle("Logging Out");
            mRegProgressDialog.setMessage("Logout");
            mRegProgressDialog.setCanceledOnTouchOutside(false);
            mRegProgressDialog.show();
            sendToStart();
            mRegProgressDialog.hide();
        }
        if (id == R.id.main_settings_btn) {
            Intent settingIntent = new Intent(BottomNavigationActivity.this, SettingsActivity.class);
            startActivity(settingIntent);
        }
        return true;
    }

    private void sendToStart() {

        Intent intent = new Intent(BottomNavigationActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();
        displayScreen(id);
        return true;
    }

    private void displayScreen(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.navigation_tts:
                fragment = new TextToSpeechFragment();
                break;
            case R.id.navigation_stt:
                fragment = new SpechtoTextFragmet();
                break;
            case R.id.navigation_chat:
                fragment = new ChatFragment();
                break;
            case R.id.navigation_image:
                fragment = new ImageButtonFragment();
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }
    public void getAddress(Context context, double LATITUDE, double LONGITUDE) {

        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                String fullAddress = address+ ", "+city+", "+state+", "+country+", "+postalCode+", "+knownName+" ";
                Toast.makeText(this, "Adress is:  "+fullAddress+" ", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}