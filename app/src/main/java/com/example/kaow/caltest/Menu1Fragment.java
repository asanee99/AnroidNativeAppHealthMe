package com.example.kaow.caltest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class Menu1Fragment extends Fragment {
    ListView bf_list;
    ListView lu_list;
    ListView din_list;

    public Menu1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview =  inflater.inflate(R.layout.fragment_menu1, container, false);

        bf_list = (ListView) rootview.findViewById(R.id.food_b_list);

        lu_list = (ListView) rootview.findViewById(R.id.food_l_list);

        din_list = (ListView) rootview.findViewById(R.id.food_d_list);


        new SelectTask_Breakfast().execute();
        new SelectTask_Lunch().execute();
        new SelectTask_Dinner().execute();

        return  rootview;
    }

    private class SelectTask_Breakfast extends AsyncTask<Void, Void, String> {


        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setCancelable(true);
            dialog.setMessage("Loading...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setProgress(0);
            dialog.show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });
            thread.start();

        }
        @Override
        protected String doInBackground(Void... params) {
            String jsonString = JsonHttp.makeHttpRequest("http://55118020115.msci.dusit.ac.th/MenuFood_1_B.php");
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

                        FoodAdapter adapter = new FoodAdapter(getActivity(),
                                mFoodList, R.layout.item, keys, views);

                        bf_list.setAdapter(adapter);
                    }
                } else if (success == 0) {
                    bf_list.setAdapter(null);
                    String msg = json.getString("message");
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    private class SelectTask_Lunch extends AsyncTask<Void, Void, String> {


        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setCancelable(true);
            dialog.setMessage("Loading...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setProgress(0);
            dialog.show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });
            thread.start();

        }
        @Override
        protected String doInBackground(Void... params) {
            String jsonString = JsonHttp.makeHttpRequest("http://55118020115.msci.dusit.ac.th/MenuFood_1_L.php");
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

                        FoodAdapter adapter = new FoodAdapter(getActivity(),
                                mFoodList, R.layout.item, keys, views);

                        lu_list.setAdapter(adapter);
                    }
                } else if (success == 0) {
                    lu_list.setAdapter(null);
                    String msg = json.getString("message");
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    private class SelectTask_Dinner extends AsyncTask<Void, Void, String> {


        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setCancelable(true);
            dialog.setMessage("Loading...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setProgress(0);
            dialog.show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                }
            });
            thread.start();

        }
        @Override
        protected String doInBackground(Void... params) {
            String jsonString = JsonHttp.makeHttpRequest("http://55118020115.msci.dusit.ac.th/MenuFood_1_D.php");
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

                        FoodAdapter adapter = new FoodAdapter(getActivity(),
                                mFoodList, R.layout.item, keys, views);

                        din_list.setAdapter(adapter);
                    }
                } else if (success == 0) {
                    din_list.setAdapter(null);
                    String msg = json.getString("message");
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


}
