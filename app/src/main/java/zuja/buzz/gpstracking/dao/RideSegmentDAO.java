package zuja.buzz.gpstracking.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import zuja.buzz.gpstracking.models.RideSegmentModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RideSegmentDAO {

    @Insert(onConflict = REPLACE)
    long insertBooking(RideSegmentModel booking);

    @Query("SELECT * FROM rideSegments")
    RideSegmentModel[] getAllRideSegments();

    @Query("SELECT * FROM rideSegments WHERE bookingId = :bookingId")
    RideSegmentModel[] getSegmentsWithBookingId(int bookingId);
}
