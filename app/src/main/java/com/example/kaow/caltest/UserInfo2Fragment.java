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
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Objects;


/**
 * A placeholder fragment containing a simple view.
 */
public class UserInfo2Fragment extends Fragment {
    TextView showTextBMI;
    ImageView showbmi;
    TextView showBMI;
    TextView showBMR;
    Button btnReset;
    Button btnMoreInfo;
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    SimpleCursorAdapter adapter;
    PersonalData mPerson;
    MyFoodDb dbFood;
    Foods mFood;
    private String id = "1";
    private int GetInfo;

    public UserInfo2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_user_info2, container, false);
        //showTextBMI = (TextView) rootview.findViewById(R.id.show_TextBmi);
        showBMI = (TextView) rootview.findViewById(R.id.show_BMI);
        showBMR = (TextView) rootview.findViewById(R.id.show_BMR);
        btnReset = (Button) rootview.findViewById(R.id.btnResetCal);
        showbmi = (ImageView) rootview.findViewById(R.id.showBmi);
        btnMoreInfo = (Button) rootview.findViewById(R.id.moreInfo);
        dbHelper = new MyDbHelper(getActivity());
        dbFood = new MyFoodDb(getActivity());
        mPerson = dbHelper.getPerson(id);

        //showTextBMI.setText(mPerson.getTextBMI());
        showBMI.setText(mPerson.getBMI());
        showBMR.setText(mPerson.getBMRa());
        //showBmrA.setText(mPerson.getBMRa());
        String txtBmi = mPerson.getTextBMI();
        if (Objects.equals(txtBmi, "BmiThin")){
            GetInfo = 1;
            showbmi.setImageDrawable(getResources().getDrawable(R.drawable.bmi1));
        } else if (Objects.equals(txtBmi, "BmiMid")){
            GetInfo = 2;
            showbmi.setImageDrawable(getResources().getDrawable(R.drawable.bmi2));
        } else if (Objects.equals(txtBmi, "BmiFat1")){
            GetInfo = 3;
            showbmi.setImageDrawable(getResources().getDrawable(R.drawable.bmi3));
        } else if (Objects.equals(txtBmi, "BmiFat2")){
            GetInfo = 4;
            showbmi.setImageDrawable(getResources().getDrawable(R.drawable.bmi4));
        } else if (Objects.equals(txtBmi, "BmiFat3")){
            GetInfo = 5;
            showbmi.setImageDrawable(getResources().getDrawable(R.drawable.bmi5));
        }

        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoreInfo.class);
                i.putExtra("GetInfo", GetInfo);
                startActivity(i);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.change_data_title));
                builder.setMessage(getString(R.string.change_data_message));

                builder.setPositiveButton(getString(android.R.string.ok),
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
