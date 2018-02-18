package com.example.sijack.contest;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sijack.contest.database.Room;

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

    public MarkerListener(Context context, ViewGroup fc, View bottomSheet, Room r) {
        frameContainer = fc;
        this.context = context;
        this.bottomSheet = (LinearLayout) bottomSheet;
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        room = r;
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
            Log.d("FOCUS", thisIv.isFocused() + "");
            tv.setText(room.getType() + " " + room.getNumber());

            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED && bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }

        }

    }

}
