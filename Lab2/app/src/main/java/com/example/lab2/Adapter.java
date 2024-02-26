package com.example.lab2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.transition.MaterialContainerTransform;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Contact> data;
    private LayoutInflater inflater;
    public Adapter( ArrayList<Contact> items,Activity activity){
        this.data = items;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.contact_item,null);

        }
        CheckBox box = v.findViewById(R.id.check);
        box.setChecked(data.get(position).getStatus());
        TextView ten =  v.findViewById(R.id.txtTen);
        TextView phone =  v.findViewById(R.id.txtPhone);

        ten.setText(data.get(position).getName());
        phone.setText(data.get(position).getPhone_number());

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.get(position).setStatus(isChecked);
            }
        });
        return v;
    }
}
