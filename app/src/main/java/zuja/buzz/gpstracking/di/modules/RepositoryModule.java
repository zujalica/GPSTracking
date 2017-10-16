package zuja.buzz.gpstracking.di.modules;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zuja.buzz.gpstracking.dao.DriverDAO;
import zuja.buzz.gpstracking.dao.LocationPointDAO;
import zuja.buzz.gpstracking.dao.RideDAO;
import zuja.buzz.gpstracking.dao.RideSegmentDAO;
import zuja.buzz.gpstracking.dao.SettingsDAO;
import zuja.buzz.gpstracking.repositories.DriverRepository;
import zuja.buzz.gpstracking.repositories.GPSRepository;
import zuja.buzz.gpstracking.repositories.SettingsRepository;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    DriverRepository provideDriverRepository(DriverDAO driverDAO, Executor executor){
        return new DriverRepository(driverDAO, executor);
    }

    @Provides
    @Singleton
    SettingsRepository provideSettingsRepository(SettingsDAO settingsDAO, Executor executor){
        return new SettingsRepository(settingsDAO, executor);
    }

    @Provides
    @Singleton
    GPSRepository provideGPSRepository(RideDAO rideDAO, RideSegmentDAO rideSegmentDAO, LocationPointDAO locationPointDAO, Executor executor){
        return new GPSRepository(rideDAO, rideSegmentDAO, locationPointDAO, executor);
    }

}
