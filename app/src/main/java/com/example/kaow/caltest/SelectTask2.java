package com.example.kaow.caltest;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kaow on 7/2/15 AD.
 */
public class SelectTask2 extends AsyncTask<Void, Void, String> {


    private Context mContext;
    private String mUrl;

    public SelectTask2(Context context, String url) {
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

                    ((ShowFoodDown) mContext).list.setAdapter(adapter);
                }
            } else if (success == 0) {
                ((ShowFoodDown) mContext).list.setAdapter(null);
                String msg = json.getString("message");
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
