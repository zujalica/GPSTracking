package zuja.buzz.gpstracking.viewmodels;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import zuja.buzz.gpstracking.models.SettingsModel;
import zuja.buzz.gpstracking.repositories.SettingsRepository;

/**
 * ViewModel for SettingsFragment.
 */
public class SettingsViewModel extends ViewModel {

    private SettingsRepository settingsRepository;
    private SettingsModel settings;

    public void init(SettingsModel settings){
        this.settings = settings;
    }

    @Inject
    public SettingsViewModel(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public SettingsModel getSettings() {
        return settings;
    }

    public void updateSettings(){
        settingsRepository.updateSettings(this.settings);
    }
}
