package zuja.buzz.gpstracking.di.modules;

import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zuja.buzz.gpstracking.GPSTrackingApplication;
import zuja.buzz.gpstracking.dao.DatabaseHelper;
import zuja.buzz.gpstracking.dao.DriverDAO;
import zuja.buzz.gpstracking.dao.LocationPointDAO;
import zuja.buzz.gpstracking.dao.RideDAO;
import zuja.buzz.gpstracking.dao.RideSegmentDAO;
import zuja.buzz.gpstracking.dao.SettingsDAO;

@Module
public class RoomModule {

    public static final String DATABASE_NAME = "gpsDB";

    private DatabaseHelper databaseHelper;

    public RoomModule(GPSTrackingApplication application) {
        this.databaseHelper = Room.databaseBuilder(
                application.getApplicationContext(), DatabaseHelper.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    DatabaseHelper providesRoomDatabase(){
        return databaseHelper;
    }

    @Provides
    @Singleton
    DriverDAO provideDriverDAO(){
        return databaseHelper.driverDAO();
    }

    @Provides
    @Singleton
    SettingsDAO provideSettingsDAO(){
        return databaseHelper.settingsDAO();
    }

    @Provides
    @Singleton
    RideDAO provideRideDAO(){
        return databaseHelper.rideDAO();
    }

    @Provides
    @Singleton
    RideSegmentDAO provideRideSegmentDAO(){
        return databaseHelper.rideSegmentDAO();
    }

    @Provides
    @Singleton
    LocationPointDAO provideLocationPointDAO(){
        return databaseHelper.locationPointDAO();
    }

}
