package com.example.sijack.contest.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import java.util.concurrent.Executors;

/**
 * Created by Sijack on 25/01/2018.
 */

@Database(entities = {Room.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RoomDao roomDao();

    private static AppDatabase INSTANCE;



    public synchronized static AppDatabase getInstance(Context context) {
        Log.d("DEBUG", "instance");
        if (INSTANCE == null) {
            Log.d("DEBUG", "instance if");

            INSTANCE = buildDatabase(context);
        } else {

            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    if (INSTANCE.roomDao().getAll().size() == 0) {
                        INSTANCE.roomDao().insertAll(Room.populateData());
                        Log.d("DEBUG", "db created");
                    }
                }
            });

        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return android.arch.persistence.room.Room.databaseBuilder(context,
                AppDatabase.class,
                "my-database").fallbackToDestructiveMigration()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Log.d("DEBUG", "first time");

                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).roomDao().insertAll(Room.populateData());
                                Log.d("DEBUG", "db created");
                            }
                        });

                    }
                })
                .build();
    }


}
