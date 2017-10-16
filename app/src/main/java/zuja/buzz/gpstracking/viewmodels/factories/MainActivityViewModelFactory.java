package zuja.buzz.gpstracking.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import zuja.buzz.gpstracking.repositories.DriverRepository;
import zuja.buzz.gpstracking.repositories.SettingsRepository;
import zuja.buzz.gpstracking.viewmodels.MainActivityViewModel;

@Singleton
public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final DriverRepository driverRepository;
    private final SettingsRepository settingsRepository;

    @Inject
    public MainActivityViewModelFactory(DriverRepository driverRepository, SettingsRepository settingsRepository) {
        this.driverRepository = driverRepository;
        this.settingsRepository = settingsRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new MainActivityViewModel(driverRepository, settingsRepository);
    }
}
