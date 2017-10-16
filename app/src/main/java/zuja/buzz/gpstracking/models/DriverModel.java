package zuja.buzz.gpstracking.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Driver model class + entity for the database
 */
@Entity
public class DriverModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String lastName;
    private int age;
    private String profilePicture;
    private String plateNumber;

    @Ignore
    public DriverModel() {
    }

    public DriverModel(int id, String name, String lastName, int age, String profilePicture, String plateNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.profilePicture = profilePicture;
        this.plateNumber = plateNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
