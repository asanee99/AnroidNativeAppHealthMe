package com.example.kaow.caltest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityAdapter extends ArrayAdapter<ActivityList> {

    private Context context;
    private int itemLayoutId;
    private ActivityList[] countries = null;

    public ActivityAdapter(Context context, int itemLayoutId, ActivityList[] countries) {
        super(context, itemLayoutId, countries);
        
        this.context = context;
        this.itemLayoutId = itemLayoutId;
        this.countries = countries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View item = inflater.inflate(itemLayoutId, parent, false);
        
        ImageView imgFlag = (ImageView) item.findViewById(R.id.act_image);
        TextView txtCountryName = (TextView) item.findViewById(R.id.act_desc);

        ActivityList country = countries[position];
        imgFlag.setImageResource(country.getFlagImageId());
        txtCountryName.setText(country.getCountryName());
        
        return item;
    }
    
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

}
