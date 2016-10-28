package com.example.kaow.caltest;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kaow on 7/12/15 AD.
 */
public class FoodAdapterTwo extends SimpleAdapter implements Filterable {


    public LayoutInflater inflater=null;
    private Context mContext;
    private Fragment mFragment;

    public FoodAdapterTwo(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item, null);

        HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);

        TextView foodname = (TextView) vi.findViewById(R.id.food_name);
        String mFoodname = (String) data.get("foodname");
        foodname.setText(mFoodname);

        TextView cal = (TextView) vi.findViewById(R.id.food_cal);
        String mCal = (String) data.get("calories");
        cal.setText(mCal);

        TextView foodtype1 = (TextView) vi.findViewById(R.id.food_type);
        String mFoodtype1 = (String) data.get("foodtype1");
        foodtype1.setText(mFoodtype1);

        TextView foodtype2 = (TextView) vi.findViewById(R.id.food_type2);
        String mFoodtype2 = (String) data.get("foodtype2");
        foodtype2.setText(mFoodtype2);

        ImageView image = (ImageView) vi.findViewById(R.id.food_image);
        String image_url = (String) data.get("foodpic");
        Picasso.with(mContext).load(image_url)
                .resize(300, 300)
                .placeholder(R.drawable.ic_launcher)
                .centerCrop()
                .into(image);

        return vi;
    }

    static class ViewHolder {
        public ImageView imageview;



    }

}