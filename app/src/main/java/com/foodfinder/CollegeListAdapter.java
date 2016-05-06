package com.foodfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class CollegeListAdapter extends BaseAdapter {

    private ArrayList<College> colleges;
    private Context context;

    public CollegeListAdapter(Context context, ArrayList<College> colleges) {
        this.colleges = colleges;
        this.context = context;
    }

    @Override
    public int getCount() {
        return colleges.size();
    }

    @Override
    public Object getItem(int position) {
        return colleges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        Holder holder;
        if (null == view) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.list_item_college, parent, false);
            holder.image = (ImageView) view.findViewById(R.id.imageview_college);
            holder.coordinates = (TextView) view.findViewById(R.id.textview_coordinates);
            holder.name = (TextView) view.findViewById(R.id.textview_name);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        College college = (College) getItem(position);
        holder.name.setText(college.name);
        holder.coordinates.setText("");
        holder.image.setImageResource(college.image);
        return view;
    }
//college.longitude + ", " + college.lattitude
    class Holder {
        public ImageView image;
        public TextView name;
        public TextView coordinates;
    }
}
