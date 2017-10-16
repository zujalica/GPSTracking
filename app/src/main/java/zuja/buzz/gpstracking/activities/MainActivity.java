package zuja.buzz.gpstracking.activities;


import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import zuja.buzz.gpstracking.GPSTrackingApplication;
import zuja.buzz.gpstracking.R;
import zuja.buzz.gpstracking.fragments.DriverProfileFragment;
import zuja.buzz.gpstracking.fragments.MyRidesFragment;
import zuja.buzz.gpstracking.fragments.NewRideFragment;
import zuja.buzz.gpstracking.fragments.SettingsFragment;
import zuja.buzz.gpstracking.models.DriverModel;
import zuja.buzz.gpstracking.models.SettingsModel;
import zuja.buzz.gpstracking.viewmodels.MainActivityViewModel;
import zuja.buzz.gpstracking.viewmodels.factories.MainActivityViewModelFactory;

/**
 * Main and only activity in this app, used to handle different fragments
 */
public class MainActivity extends AppCompatActivity {

    private String currentFragmentString;
    private GPSTrackingApplication application;

    private MainActivityViewModel activityViewModel;

    @Inject
    MainActivityViewModelFactory factory;

    @InjectView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        application = ((GPSTrackingApplication) getApplication());
        application.getFactoryComponent().inject(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        activityViewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        activityViewModel.init(this);
        swithFragment(MyRidesFragment.TAG);
    }

    /**
     * Switches different fragments based on fragment TAG. Searches for an existing fragment and if
     * it doesn't exist it creates a new one, otherwise reuses the same fragment.
     *
     * @param fragmentTag
     */
    private void swithFragment(String fragmentTag){
        if(fragmentTag != null && !TextUtils.equals(currentFragmentString, fragmentTag)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            Fragment targetFragment = fragmentManager.findFragmentByTag(fragmentTag);
            if(targetFragment == null){
                targetFragment = createNewFragment(fragmentTag);
            }
            fragmentTransaction.replace(R.id.fragment_container, targetFragment, fragmentTag);
            currentFragmentString = fragmentTag;
            fragmentTransaction.commit();
        }
    }

    /**
     * Creates a new fragment based on it's TAG.
     *
     * @param tag
     * @return
     */
    private Fragment createNewFragment(String tag){
        switch (tag) {
            case DriverProfileFragment.TAG:
                return new DriverProfileFragment();
            case SettingsFragment.TAG:
                return new SettingsFragment();
            case NewRideFragment.TAG:
                return new NewRideFragment();
            case MyRidesFragment.TAG:
                return new MyRidesFragment();
        }
        return null;
    }

    /**
     * Gets the driver taht uses this app.
     *
     * @return
     */
    public DriverModel getDriver(){
        return activityViewModel.getDriverModel();
    }

    /**
     * Click listener for bottom navigationBar. Mainly used for switching fragments
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_my_rides:
                    // new fragment for driver rides
                    swithFragment(MyRidesFragment.TAG);
                    return true;
                case R.id.navigation_new_ride:
                    // new fragment for new ride
                    swithFragment(NewRideFragment.TAG);
                    return true;
                case R.id.navigation_profile:
                    // new fragment for driver profile
                    swithFragment(DriverProfileFragment.TAG);
                    return true;
                case R.id.navigation_settings:
                    // new fragment for settings
                    swithFragment(SettingsFragment.TAG);
                    return true;
            }
            return false;
        }

    };

    /**
     * Changes the locale of the app (Languages)
     * @param locale
     */
    public void changeLocale(Locale locale){
        Locale.setDefault(locale);
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, null);
        changeNavigationBarTitles();
    }

    /**
     * Since the bottom navigation bar lives outside of activity's scope, we need to manually refresh
     * the titles.
     */
    public void changeNavigationBarTitles(){
        navigation.getMenu().findItem(R.id.navigation_my_rides).setTitle(R.string.navigatio_title_myRides);
        navigation.getMenu().findItem(R.id.navigation_new_ride).setTitle(R.string.navigation_title_newRide);
        navigation.getMenu().findItem(R.id.navigation_profile).setTitle(R.string.navigation_title_profile);
        navigation.getMenu().findItem(R.id.navigation_settings).setTitle(R.string.navigation_title_settings);
    }

    public SettingsModel getSettings(){
        return activityViewModel.getSettings();
    }
}
