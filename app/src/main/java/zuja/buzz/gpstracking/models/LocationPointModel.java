package zuja.buzz.gpstracking.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * GPS point model + database entity
 */
@Entity(tableName = "locationPoints")
public class LocationPointModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int bookingId;
    private String status;
    private String timestamp;
    private double lat;
    private double lng;

    @Ignore
    public LocationPointModel() {
    }

    public LocationPointModel(int id, int bookingId, String status, String timestamp, double lat, double lng) {
        this.id = id;
        this.bookingId = bookingId;
        this.status = status;
        this.timestamp = timestamp;
        this.lat = lat;
        this.lng = lng;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
