package com.example.kaow.caltest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.DialogPreference;
import android.renderscript.Sampler;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaow on 7/9/15 AD.
 */
public class Dinner extends Fragment {
    private String id = "1";
    private String Foodtime = "Dinner";

    MyDbHelper dbHelper;
    PersonalData mPerson;
    MyFoodDb dbFood;
    Foods mFood;
    TextView nCAL;
    TextView aCal;
    Cursor cursor;
    ListView list;
    SimpleCursorAdapter sAdapter;
    String sumCal_string;
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;

    @Override
    public void onResume() {
        super.onResume();

        db = dbFood.getWritableDatabase();

        cursor = dbFood.getDinner();
        String[] showColumns = new String[] { Foods.fColumn.COL_FNAME, Foods.fColumn.COL_FCAL };

        int[] view = new int[] { android.R.id.text1, android.R.id.text2};
        sAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.two_line_list_item, cursor, showColumns, view);

        list.setAdapter(sAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_3, container, false);

        list = (ListView) rootView.findViewById(R.id.list_dinner);

        dbFood = new MyFoodDb(getActivity());

        Button sum = (Button) rootView.findViewById(R.id.sumfood);
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sum = new Intent(getActivity(), SumFood.class);
                startActivity(sum);
            }
        });
        Button reset = (Button) rootView.findViewById(R.id.resetfood);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbFood.deleteAllFoodRow();
                sAdapter.notifyDataSetChanged();
                Intent reset = new Intent(getActivity(), MainFrag.class);
                startActivity(reset);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                String fName = cursor.getString(1);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ลบข้อมูลอาหาร");
                builder.setMessage("คุณต้องการลบ " + fName + "ใช่หรือไม่");
                builder.setPositiveButton("ใช่",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String rowId = cursor.getString(0);
                                dbFood.deleteFood(rowId);
                                cursor.requery();
                                sAdapter.notifyDataSetChanged();
                                Intent i = new Intent(getActivity(), MainFrag.class);
                                startActivity(i);

                            }
                        });
                builder.setNegativeButton("ไม่",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });builder.show();
            }
        });
        rootView.findViewById(R.id.insertInfo)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), Drawer.class);
                        intent.putExtra("Foodtime", Foodtime);
                        startActivity(intent);
                    }
                });

        return rootView;
    }
}
