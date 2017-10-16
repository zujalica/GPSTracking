package zuja.buzz.gpstracking.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import zuja.buzz.gpstracking.repositories.GPSRepository;
import zuja.buzz.gpstracking.viewmodels.RideViewModel;

@Singleton
public class RideViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private GPSRepository gpsRepository;

    @Inject
    public RideViewModelFactory(GPSRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new RideViewModel(gpsRepository);
    }
}
