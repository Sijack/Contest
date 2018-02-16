package com.example.sijack.contest;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sijack.contest.database.Room;

/**
 * Created by Sijack on 16/02/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Activity activity;
    private Room[] rooms;

    public RecyclerViewAdapter(Activity activity, Room[] rooms) {
        this.activity = activity;
        this.rooms = rooms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_element, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Room r = rooms[position];
        viewHolder.textView.setText(r.getType() + " " + r.getNumber() + ", Edificio " + r.getBuilding());
        viewHolder.textView.setTag(r.getId());
    }

    @Override
    public int getItemCount() {
        return rooms.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textViewList);
        }
    }
}