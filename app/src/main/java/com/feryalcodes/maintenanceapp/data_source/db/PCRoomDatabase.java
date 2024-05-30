package com.feryalcodes.maintenanceapp.data_source.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.feryalcodes.maintenanceapp.data_source.db.dao.ServiceDao;
import com.feryalcodes.maintenanceapp.model.service.ServiceModel;
import com.feryalcodes.maintenanceapp.model.service.ServiceResult;

@Database(entities = {ServiceResult.class}, version = 1, exportSchema = true)
public abstract class PCRoomDatabase extends RoomDatabase {

    private static volatile PCRoomDatabase mRoomInstance;

    public static PCRoomDatabase getDatabase(final Context context) {
        if (mRoomInstance == null) {
            synchronized (PCRoomDatabase.class) {
                if (mRoomInstance == null) {
                    mRoomInstance = Room.databaseBuilder(context.getApplicationContext(), PCRoomDatabase.class, "pc_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return mRoomInstance;
    }

    public abstract ServiceDao serviceDao();
}
