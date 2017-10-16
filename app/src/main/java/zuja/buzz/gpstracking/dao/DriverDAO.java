package zuja.buzz.gpstracking.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import zuja.buzz.gpstracking.models.DriverModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Queries for driver table.
 */
@Dao
public interface DriverDAO {

    @Insert(onConflict = REPLACE)
    long save(DriverModel driverModel);

    @Query("SELECT * FROM driverModel WHERE id = :driverId")
    DriverModel load(int driverId);

    @Query("UPDATE driverModel SET name = :name WHERE id = :driverId")
    void updateName(String name, int driverId);

    @Query("UPDATE driverModel SET lastName = :lastName WHERE id = :driverId")
    void updateLastName(String lastName, int driverId);

    @Query("UPDATE driverModel SET age = :age WHERE id = :driverId")
    void updateAge(int age, int driverId);

    @Query("UPDATE driverModel SET plateNumber = :plateNumber WHERE id = :driverId")
    void updatePlateNumber(String plateNumber, int driverId);

    @Query("UPDATE driverModel SET profilePicture = :profilePicture WHERE id = :driverId")
    void updateProfilePicture(String profilePicture, int driverId);

    @Update(onConflict = REPLACE)
    void updateDriver(DriverModel driverModel);
}
