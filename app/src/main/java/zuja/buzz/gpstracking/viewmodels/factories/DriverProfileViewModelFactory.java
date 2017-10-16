package zuja.buzz.gpstracking.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import zuja.buzz.gpstracking.repositories.DriverRepository;
import zuja.buzz.gpstracking.viewmodels.DriverProfileViewModel;

@Singleton
public class DriverProfileViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final DriverRepository mRepository;

    @Inject
    public DriverProfileViewModelFactory(DriverRepository mRepository) {
        this.mRepository = mRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DriverProfileViewModel(mRepository);
    }
}
