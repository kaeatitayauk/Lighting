package com.example.samlee.lighting;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by samlee on 5/14/2018.
 */

public class CustomAdapterAdmin extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<StudentModelAdmin> Items;


    public CustomAdapterAdmin(Activity activity, ArrayList<StudentModelAdmin> Items){
        this.activity = activity;
        this.Items = Items;
    }

    @Override
    public int getCount() {
        return Items.size();
    }

    @Override
    public StudentModelAdmin getItem(int position) {
        return Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
           inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
           convertView = inflater.inflate(R.layout.row,null);
        }
        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        //TextView tvAge = (TextView) convertView.findViewById(R.id.tvAge);

        final StudentModelAdmin sm = Items.get(position);

        tvId.setText(sm.getiD());
        tvName.setText(sm.getName());
        //tvAge.setText(sm.getAge());


        return convertView;
    }

}
