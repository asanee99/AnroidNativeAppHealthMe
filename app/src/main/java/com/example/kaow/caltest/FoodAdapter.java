package com.example.kaow.caltest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Kaow on 6/23/15 AD.
 */


public class FoodAdapter extends SimpleAdapter implements Filterable {


    public LayoutInflater inflater=null;
    private Context mContext;
    String fcolor;
    public FoodAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View vi=convertView;
        if(convertView==null)
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

        ImageView image=(ImageView) vi.findViewById(R.id.food_image);
        String image_url = (String) data.get("foodpic");
        Picasso.with(mContext).load(image_url)
                .resize(200,200)
                .placeholder(R.drawable.ic_launcher)
                .centerCrop()
                .into(image);

        ImageView foodColor = (ImageView) vi.findViewById(R.id.FoodColor);
        if (Objects.equals(mFoodtype2, "อาหารแนะนำสำหรับผู้ที่ต้องการลดน้ำหนัก")){
            Picasso.with(mContext).load(R.drawable.foodcolor2)
                    .resize(60,60)
                    .placeholder(R.drawable.ic_launcher)
                    .centerCrop()
                    .into(foodColor);
        } else if (Objects.equals(mFoodtype2, "อาหารแนะนำสำหรับผู้ที่น้ำหนักอยู่ในเกณฑ์ปกติ")){
            Picasso.with(mContext).load(R.drawable.foodcolor1)
                    .resize(60,60)
                    .placeholder(R.drawable.ic_launcher)
                    .centerCrop()
                    .into(foodColor);
        } else if (Objects.equals(mFoodtype2, "อาหารแนะนำสำหรับผู้ที่ต้องการเพิ่มน้ำหนัก")){
            Picasso.with(mContext).load(R.drawable.foodcolor3)
                    .resize(60,60)
                    .placeholder(R.drawable.ic_launcher)
                    .centerCrop()
                    .into(foodColor);
        }


        return vi;





        /*View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.food_image);
            holder.tvFoodName = (TextView) v.findViewById(R.id.food_name);
            holder.tvFoodCal = (TextView) v.findViewById(R.id.food_cal);
            holder.tvFoodType1 = (TextView) v.findViewById(R.id.food_type);
            holder.tvFoodType2 = (TextView) v.findViewById(R.id.food_type2);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        String url = (foodList.get(position).getPicurl());
        holder.tvFoodName.setText(foodList.get(position).getFoodname());
        holder.tvFoodCal.setText(foodList.get(position).getFoodcal());
        holder.tvFoodType1.setText(foodList.get(position).getFoodtype1());
        holder.tvFoodType2.setText(foodList.get(position).getFoodtype2());
        Picasso.with(getContext())
                .load(url)
                .placeholder(R.drawable.ic_launcher)
                .into(holder.imageview);

        return v;*/

    }



    static class ViewHolder {
        public ImageView imageview;



    }

}