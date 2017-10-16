package zuja.buzz.gpstracking.fragments;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import zuja.buzz.gpstracking.GPSTrackingApplication;
import zuja.buzz.gpstracking.R;
import zuja.buzz.gpstracking.activities.MainActivity;
import zuja.buzz.gpstracking.models.RideModel;
import zuja.buzz.gpstracking.utils.PermissionUtils;
import zuja.buzz.gpstracking.viewmodels.RideViewModel;
import zuja.buzz.gpstracking.viewmodels.factories.RideViewModelFactory;

/**
 * TODO:Fragment with a mapView that records the drivers ride.
 */
public class NewRideFragment extends Fragment implements OnMapReadyCallback {

    /**
     * Enum used for checking the fragment state.
     * DEFAULT - the default state, before the driver started the drive
     * RIDE_STARTED - when the driver starts the ride
     * PASSENGERS_PICKED_UP - marks that the passangers have been picked up (probably wont be used)
     * PAUSED - the driver pulled over
     * CONTINUED - driver unpaused the ride
     * END_RIDE - the driver reached his destination and the ride is over
     */
    private enum RideState {
        DEFAULT, RIDE_STARTED, PASSENGERS_PICKED_UP, PAUSED, CONTINUED, END_RIDE;
    }

    /**
     * Locations request code
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    public final static String TAG = "NEW_RIDE_FRAGMENT_TAG";

    private RideViewModel newRideViewModel;
    /**
     * Current ride state (check the RideState enum for more details)
     */
    private RideState currentState;
    private GoogleMap mMap;
    /**
     * Show the permission denied dialog after check permission callback
     */
    private boolean mShowPermissionDeniedDialog = false;

    @Inject
    RideViewModelFactory factory;

    @InjectView(R.id.start_location)
    TextInputEditText startLocation;
    @InjectView(R.id.start_location_til)
    TextInputLayout startLocationTil;
    @InjectView(R.id.end_location)
    TextInputEditText endLocation;
    @InjectView(R.id.end_location_til)
    TextInputLayout endLocationTil;
    @InjectView(R.id.no_passangers)
    TextInputEditText noPassangers;
    @InjectView(R.id.no_passangers_til)
    TextInputLayout noPassangersTil;
    @InjectView(R.id.main_floating_button)
    FloatingActionButton mainFloatingButton;
    @InjectView(R.id.pause_floating_button)
    FloatingActionButton pauseFloatingButton;
    @InjectView(R.id.mapView)
    MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (currentState == null) {
            currentState = RideState.DEFAULT;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_ride, container, false);
        ButterKnife.inject(this, view);
        ((GPSTrackingApplication) getActivity().getApplication()).getFactoryComponent().inject(this);
        newRideViewModel = ViewModelProviders.of(this, factory).get(RideViewModel.class);
        newRideViewModel.init(((MainActivity) getActivity()).getDriver().getId());
        newRideViewModel.getRide().observe(this, new Observer<RideModel>() {
            @Override
            public void onChanged(@Nullable RideModel rideModel) {
                // update the map
            }
        });
        initializeMapView();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity().getApplicationContext());
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        getMyLocation();
    }

    /**
     * Initialize the mapView if necessary
     */
    public void initializeMapView() {
        if (mapView != null) {
            // Initialise the MapView
            mapView.onCreate(null);
            mapView.onPause();
            mapView.onResume();
            // Set the map ready callback to receive the GoogleMap object
            mapView.getMapAsync(this);
        }
    }

    /**
     * Try to get the drivers current location if allowed.
     */
    private void getMyLocation(){
        if(currentState == RideState.RIDE_STARTED){
            // Enable the location layer. Request the location permission if needed.
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                // Uncheck the box until the layer has been enabled and request missing permission.
                PermissionUtils.requestPermission(((MainActivity)getActivity()), LOCATION_PERMISSION_REQUEST_CODE,
                        Manifest.permission.ACCESS_FINE_LOCATION, false);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * Main floating button click listener.
     */
    @OnClick(R.id.main_floating_button)
    public void onMainFloatingButtonClicked() {
        switch (currentState) {
            case DEFAULT:
                break;
            case RIDE_STARTED:
                break;
            case PAUSED:
                break;
            case CONTINUED:
                break;
            case END_RIDE:
                break;
        }
    }

    /**
     * Secondary floating button used for pausing and resuming the ride.
     */
    @OnClick(R.id.pause_floating_button)
    public void onPauseFloatingButtonClicked() {
    }

    /**
     * Check permission callback.
     * @param requestCode
     * @param permissions
     * @param results
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] results) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, results,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            mMap.setMyLocationEnabled(true);
        } else {
            mShowPermissionDeniedDialog = true;
        }
    }

}
