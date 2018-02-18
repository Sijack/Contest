package com.example.sijack.contest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Sijack on 30/01/2018.
 */

public class MarkerImageView extends android.support.v7.widget.AppCompatImageView {

    private boolean focused = false;
    private Bitmap marker, blue_marker;

    public MarkerImageView(Context context) {
        super(context);
        marker = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker);
        blue_marker = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_marker);
        this.setImageBitmap(marker);
    }

    @Override
    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {

        if (focused) {
            this.setImageBitmap(blue_marker);
            Log.d("FOCUS", "setting blue");
        } else {
            this.setImageBitmap(marker);
        }

        this.focused = focused;
    }
}
