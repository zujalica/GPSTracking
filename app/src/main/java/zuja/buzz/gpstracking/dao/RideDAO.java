package zuja.buzz.gpstracking.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import zuja.buzz.gpstracking.models.RideModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Queries for rides table.
 */
@Dao
public interface RideDAO {

    @Insert(onConflict = REPLACE)
    long insert(RideModel ride);

    @Update(onConflict = REPLACE)
    void update(RideModel ride);

    @Query("SELECT * FROM rides")
    LiveData<RideModel> getAllRides();

    @Query("SELECT * FROM rides WHERE bookingId = :bookingId")
    LiveData<RideModel> getRidesWithBookingId(int bookingId);
}
