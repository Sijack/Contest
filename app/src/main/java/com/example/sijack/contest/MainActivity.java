package com.example.sijack.contest;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
//import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.sijack.contest.database.AppDatabase;
import com.example.sijack.contest.database.Room;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    ImageView plant;
    ScrollView scrollView;
    HorizontalScrollView hScrollView;
    ConstraintLayout root;
    RelativeLayout fc;
    LinearLayout llBottomSheet;
    FloatingActionMenu fabMenu;
    com.github.clans.fab.FloatingActionButton b_1, b0, b1, b2, b3, b4;
    float screen_density;
    int screenw_px, screenh_px;
    boolean isScrollable = false;
    BottomSheetBehavior bottomSheetBehavior;
    AppDatabase db;
    List<Room> rooms;
    int building;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = AppDatabase.getInstance(getApplicationContext());

        fabMenu = findViewById(R.id.menu);

        b_1 = new com.github.clans.fab.FloatingActionButton(getApplicationContext());
        b_1.setButtonSize(com.github.clans.fab.FloatingActionButton.SIZE_MINI);
        b_1.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        b_1.setImageResource(R.drawable.smallm1);
        b_1.setTag("m1");
        b_1.setColorNormal(getResources().getColor(R.color.lightBlue));
        b_1.setOnClickListener(this);

        b0 = new com.github.clans.fab.FloatingActionButton(getApplicationContext());
        b0.setButtonSize(com.github.clans.fab.FloatingActionButton.SIZE_MINI);
        b0.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        b0.setImageResource(R.drawable.small0);
        b0.setTag("0");
        b0.setColorNormal(getResources().getColor(R.color.lightBlue));
        b0.setOnClickListener(this);

        b1 = new com.github.clans.fab.FloatingActionButton(getApplicationContext());
        b1.setButtonSize(com.github.clans.fab.FloatingActionButton.SIZE_MINI);
        b1.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        b1.setImageResource(R.drawable.small1);
        b1.setTag("1");
        b1.setColorNormal(getResources().getColor(R.color.lightBlue));
        b1.setOnClickListener(this);

        b2 = new com.github.clans.fab.FloatingActionButton(getApplicationContext());
        b2.setButtonSize(com.github.clans.fab.FloatingActionButton.SIZE_MINI);
        b2.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        b2.setImageResource(R.drawable.small2);
        b2.setTag("2");
        b2.setColorNormal(getResources().getColor(R.color.lightBlue));
        b2.setOnClickListener(this);

        b3 = new com.github.clans.fab.FloatingActionButton(getApplicationContext());
        b3.setButtonSize(com.github.clans.fab.FloatingActionButton.SIZE_MINI);
        b3.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        b3.setImageResource(R.drawable.small3);
        b3.setTag("3");
        b3.setColorNormal(getResources().getColor(R.color.lightBlue));
        b3.setOnClickListener(this);

        b4 = new com.github.clans.fab.FloatingActionButton(getApplicationContext());
        b4.setButtonSize(com.github.clans.fab.FloatingActionButton.SIZE_MINI);
        b4.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        b4.setImageResource(R.drawable.small4);
        b4.setTag("4");
        b4.setColorNormal(getResources().getColor(R.color.lightBlue));
        b4.setOnClickListener(this);

        root = findViewById(R.id.root);

        plant = findViewById(R.id.plant_img);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);

