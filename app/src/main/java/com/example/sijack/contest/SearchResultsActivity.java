package com.example.sijack.contest;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sijack.contest.database.AppDatabase;
import com.example.sijack.contest.database.Room;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    ListView auleeList, labList, ufficiList;
    AppDatabase db;
    String[] roomsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        db = AppDatabase.getInstance(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auleeList = findViewById(R.id.auleeList);
        labList = findViewById(R.id.labList);
        ufficiList = findViewById(R.id.ufficiList);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("SEARCH", query);
            //use the query to search your data somehow

            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Room> rooms;
                    rooms = db.roomDao().getRoomsByString("%" + query + "%");
                    Log.d("ROOMS SEARCH", rooms.size() + "");
                    roomsString = new String[rooms.size()];
                    for (int i=0; i<rooms.size(); i++) {
                        Room r = rooms.get(i);
                        roomsString[i] = r.getType() + " " + r.getNumber() + ", Edificio " + r.getBuilding();
                        Log.d("ROOM FOUND", roomsString[i] + " " + r.getId());
                    }

                            ArrayAdapter<String> auleeAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element, R.id.textViewList, roomsString);
                            auleeList.setAdapter(auleeAdapter);


                }
            }).start();




        }
    }

}
