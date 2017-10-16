package zuja.buzz.gpstracking.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import zuja.buzz.gpstracking.models.LocationPointModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface LocationPointDAO {

    @Insert(onConflict = REPLACE)
    long insert(LocationPointModel locationPoint);

    @Query("SELECT * FROM locationPoints")
    LocationPointModel[] getAllLocations();

    @Query("SELECT * FROM locationPoints WHERE bookingId = :bookingId")
    LocationPointModel[] getLocationPointsWithBookingId(int bookingId);
}
