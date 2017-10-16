package zuja.buzz.gpstracking.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import zuja.buzz.gpstracking.models.DriverModel;
import zuja.buzz.gpstracking.repositories.DriverRepository;

/**
 * Viewmodel for driver profile fragment.
 */
public class DriverProfileViewModel extends ViewModel {

    private MutableLiveData<DriverModel> driverLiveData;
    private DriverRepository driverRepository;

    public DriverProfileViewModel() {
        driverLiveData = new MutableLiveData<>();
    }

    @Inject
    public DriverProfileViewModel(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    public void init(DriverModel driverModel){
        if(driverLiveData == null){
            driverLiveData = new MutableLiveData<>();
        }
        driverLiveData.setValue(driverModel);
    }

    public MutableLiveData<DriverModel> getDriverLiveData() {
        return driverLiveData;
    }

    public void setDriverLiveData(DriverModel driverModelLiveData) {
        this.driverLiveData.postValue(driverModelLiveData);
    }

    public void saveDriver(){
        driverRepository.updateDriver(driverLiveData.getValue());
    }
}
