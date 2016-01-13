package com.iayon.retrofit20tutorial;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import models.Items;


public class UserAdapters extends BaseAdapter {

    private ArrayList<Items> users ;
    private Context context ;

    public UserAdapters(Context ctx, ArrayList<Items> items) {
        super();
        this.context = ctx ;
        this.users = items ;
    }

    @Override
    public int getCount() {
        return this.users.size();
    }

    @Override
    public long getItemId(int i) {
        return 0 ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.cell, viewGroup, false);
        TextView tv = (TextView) v.findViewById(R.id.textview);
        Items user = users.get(i);
        tv.setText(user.getName() + System.getProperty("line.separator") + user.getArea());
        Log.d("Adapter", user.getName());
        return v;
    }
}
