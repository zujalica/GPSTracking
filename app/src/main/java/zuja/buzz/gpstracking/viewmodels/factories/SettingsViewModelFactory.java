package zuja.buzz.gpstracking.viewmodels.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import zuja.buzz.gpstracking.repositories.SettingsRepository;
import zuja.buzz.gpstracking.viewmodels.SettingsViewModel;

public class SettingsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    SettingsRepository settingsRepository;

    @Inject
    public SettingsViewModelFactory(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new SettingsViewModel(settingsRepository);
    }
}
