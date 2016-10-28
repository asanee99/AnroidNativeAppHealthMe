package com.example.kaow.caltest;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Kaow on 6/22/15 AD.
 */
public class ShowFoodDown extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener  {


    ListView list;
    ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_food);


        list = (ListView) findViewById(R.id.list);


    }

    @Override
    public void onResume() {
        super.onResume();

        getAlldata();
    }

    private void getAlldata() {

        String url = "http://55118020115.msci.dusit.ac.th/showFoodDown.php";
        SelectTask2 task = new SelectTask2(this, url);

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