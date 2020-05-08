package com.kc.showdisease;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = {Disease.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DiseaseDao diseaseDao();
}
