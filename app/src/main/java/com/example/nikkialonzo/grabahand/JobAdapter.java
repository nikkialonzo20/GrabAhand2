package com.example.nikkialonzo.grabahand;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class JobAdapter extends ArrayAdapter<JobItem> {

    Context context;
    List<JobItem> drawerItemList;
    int layoutResID;


    public JobAdapter(Context context, int layoutResourceID,
                      List<JobItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (null == convertView) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.ItemName = (TextView) view.findViewById(R.id.jobName);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        JobItem dItem = this.drawerItemList.get(position);

        if (dItem.getId() == 0)
            drawerHolder.ItemName.setText("EMERGENCIES:");
        else
            drawerHolder.ItemName.setText("\n\tID: [" + dItem.getId() + "]\n\tName: " + dItem.getName() + "\n");

        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
    }
}
