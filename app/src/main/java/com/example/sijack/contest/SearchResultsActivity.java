package com.example.sijack.contest;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.sijack.contest.database.AppDatabase;
import com.example.sijack.contest.database.Professor;
import com.example.sijack.contest.database.Room;

import java.util.ArrayList;
import java.util.List;

import static android.os.SystemClock.sleep;

public class SearchResultsActivity extends AppCompatActivity {

    RecyclerView auleeList, labList, ufficiList;
    AppDatabase db;
    String[] roomsString;
    Activity a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = this;
        setContentView(R.layout.activity_search_results);
        db = AppDatabase.getInstance(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auleeList = findViewById(R.id.auleeList);
        labList = findViewById(R.id.labList);
        ufficiList = findViewById(R.id.ufficiList);

        RecyclerView.LayoutManager firstLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager secondLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager thirdLayoutManager = new LinearLayoutManager(this);

        auleeList.setLayoutManager(firstLayoutManager);
        labList.setLayoutManager(secondLayoutManager);
        ufficiList.setLayoutManager(thirdLayoutManager);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Room> rooms;
                    List<Professor> professors;
                    rooms = db.roomDao().getRoomsByString("%" + query + "%");
                    professors = db.professorDao().findProfessors("%" + query + "%");
                    Room profRoom;
                    Log.d("ROOMS SEARCH", rooms.get(0).getNumber() + "");
                    for (Professor p : professors) {
                        Log.d("SEARCH", "PROFESSOR " + p.getOfficeNumber() + " " + p.getBuilding());

                        profRoom = db.roomDao().getRoomByNumber(p.getOfficeNumber(), p.getBuilding());
                        Log.d("SEARCH", "found " + profRoom.getNumber());
                        if (!rooms.contains(profRoom)) {
                            rooms.add(profRoom);
                            Log.d("ROOMS ADDING", p.getOfficeNumber() + "");

                        }
                    }

                    Log.d("ROOMS SIZE", rooms.size() + "");
                    List<Room> aulee = new ArrayList<Room>(), uffici = new ArrayList<Room>(), laboratori = new ArrayList<Room>();
                    //roomsString = new String[rooms.size()];
                    for (int i=0; i<rooms.size(); i++) {
                        Log.d("ROOMS INDEX", i + "");

                        Room r = rooms.get(i);
                        Log.d("ROOMS SEARCH", rooms.get(i).getNumber() + "");

                        switch (r.getType()) {
                            case "Aula":
                                aulee.add(r);
                                break;
                            case "Ufficio":
                                uffici.add(r);
                                break;
                            case "Laboratorio":
                                laboratori.add(r);
                                break;
                        }
                    }

                    RecyclerViewAdapter auleeAdapter = new RecyclerViewAdapter(a , aulee.toArray(new Room[aulee.size()]));
                    auleeList.setAdapter(auleeAdapter);
                    RecyclerViewAdapter ufficiAdapter = new RecyclerViewAdapter(a , uffici.toArray(new Room[uffici.size()]));
                    ufficiList.setAdapter(ufficiAdapter);
                    RecyclerViewAdapter laboratoriAdapter = new RecyclerViewAdapter(a , laboratori.toArray(new Room[laboratori.size()]));
                    labList.setAdapter(laboratoriAdapter);


                }
            }).start();
        }
    }

    public void textClicked(View view) {
        Intent intent = new Intent();
        intent.putExtra("roomId", (int) view.getTag());
        setResult(RESULT_OK, intent);
        finish();
    }
}
