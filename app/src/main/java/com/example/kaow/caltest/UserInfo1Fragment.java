package com.example.kaow.caltest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class UserInfo1Fragment extends Fragment {
    TextView pID;
    TextView showSEX;
    TextView showAGE;
    TextView showWEIGHT;
    TextView showHEIGHT;
    TextView pNAME;
    Button btnReset;
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    SimpleCursorAdapter adapter;
    PersonalData mPerson;
    MyFoodDb dbFood;
    Foods mFood;
    private String id = "1";

    public UserInfo1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_user_info1, container, false);
        showSEX = (TextView) rootview.findViewById(R.id.show_sex);
        showAGE = (TextView) rootview.findViewById(R.id.show_age);
        showWEIGHT = (TextView) rootview.findViewById(R.id.show_weight);
        showHEIGHT = (TextView) rootview.findViewById(R.id.show_height);
        pNAME = (TextView) rootview.findViewById(R.id.username);
        btnReset = (Button) rootview.findViewById(R.id.btnResetCal);

        dbHelper = new MyDbHelper(getActivity());
        dbFood = new MyFoodDb(getActivity());
        mPerson = dbHelper.getPerson(id);

        pNAME.setText(mPerson.getpUNAME());
        showSEX.setText(mPerson.getSEX());
        showAGE.setText(mPerson.getAGE());
        showWEIGHT.setText(mPerson.getWEIGTH());
        showHEIGHT.setText(mPerson.getHEIGTH());

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.change_data_title));
                builder.setMessage(getString(R.string.change_data_message));

                AlertDialog.Builder builder1 = builder.setPositiveButton(getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sharedpreferences = getActivity().getSharedPreferences
                                        (MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);  // ดึง share preference ชื่อ MyPrefs เก็บไว้ในตัวแปร sharedpreferences
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.clear();  // ทำการลบข้อมูลทั้งหมดจาก preferences
                                editor.commit();
                                String title = "กำลังคำนวณข้อมูล";
                                String msg = "กรุณารอสักครู่";
                                dialog = ProgressDialog.show(getActivity(), title, msg);
                                dbFood.deleteAllFoodRow();
                                dbHelper.deleteFriend(id);
                                Intent change = new Intent(getActivity(), MainActivity.class);
                                startActivity(change);
                                overridePendingTransition(android.R.anim.fade_in,
                                        android.R.anim.fade_out);
                            }




                        });
                builder.setNegativeButton(getString(android.R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }

            private void overridePendingTransition(int fade_in, int fade_out) {

            }
        });

        return  rootview;
    }


}
