package com.example.kaow.caltest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A placeholder fragment containing a simple view.
 */
public class BreakfastFragment extends Fragment implements SearchView.OnQueryTextListener,AdapterView.OnItemClickListener {

    private ListView list_food_all;
    SearchView mSearchView;
    Foods mFood;
    String fName;
    String cal;
    MyFoodDb foodDb;
    ImageView foodColor;
    int Foodtime;
    public BreakfastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View  rootView = inflater.inflate(R.layout.fragment_breakfast, container, false);

        list_food_all = (ListView) rootView.findViewById(R.id.list_food_all);
        mSearchView = (SearchView) rootView.findViewById(R.id.searchView2);
        foodColor = (ImageView) rootView.findViewById(R.id.FoodColor);

        list_food_all.setOnItemClickListener(this);
        list_food_all.setTextFilterEnabled(true);
        setupSearchView();

        new SelectTask().execute();

        return rootView;
    }


    private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mSearchView.clearFocus();
        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            list_food_all.clearTextFilter();
        } else {
            list_food_all.setFilterText(newText);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        String foodName = ((TextView) v.findViewById(R.id.food_name)).getText().toString();
        String foodCal = ((TextView) v.findViewById(R.id.food_cal)).getText().toString();
        String foodCal_kcal = foodCal + "  กิโลแคลอรี่";
        fName = foodName;
        cal = foodCal_kcal;

        final String[] items = { "อาหารเช้า","อาหารกลางวัน","อาหารเย็น"};
        new AlertDialog.Builder(getActivity())
                .setTitle("คุณต้องการเพิ่มอาหารนี้ในมื้อใด")
                .setSingleChoiceItems(items, 0, null)
                .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListView lv = ((AlertDialog) dialog).getListView();
                        int selectItem = lv.getCheckedItemPosition();
                        if (selectItem == 0) {

                            foodDb = new MyFoodDb(getActivity());
                            Foods foods = new Foods();
                            foods.setFoodname(fName);
                            foods.setFoodcal(cal);
                            foods.setFoodtype1("Breakfast");
                            foodDb.addFoods(foods);

                            Intent updateIntent = new Intent(getActivity(),
                                    MainFrag.class);
                            startActivity(updateIntent);
                        } else if (selectItem == 1) {

                            foodDb = new MyFoodDb(getActivity());
                            Foods foods = new Foods();
                            foods.setFoodname(fName);
                            foods.setFoodcal(cal);
                            foods.setFoodtype1("Lunch");
                            foodDb.addFoods(foods);

                            Intent updateIntent = new Intent(getActivity(),
                                    MainFrag.class);
                            startActivity(updateIntent);
                        } else if (selectItem == 2) {

                            foodDb = new MyFoodDb(getActivity());
                            Foods foods = new Foods();
                            foods.setFoodname(fName);
                            foods.setFoodcal(cal);
                            foods.setFoodtype1("Dinner");
                            foodDb.addFoods(foods);

                            Intent updateIntent = new Intent(getActivity(),
                                    MainFrag.class);
                            startActivity(updateIntent);
                        }
                    }

                })
                .setNegativeButton("ยกเลิก",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();


    }

    private void overridePendingTransition(int fade_in, int fade_out) {

    }



    private class SelectTask extends AsyncTask<Void, Void, String> {


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
                        Thread.sleep(2000);
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
            String jsonString = JsonHttp.makeHttpRequest("http://55118020115.msci.dusit.ac.th/showBreakfast.php");
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

                        list_food_all.setAdapter(adapter);
                    }
                } else if (success == 0) {
                    list_food_all.setAdapter(null);
                    String msg = json.getString("message");
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
