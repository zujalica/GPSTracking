package zuja.buzz.gpstracking.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import zuja.buzz.gpstracking.models.RideModel;
import zuja.buzz.gpstracking.repositories.GPSRepository;

/**
 * ViewModel for MyRidesFragment and NewRideFragment
 */
public class RideViewModel extends ViewModel {

    private GPSRepository gpsRepository;
    private MutableLiveData<RideModel> ride;

    public void init(int driverId){
        if(ride == null){
            ride = new MutableLiveData<>();
            ride.setValue(new RideModel());
        }
        ride.getValue().setDriverId(driverId);
    }

    @Inject
    public RideViewModel(GPSRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    public GPSRepository getGpsRepository() {
        return gpsRepository;
    }

    public void setGpsRepository(GPSRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    public MutableLiveData<RideModel> getRide() {
        return ride;
    }

    public void setRide(MutableLiveData<RideModel> ride) {
        this.ride = ride;
    }

    public RideModel getRideModel(){
        return this.ride.getValue();
    }
}
