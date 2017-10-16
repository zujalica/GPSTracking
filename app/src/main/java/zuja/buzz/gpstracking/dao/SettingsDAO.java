package zuja.buzz.gpstracking.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import zuja.buzz.gpstracking.models.SettingsModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Queries for settings table.
 */
@Dao
public interface SettingsDAO {

    @Insert(onConflict = REPLACE)
    void addSettings(SettingsModel setings);

    @Query("SELECT * FROM settings")
    SettingsModel loadSettings();

    @Update(onConflict = REPLACE)
    void updateSettings(SettingsModel settings);

    @Delete
    void clearSettings(SettingsModel settings);
}
