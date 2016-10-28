package com.example.kaow.caltest;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
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
public class ShowFoodMid extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener,SearchView.OnQueryTextListener {

    SearchView mSearchView;
    ListView list;
    ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_food);
        mSearchView= (SearchView) findViewById(R.id.searchView1);

        list = (ListView) findViewById(R.id.list);
        list.setTextFilterEnabled(true);
        setupSearchView();


    }
    private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
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

        String url = "http://55118020115.msci.dusit.ac.th/showFoodMid.php";
        SelectTask3 task = new SelectTask3(this, url);
        task.execute();
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

/*FoodAdapter adapter = new FoodAdapter(this, R.layout.item, foodsList);
    setListAdapter(adapter); */