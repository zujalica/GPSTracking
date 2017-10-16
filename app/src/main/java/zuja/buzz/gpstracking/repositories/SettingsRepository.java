package zuja.buzz.gpstracking.repositories;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import zuja.buzz.gpstracking.dao.SettingsDAO;
import zuja.buzz.gpstracking.models.SettingsModel;
/**
 * Settings repository. Used for communication with the database and web services.
 */
@Singleton
public class SettingsRepository {

    private SettingsDAO settingsDAO;
    private Executor executor;

    @Inject
    public SettingsRepository(SettingsDAO settingsDAO, Executor executor) {
        this.settingsDAO = settingsDAO;
        this.executor = executor;
    }

    public void addSettings(final SettingsModel settings){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                settingsDAO.addSettings(settings);
            }
        });
    }

    public void updateSettings(final SettingsModel settings){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                settingsDAO.updateSettings(settings);
            }
        });
    }

    public void clearSettings(final SettingsModel settings){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                settingsDAO.clearSettings(settings);
            }
        });
    }

    public SettingsModel loadSetings(){
        return settingsDAO.loadSettings();
    }
}
