package exnologialogismikou.tei.com.bestoffer;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class Home_screen extends FragmentActivity implements OnMapReadyCallback,
        LocationListener,
        GoogleMap.OnMyLocationButtonClickListener {

    private static final LatLng Serres = new LatLng(41.078628, 23.3545706);

    private GoogleMap mMap;
    private Marker mSerres;

    private LocationManager locationManager = null;
    Location mLastLocation = null;
    private int PERMISSION_LOCATION_REQUEST_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (checkPermission(this)) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0, 0,
                    this);
            mLastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_LOCATION_REQUEST_CODE);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0, 0,
                    this);
            mLastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Serres, Greece.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        enableMyLocation();//My Location and localize button
        mMap.setOnMyLocationButtonClickListener(this);

        mSerres = mMap.addMarker(new MarkerOptions()
                .position(Serres)
                .title("Shoes shop")
                .snippet("Irodotou 37"));
        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(Serres, 8);
        mMap.moveCamera(cameraPosition);
        mMap.animateCamera(cameraPosition);

        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        Toast.makeText(this,
                "Location Changed:\n latitude: " +
                        String.format(Locale.getDefault(), "%.2f", location.getLatitude()) +
                        " longitude: " +
                        String.format(Locale.getDefault(), "%.2f", location.getLongitude()),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, " Finding your location  . . . ", Toast.LENGTH_SHORT).show();
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        return false;
    }

    private void enableMyLocation() {
        if (!checkPermission(this)) {
            Toast.makeText(this, " GPS Access not granted to the app ", Toast.LENGTH_SHORT).show();
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    public static boolean checkPermission(Context context) {
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

}
