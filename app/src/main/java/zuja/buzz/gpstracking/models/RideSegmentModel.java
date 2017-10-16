package zuja.buzz.gpstracking.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Ride Segment model + database entity. One segment can have multiple location points
 */
@Entity(tableName = "rideSegments")
public class RideSegmentModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int bookingId;
    private String timestamp;

    @Ignore
    private List<LocationPointModel> locations;

    @Ignore
    public RideSegmentModel() {
    }

    public RideSegmentModel(int id, int bookingId, String timestamp) {
        this.id = id;
        this.bookingId = bookingId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<LocationPointModel> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationPointModel> locations) {
        this.locations = locations;
    }
}
