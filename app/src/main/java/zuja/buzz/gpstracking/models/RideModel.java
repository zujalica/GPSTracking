package zuja.buzz.gpstracking.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Ride model + database entity. One ride can have several segments.
 */
@Entity(tableName = "rides")
public class RideModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int bookingId;
    private String startTime;
    private String endTime;
    private int driverId;
    private String startLocation;
    private String endLocation;
    private int passengers;

    @Ignore
    private List<RideSegmentModel> segments;

    @Ignore
    public RideModel() {
    }

    public RideModel(int id, int bookingId, String startTime, String endTime, int driverId, String startLocation, String endLocation, int passengers) {
        this.id = id;
        this.bookingId = bookingId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driverId = driverId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.passengers = passengers;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public List<RideSegmentModel> getSegments() {
        return segments;
    }

    public void setSegments(List<RideSegmentModel> segments) {
        this.segments = segments;
    }
}