// init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        hScrollView = new HorizontalScrollView(this);
        hScrollView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        fc = new RelativeLayout(this);
        fc.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        Display display = getWindowManager().getDefaultDisplay();

        //Dimensione display, in pixel reali
        Point size = new Point();
        display.getSize(size);
        screenw_px = size.x;
        screenh_px = size.y;

        //Densità
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screen_density = metrics.density;

        navigationView.getMenu().getItem(1).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(1));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchResultsActivity.class)));
        searchView.setIconifiedByDefault(false);
        Log.d("SEARCH", searchManager.getSearchableInfo(getComponentName()).toString());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (isScrollable)
            imageClicked(plant);

       switch (id) {
           case R.id.edificio_f:
               //TODO caricare i piani nel floating menu
               plant.setImageResource(R.drawable.f_stecca7_piano4);
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       rooms = db.roomDao().getRoomsByBuildingFloor("F", 4);
                       Log.d("ROOMS", rooms.size() + "");
                   }
               }).start();
               fabMenu.removeAllMenuButtons();
               fabMenu.addMenuButton(b4);
               fabMenu.addMenuButton(b2);
               building = 0;
               break;

           case R.id.edificio_f2:
               plant.setImageResource(R.drawable.f2_piano0);
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       rooms = db.roomDao().getRoomsByBuildingFloor("F2", 0);
                       Log.d("ROOMS", rooms.size() + "");
                   }
               }).start();
               fabMenu.removeAllMenuButtons();
               fabMenu.addMenuButton(b1);
               fabMenu.addMenuButton(b0);
               fabMenu.addMenuButton(b_1);
               building = 1;
               break;
           case R.id.edificio_f3:
               plant.setImageResource(R.drawable.f3_piano0);
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       rooms = db.roomDao().getRoomsByBuildingFloor("F3", 0);
                       Log.d("ROOMS", rooms.size() + "");
                   }
               }).start();
               fabMenu.removeAllMenuButtons();
               fabMenu.addMenuButton(b2);
               fabMenu.addMenuButton(b1);
               fabMenu.addMenuButton(b0);
               fabMenu.addMenuButton(b_1);
               building = 2;
               break;
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void imageClicked(View view) {
        if (isScrollable) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

            root.removeView(scrollView);
            scrollView.removeView(hScrollView);
            hScrollView.removeView(fc);
            fc.removeView(view);
            fc.removeAllViews();

            root.addView(view);

            isScrollable = false;
        } else {
            root.removeView(view);

            root.addView(scrollView);
            scrollView.addView(hScrollView);
            hScrollView.addView(fc);
            fc.addView(view);

            addRooms();


            isScrollable = true;
        }

    }

    @Override
    public void onClick(View view) {
        if (view instanceof com.github.clans.fab.FloatingActionButton) {
            final View v = view;
            String resName;
            switch (building) {
                case 0:
                    resName = "f_stecca7_piano" + view.getTag();
                    plant.setImageResource(getResources().getIdentifier(resName, "drawable", getPackageName()));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            rooms = db.roomDao().getRoomsByBuildingFloor("F", Integer.parseInt((String)v.getTag()));
                            addRooms();
                            Log.d("ROOMS", rooms.size() + "");
                        }
                    }).start();
                    break;
                case 1:
                    resName = "f2_piano" + view.getTag();
                    plant.setImageResource(getResources().getIdentifier(resName, "drawable", getPackageName()));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            rooms = db.roomDao().getRoomsByBuildingFloor("F2", v.getTag().equals("m1")?-1:Integer.parseInt((String)v.getTag()));
                            Log.d("ROOMS", rooms.size() + "");
                        }
                    }).start();
                    break;
                case 2:
                    resName = "f3_piano" + view.getTag();
                    plant.setImageResource(getResources().getIdentifier(resName, "drawable", getPackageName()));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            rooms = db.roomDao().getRoomsByBuildingFloor("F3", v.getTag().equals("m1")?-1:Integer.parseInt((String)v.getTag()));
                            Log.d("ROOMS", rooms.size() + "");
                        }
                    }).start();
                    break;
            }
            if (isScrollable)
             imageClicked(plant);
        }
    }

    public void addRooms() {
        MarkerImageView iv;
        Bitmap marker = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
        int markerd = marker.getWidth();

        for (Room r : rooms) {
            int x = r.getX()*(int)screen_density;
            int y = r.getY()*(int)screen_density;
            int w = r.getWidth()*(int)screen_density;
            int h = r.getHeight()*(int)screen_density;

            iv = new MarkerImageView(this);
            iv.setX(x+(w/2)-(markerd/2));
            iv.setY(y+(h/2)-(markerd/2));
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            iv.setOnClickListener(new MarkerListener(this, fc, llBottomSheet, r));


            fc.addView(iv);

        }
    }
}
