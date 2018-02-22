package com.example.sijack.contest.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.concurrent.Executors;

/**
 * Created by Sijack on 25/01/2018.
 */

@Database(entities = {Room.class, Professor.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RoomDao roomDao();
    public abstract ProfessorDao professorDao();
    static boolean ok = false;

    private static AppDatabase INSTANCE;



    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
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

                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).roomDao().insertAll(Room.populateData());
                                getInstance(context).professorDao().insertAll(Professor.populateData());
                            }
                        });

                    }
                })
                .build();
    }


}
