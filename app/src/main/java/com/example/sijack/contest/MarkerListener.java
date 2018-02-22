package com.example.sijack.contest;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sijack.contest.database.AppDatabase;
import com.example.sijack.contest.database.Professor;
import com.example.sijack.contest.database.Room;

import java.util.List;

/**
 * Created by Sijack on 30/01/2018.
 */

public class MarkerListener implements View.OnClickListener {
    private boolean focused = false;
    private ViewGroup frameContainer;
    private Context context;
    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout bottomSheet;
    private Room room;
    private AppDatabase db;
    private String text = "";

    public MarkerListener(Context context, ViewGroup fc, View bottomSheet, Room r) {
        frameContainer = fc;
        this.context = context;
        this.bottomSheet = (LinearLayout) bottomSheet;
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        room = r;
        db = AppDatabase.getInstance(context);
    }

    @Override
    public void onClick(View view) {
        MarkerImageView thisIv = (MarkerImageView) view;
        TextView tv = bottomSheet.findViewById(R.id.room_name);

        if (thisIv.isFocused()) {
            thisIv.setFocused(false);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {

            for (int i = 1; i < frameContainer.getChildCount(); i++) {
                MarkerImageView v = (MarkerImageView) frameContainer.getChildAt(i);
                if (v.isFocused()) {
                    v.setFocused(false);
                }
            }

            thisIv.setFocused(true);
            switch (room.getType()) {
                case "Ufficio":
                   Thread t = new Thread(new FinderThread());
                   t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tv.setText(text);

                    break;
                default:
                        tv.setText(room.getType() + " " + room.getNumber());
                        break;
            }

            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED && bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }

        }

    }

    public class FinderThread implements Runnable {

        @Override
        public void run() {
            List<Professor> professors = db.professorDao().getProfessorByOffice(room.getNumber(), room.getBuilding());
            for (Professor p : professors) {
                text = text + p.getSurname() + " " + p.getName().substring(0,1) + "., ";
            }
            text = text + room.getType() + " " + room.getNumber();
        }
    }

}
