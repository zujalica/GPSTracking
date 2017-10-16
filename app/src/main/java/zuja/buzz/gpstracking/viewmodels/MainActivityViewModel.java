package zuja.buzz.gpstracking.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.res.Configuration;

import org.eclipse.jdt.internal.compiler.batch.Main;

import java.util.Locale;

import javax.inject.Inject;

import zuja.buzz.gpstracking.Constants;
import zuja.buzz.gpstracking.activities.MainActivity;
import zuja.buzz.gpstracking.models.DriverModel;
import zuja.buzz.gpstracking.models.SettingsModel;
import zuja.buzz.gpstracking.repositories.DriverRepository;
import zuja.buzz.gpstracking.repositories.SettingsRepository;

/**
 * ViewModel for the main activity. Holds the driver and settings data.
 */
public class MainActivityViewModel extends ViewModel {

    private DriverRepository driverRepository;
    private SettingsRepository settingsRepository;

    private DriverModel driverModel;
    private SettingsModel settings;

    public MainActivityViewModel(DriverRepository driverRepository, SettingsRepository settingsRepository){
        this.driverRepository = driverRepository;
        this.settingsRepository = settingsRepository;
    }

    public void init(MainActivity activity){
        driverModel = driverRepository.getDriver(Constants.DEFAULT_DRIVER_ID);
        if(driverModel == null){
            driverModel = new DriverModel();
            driverModel.setId(Constants.DEFAULT_DRIVER_ID);
            driverRepository.insertDriver(driverModel);
        }
        this.settings = setupSettings(activity);
    }

    private SettingsModel setupSettings(MainActivity activity){
        SettingsModel settings = new SettingsModel();
        settings.setLocale(setupLocale(activity));
        return settings;
    }

    private Locale setupLocale(MainActivity activity){
        SettingsModel settings = settingsRepository.loadSetings();
        Configuration configuration = activity.getResources().getConfiguration();
        if(settings == null){
            Locale locale = configuration.locale;
            settings = new SettingsModel(locale);
            settingsRepository.addSettings(settings);
        }else{
            Locale.setDefault(settings.getLocale());
            configuration.setLocale(settings.getLocale());
            activity.getResources().updateConfiguration(configuration, null);
        }
        activity.changeNavigationBarTitles();
        return settings.getLocale();
    }

    public DriverModel getDriverModel() {
        return driverModel;
    }

    public void setDriverModel(DriverModel driverModel) {
        this.driverModel = driverModel;
    }

    public SettingsRepository getSettingsRepository() {
        return settingsRepository;
    }

    public void setSettingsRepository(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public SettingsModel getSettings() {
        return settings;
    }

    public void setSettings(SettingsModel settings) {
        this.settings = settings;
    }
}
