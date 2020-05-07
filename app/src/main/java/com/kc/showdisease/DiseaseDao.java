package com.kc.showdisease;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DiseaseDao {
    @Query("select * from Disease")
    List<Disease> getAll();

    @Insert
    void insert(Disease disease);

    @Update
    void update(Disease disease);

    @Delete
    void delete(Disease disease);
}
