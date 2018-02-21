package com.example.sijack.contest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sijack.contest.database.AppDatabase;

/**
 * Created by Sijack on 21/02/2018.
 */

public class SplashActivity extends AppCompatActivity {
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getApplicationContext());
        Thread t = new Thread(new CheckThread());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private class CheckThread implements Runnable {

        @Override
        public void run() {
            while (db.roomDao().getAll().size()==0 && db.professorDao().getAll().size()==0) {

            }
        }
    }
}
