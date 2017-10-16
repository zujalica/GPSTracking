package zuja.buzz.gpstracking.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import zuja.buzz.gpstracking.models.DriverModel;
import zuja.buzz.gpstracking.models.LocationPointModel;
import zuja.buzz.gpstracking.models.RideModel;
import zuja.buzz.gpstracking.models.RideSegmentModel;
import zuja.buzz.gpstracking.models.SettingsModel;

/**
 * Used to create the database, and return the requested DAO layer.
 */
@Database(entities =
                {DriverModel.class,
                SettingsModel.class,
                RideSegmentModel.class,
                LocationPointModel.class,
                RideModel.class},
        version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {

    public static final String DATABASE_NAME = "gpsDB";

    private static final Object LOCK = new Object();
    private static volatile DatabaseHelper sInstance;

    public static DatabaseHelper getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                if(sInstance == null){
                    sInstance = Room.databaseBuilder(
                            context.getApplicationContext(), DatabaseHelper.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return sInstance;
    }

    public abstract DriverDAO driverDAO();

    public abstract SettingsDAO settingsDAO();

    public abstract RideSegmentDAO rideSegmentDAO();

    public abstract LocationPointDAO locationPointDAO();

    public abstract RideDAO rideDAO();
}
