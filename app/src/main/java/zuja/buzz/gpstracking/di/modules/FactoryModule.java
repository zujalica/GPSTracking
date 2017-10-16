package zuja.buzz.gpstracking.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zuja.buzz.gpstracking.repositories.DriverRepository;
import zuja.buzz.gpstracking.repositories.SettingsRepository;
import zuja.buzz.gpstracking.viewmodels.factories.DriverProfileViewModelFactory;
import zuja.buzz.gpstracking.viewmodels.factories.MainActivityViewModelFactory;

@Module
public class FactoryModule {

    @Provides
    @Singleton
    MainActivityViewModelFactory provideMainActivityViewModelFactory(DriverRepository driverRepository, SettingsRepository settingsRepository){
        return new MainActivityViewModelFactory(driverRepository, settingsRepository);
    }

    @Provides
    @Singleton
    DriverProfileViewModelFactory provideDriverFragmentViewModelFactory(DriverRepository repository){
        return new DriverProfileViewModelFactory(repository);
    }
}
