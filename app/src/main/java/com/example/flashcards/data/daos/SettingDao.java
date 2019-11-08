package com.example.flashcards.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.flashcards.data.entities.Setting;

import java.util.List;

@Dao
public interface SettingDao {
    @Query("SELECT * FROM settings")
    List<Setting> getAll();

    @Query("SELECT * FROM settings WHERE id IN (:settingIDs)")
    List<Setting> loadAllByIds(int[] settingIDs);

    @Insert
    void insertAll(Setting... settings);

    @Delete
    void delete(Setting setting);
}
