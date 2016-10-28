package com.example.kaow.caltest;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SelectTask extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private String mUrl;
    Fragment mFragment;
    FoodMainFragment foodMain;
    ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(mContext);
        dialog.setCancelable(true);
        dialog.setMessage("Loading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setProgress(0);
        dialog.show();

    }

    public SelectTask(Context context, String url) {
        super();
        mUrl = url;
        mContext = context;

    }

    @Override
    protected String doInBackground(Void... params) {
        String jsonString = JsonHttp.makeHttpRequest(mUrl);
        return jsonString;

    }

    @Override
    protected void onPostExecute(String jsonString) {

        ArrayList<HashMap<String, String>> mFoodList;

        try {
            JSONObject json = new JSONObject(jsonString);
            int success = json.getInt("success");


            if (success == 1) {
                JSONArray food = json.getJSONArray("food");
                mFoodList = new ArrayList<HashMap<String, String>>();

                for (int i = 0; i < food.length(); i++) {
                    JSONObject mFood = food.getJSONObject(i);

                    String id = mFood.getString("foodID");
                    String name = mFood.getString("foodname");
                    String cals = mFood.getString("calories");
                    String type1 = mFood.getString("foodtype1");
                    String type2 = mFood.getString("foodtype2");
                    String pic = mFood.getString("foodpic");


                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("foodID", id);
                    map.put("foodname", name);
                    map.put("calories", cals);
                    map.put("foodtype1", type1);
                    map.put("foodtype2", type2);
                    map.put("foodpic", pic);


                    mFoodList.add(map);


                    String[] keys = new String[] { "foodID", "foodname", "calories", "foodtype1", "foodtype2" };
                    int[] views = new int[] { R.id.food_id, R.id.food_name,
                            R.id.food_cal, R.id.food_type, R.id.food_type2  };

                    FoodAdapter adapter = new FoodAdapter(mContext,
                            mFoodList, R.layout.item, keys, views);

                    ((FoodMainFragment) mFragment).list_food_main.setAdapter(adapter);
                }
            } else if (success == 0) {
                ((FoodMainFragment) mFragment).list_food_main.setAdapter(null);
                String msg = json.getString("message");
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
