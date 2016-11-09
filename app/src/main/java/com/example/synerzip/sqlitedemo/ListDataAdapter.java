package com.example.synerzip.sqlitedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by synerzip on 17/3/16.
 */
public class ListDataAdapter extends ArrayAdapter {
    List list;

    public ListDataAdapter(Context context, int resource, List<DataProvider> list) {
        super(context, resource);
        this.list = list;
    }
    static class LayoutHandler{
        TextView ID,NAME,MO,EMAIL;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutHandler layoutHandler;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.ID=(TextView)row.findViewById(R.id.idUser_ID);
            layoutHandler.NAME=(TextView)row.findViewById(R.id.idUser_name);
            layoutHandler.MO=(TextView)row.findViewById(R.id.idMobile_No);
            layoutHandler.EMAIL=(TextView)row.findViewById(R.id.idEmail_Id);
            row.setTag(layoutHandler);

        }
        else {
            layoutHandler=(LayoutHandler)row.getTag();
                          }
      //  DataProvider dataProvider=(DataProvider)this.getItem(position);
        DataProvider dataProvider = (DataProvider) list.get(position);
        layoutHandler.ID.setText(String.valueOf(dataProvider.getId()));
        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.MO.setText(dataProvider.getMob());
        layoutHandler.EMAIL.setText(dataProvider.getEmail());

        return row;
    }
}
