package com.example.kaow.caltest;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Kaow on 6/22/15 AD.
 */
public class ShowFoodAll extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener,SearchView.OnQueryTextListener {

    SearchView mSearchView;
    ListView list;
    ProgressDialog pDialog;
    private String aJustshow;
    MyFoodDb foodDb;
    Foods mFood;
    private  String Foodtime;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_food);
        mSearchView = (SearchView) findViewById(R.id.searchView1);

        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);

        list.setTextFilterEnabled(true);
        setupSearchView();

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
        Foodtime = bundle.getString("Foodtime");

        }
    }
    private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("ค้นหา");
    }
    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            list.clearTextFilter();
        } else {
            list.setFilterText(newText);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }
    @Override
    public void onResume() {
        super.onResume();

        getAlldata();
    }

    private void getAlldata() {

        String url = "http://55118020115.msci.dusit.ac.th/showFoodAll.php";
        SelectTaskAll task = new SelectTaskAll(this, url);
        task.execute();
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        String foodName = ((TextView) v.findViewById(R.id.food_name)).getText().toString();
        String foodCal = ((TextView) v.findViewById(R.id.food_cal)).getText().toString();
        String foodCal_kcal = foodCal + "  กิโลแคลอรี่";

        foodDb = new MyFoodDb(this);
        Foods foods = new Foods();
        foods.setFoodname(foodName);
        foods.setFoodcal(foodCal_kcal);
        foods.setFoodtype1(Foodtime);

        foodDb.addFoods(foods);

        Intent updateIntent = new Intent(this,
                MainFrag.class);
        startActivity(updateIntent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);


    }
}

/*FoodAdapter adapter = new FoodAdapter(this, R.layout.item, foodsList);
    setListAdapter(adapter); */