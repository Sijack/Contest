package com.example.sijack.contest;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

    private static final int ACTIVITY_REQUEST_CODE = 0;
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
    NavigationView navigationView;
    int building, floorSelected = -2, iw, ih, iW, iH;
    Toolbar toolbar;
    Thread t, adder;
    public static int focusedMarkerId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()/*new ComponentName(this, SearchResultsActivity.class*/));
        searchView.setIconifiedByDefault(false);
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
        String resName;

        switch (id) {
            case R.id.edificio_f:
                resName = "f_stecca7_piano" + (floorSelected == -2 ? "4" : floorSelected+"");
                plant.setImageResource(getResources().getIdentifier(resName, "drawable", getPackageName()));

                t = new Thread(new MyThread("F", floorSelected == -2 ? "4" : floorSelected+"")) ;
                t.start();

                fabMenu.removeAllMenuButtons();
                fabMenu.addMenuButton(b4);
                fabMenu.addMenuButton(b2);
                building = 0;
                break;

            case R.id.edificio_f2:
                resName = "f2_piano" + (floorSelected == -2 ? "0" : (floorSelected == -1 ? "m1" : floorSelected+""));
                plant.setImageResource(getResources().getIdentifier(resName, "drawable", getPackageName()));

                t = new Thread(new MyThread("F2", floorSelected == -2 ? "0" : floorSelected+"")) ;
                t.start();

                fabMenu.removeAllMenuButtons();
                fabMenu.addMenuButton(b1);
                fabMenu.addMenuButton(b0);
                fabMenu.addMenuButton(b_1);
                building = 1;
                break;
            case R.id.edificio_f3:
                resName = "f3_piano" + (floorSelected == -2 ? "0" : (floorSelected == -1 ? "m1" : floorSelected+""));
                plant.setImageResource(getResources().getIdentifier(resName, "drawable", getPackageName()));

                t = new Thread(new MyThread("F3", floorSelected == -2 ? "0" : floorSelected+"")) ;
                t.start();

                fabMenu.removeAllMenuButtons();
                fabMenu.addMenuButton(b2);
                fabMenu.addMenuButton(b1);
                fabMenu.addMenuButton(b0);
                fabMenu.addMenuButton(b_1);
                building = 2;
                break;
        }

        focusedMarkerId = -1;
        floorSelected = -2;

        setScrollable(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void imageClicked(View view) {

        MarkerImageView iv;
        for (Room r : rooms) {
            iv = findViewById(r.getId());
            if (iv.isFocused()) {
                focusedMarkerId = r.getId();
                Log.d("CLICKED", r.getId() + "");
                break;
            }
        }

        if (isScrollable) {

            setScrollable(false);

        } else {

            setScrollable(true);

        }

    }

    private void setScrollable(boolean scrollable) {
        if (!scrollable) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

            root.removeView(scrollView);
            root.removeAllViews();

            scrollView.removeView(hScrollView);
            hScrollView.removeView(fc);
            fc.removeView(plant);
            fc.removeAllViews();

            root.addView(plant);

            isScrollable = false;

            addRooms();
        } else {
            root.removeAllViews();

            root.addView(scrollView);
            scrollView.addView(hScrollView);
            hScrollView.addView(fc);
            fc.addView(plant);

            isScrollable = true;

            addRooms();

        }
    }

    @Override
    public void onClick(View view) {
        if (view instanceof com.github.clans.fab.FloatingActionButton) {
            final View v = view;
            String resName;
            floorSelected = view.getTag().equals("m1") ? -1 : Integer.parseInt((String)view.getTag());
            switch (building) {
                case 0:
                    navigationView.getMenu().getItem(0).setChecked(true);
                    onNavigationItemSelected(navigationView.getMenu().getItem(0));
                    break;
                case 1:
                    navigationView.getMenu().getItem(1).setChecked(true);
                    onNavigationItemSelected(navigationView.getMenu().getItem(1));
                    break;
                case 2:
                    navigationView.getMenu().getItem(2).setChecked(true);
                    onNavigationItemSelected(navigationView.getMenu().getItem(2));
                    break;
            }
        }
    }

    public void addRooms() {

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adder = new Thread(new AddMarkerThread());
        plant.post(adder);
    }

    private void handleIntent(Intent intent) {
        // Get the intent, verify the action and get the query
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            // manually launch the real search activity
            final Intent searchIntent = new Intent(getApplicationContext(),
                    SearchResultsActivity.class);
            // add query to the Intent Extras
            searchIntent.putExtra(SearchManager.QUERY, query);
            searchIntent.setAction(Intent.ACTION_SEARCH);
            Log.d("INTENT", "Starting activity for result");
            startActivityForResult(searchIntent, ACTIVITY_REQUEST_CODE);
        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("INTENT", "on new intent");
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d("RESULT",  "entering");
        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            final int roomId = data.getIntExtra("roomId", -1);
            Log.d("RESULT", roomId + "");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Room room = db.roomDao().getRoomById(roomId);
                    floorSelected = room.getFloor();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setBuilding(room.getBuilding());

                            plant.post(new Runnable() {
                                @Override
                                public void run() {
                                    MarkerImageView iv = findViewById(roomId);
                                    iv.callOnClick();
                                    iv.bringToFront();
                                }
                            });
                          /*
                            //getFocusOnView(iv, room.getX(), room.getY());*/
                        }
                    });
                }
            }).start();
        }
    }

    private void setBuilding(String building) {
        //setchecked?
        switch (building) {
            case "F":
                navigationView.getMenu().getItem(0).setChecked(true);
                onNavigationItemSelected(navigationView.getMenu().getItem(0));
                break;
            case "F2":
                navigationView.getMenu().getItem(1).setChecked(true);
                onNavigationItemSelected(navigationView.getMenu().getItem(1));
                break;
            case "F3":
                navigationView.getMenu().getItem(2).setChecked(true);
                onNavigationItemSelected(navigationView.getMenu().getItem(2));
                break;
        }

    }

    private void getFocusOnView(MarkerImageView iv, final int x, final int y) {
        if (isScrollable) {
            hScrollView.post(new Runnable() {
                @Override
                public void run() {
                    hScrollView.scrollTo(x * (int) screen_density, 0);
                    scrollView.scrollTo(0, y * (int) screen_density);
                }
            });
        }
    }

    public class MyThread implements Runnable {

        private String building;
        private String tag;

        public MyThread(String b, String t) {
            building = b;
            tag = t;
        }

        @Override
        public void run() {

            rooms = db.roomDao().getRoomsByBuildingFloor(building, tag.equals("m1") ? -1 : Integer.parseInt(tag));
            Log.d("ROOMS", rooms.size() + "");

        }
    }

    public class AddMarkerThread implements Runnable {

        @Override
        public void run() {
            iw=screenw_px;//width of imageView
            iH=plant.getDrawable().getIntrinsicHeight();
            iW = plant.getDrawable().getIntrinsicWidth();//original height of underlying image
            int scale=plant.getDrawable().getIntrinsicWidth()/screenw_px;
            ih = screenw_px*iH/iW;//original width of underlying image

            Log.d("PLANT SIZE", iw + " " + ih + " " + iW + " " + iH + " " + plant.getPivotY() + ", " + scale);

            MarkerImageView iv;
            Bitmap marker = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
            int markerd = marker.getWidth();
            int offset = (int)root.getPivotY() - (ih/2);

            if (isScrollable) {

                for (Room r : rooms) {
                    int x = r.getX() * (int) screen_density;
                    int y = r.getY() * (int) screen_density;
                    int w = r.getWidth() * (int) screen_density;
                    int h = r.getHeight() * (int) screen_density;

                    iv = new MarkerImageView(getApplicationContext());
                    iv.setX(x + (w / 2) - (markerd / 2));
                    iv.setY(y + (h / 2) - (markerd / 2));
                    iv.setId(r.getId());
                    iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

                    iv.setOnClickListener(new MarkerListener(getApplicationContext(), fc, llBottomSheet, r));


                    fc.addView(iv);

                    if (r.getId() == focusedMarkerId) {
                        iv.callOnClick();
                        iv.bringToFront();
                        getFocusOnView(iv, r.getX(), r.getY());
                    }

                }
            }
            else {

                for (Room r : rooms) {
                    int x = Math.round(iw * (r.getX() * screen_density) / iW);
                    int y = Math.round((ih * (r.getY() * screen_density) / iH)) + offset;
                    int w = Math.round(iw * (r.getWidth() * screen_density) / iW);
                    int h = Math.round(iw * (r.getHeight() * screen_density) / iW);
                    iv = new MarkerImageView(getApplicationContext());
                    iv.setPivotX(0);
                    iv.setPivotY(0);
                    iv.setScaleX(0.5f);
                    iv.setScaleY(0.5f);
                    iv.setX(x + (w / 2) - (markerd /4));
                    iv.setY(y + (h / 2) - (markerd / 4));
                    iv.setId(r.getId());
                    iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    iv.setOnClickListener(new MarkerListener(getApplicationContext(), root, llBottomSheet, r));

                    root.addView(iv);

                    if (r.getId() == focusedMarkerId) {
                        iv.callOnClick();
                        iv.bringToFront();
                    }
                }
            }

        }
    }
}
