package com.example.kaow.caltest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kaow.caltest.PersonalData;
import com.example.kaow.caltest.MyDbHelper;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;

public class SubActivity extends Activity  {

    private MyDbHelper mHelper;
    private MyFoodDb myFoodDb;
    private int ID = -1;

    boolean isImageFitToScreen;

    private TextView textViewBMI;
    private TextView textViewBMIshowText;

    private TextView textViewBMR_beforeMulti;
    private TextView textViewBMR_afterMulti;

    private TextView textViewSex;
    private TextView textViewAge;
    private TextView textViewHeight;
    private TextView textViewWeight;
    private TextView textViewName;
    private ImageView genBMI;

    private String i_string;

    private String BMI_string;
    private double BMI_double;

    private String BMR_string_afterMulti2;
    private double BMR_double_afterMulti2;

    private String BMR_string_afterMulti;
    private double BMR_double_afterMulti;

    private String AGE;
    private double AGE_db;

    private String WEIGHT;
    private double WEIGHT_db;

    private String HEIGHT;
    private double HEIGHT_db;

    private String SEX;
    private String uNAME;
    private String HBEAT;
    ProgressDialog dialog;
    int GetInfo;
    ImageView ShowGenBmi;
    SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    int pID = 1;
    String pID_s;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_cal_1);

        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(true);

        Bundle bundle = getIntent().getExtras();

        Button btnSave = (Button) findViewById(R.id.mBtn1);
        Button btnMoreInfo = (Button) findViewById(R.id.moreInfo);
        mHelper = new MyDbHelper(this);
        myFoodDb = new MyFoodDb(this);



         /*
        Button mBtn1 = (Button) findViewById(R.id.mBtn1);

        mBtn1.setOnClickListener (new OnClickListener () {

            public void onClick(View  V) {
                Intent i =  new Intent (getApplicationContext(), ShowFoodUp.class);
                startActivity(i);
            }
        });


        Button mBtn2 = (Button) findViewById(R.id.mBtn2);
        mBtn2.setOnClickListener (new OnClickListener () {

            public void onClick(View  V) {
                Intent i =  new Intent (getApplicationContext(), ShowFoodDown.class);
                startActivity(i);
            }

        });
        */
        ShowGenBmi = (ImageView) findViewById(R.id.imgGenBMI);
        genBMI = (ImageView) findViewById(R.id.imgTextBMI);
        textViewBMI = (TextView) findViewById(R.id.bmiResult);
        textViewBMIshowText = (TextView) findViewById(R.id.bmiShowText);
        //textViewBMR_beforeMulti = (TextView) findViewById(R.id.bmrCalculate);
        textViewBMR_afterMulti = (TextView) findViewById(R.id.bmrIntroduce);
        textViewAge = (TextView) findViewById(R.id.age);
        textViewHeight = (TextView) findViewById(R.id.height);
        textViewWeight = (TextView) findViewById(R.id.weight);
        textViewSex = (TextView) findViewById(R.id.sex);
        textViewName = (TextView) findViewById(R.id.p_name);

        HBEAT = bundle.getString("HBEAT");
        AGE = bundle.getString("AGE");
        textViewAge.setText(AGE);
        WEIGHT = bundle.getString("WEIGHT");
        textViewWeight.setText(WEIGHT);
        HEIGHT = bundle.getString("HEIGHT");
        textViewHeight.setText(HEIGHT);
        SEX = bundle.getString("SEX");
        textViewSex.setText(SEX);
        BMI_string = bundle.getString("BMI_string");
        BMI_double = Double.parseDouble(BMI_string);
        textViewBMI.setText(BMI_string);
        uNAME = bundle.getString("uName_string");
        textViewName.setText(uNAME);

        ShowGenBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowGenBmi.setScaleType(ImageView.ScaleType.FIT_XY);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
        });

        BMR_string_afterMulti2 = bundle.getString("BMR_string_afterMulti");
        //textViewBMR_beforeMulti.setText(BMR_string_afterMulti2);

        BMR_string_afterMulti = bundle.getString("BMR_string_afterMulti");
        BMR_double_afterMulti = Double.parseDouble(BMR_string_afterMulti);
        //textViewBMR_afterMulti.setText(BMR_string_afterMulti + " kcal");
        /*
        i_string = bundle.getString("i_string");
        textViewBMR_afterMulti.setText("i_string = " + i_string);
        */


        if (BMI_double >= 30) {
            GetInfo = 5;
            textViewBMIshowText.setText("ิBmiFat3");
            genBMI.setImageDrawable(getResources().getDrawable(R.drawable.bmi5));


        } else if ((BMI_double >= 25) && (BMI_double <= 29.99)) {
            GetInfo = 4;
            textViewBMIshowText.setText("BmiFat2");
            genBMI.setImageDrawable(getResources().getDrawable(R.drawable.bmi4));

        } else if ((BMI_double >= 23) && (BMI_double <= 24.99)) {
            GetInfo = 3;
            textViewBMIshowText.setText("BmiFat1");
            genBMI.setImageDrawable(getResources().getDrawable(R.drawable.bmi3));

        } else if ((BMI_double >= 18.50) && (BMI_double <= 22.99)) {
            GetInfo = 2;
            textViewBMIshowText.setText("BmiMid");
            genBMI.setImageDrawable(getResources().getDrawable(R.drawable.bmi2));

        } else {
            GetInfo = 1;
            textViewBMIshowText.setText("BmiThin");
            genBMI.setImageDrawable(getResources().getDrawable(R.drawable.bmi1));
        }

        if (BMI_double >= 23) {
            BMR_double_afterMulti = BMR_double_afterMulti - 200;
            BMR_string_afterMulti = Double.toString(BMR_double_afterMulti);
            textViewBMR_afterMulti.setText(BMR_string_afterMulti);
        } else if ((BMI_double >= 18) && (BMI_double < 22.99)) {
            BMR_string_afterMulti = Double.toString(BMR_double_afterMulti);
            textViewBMR_afterMulti.setText(BMR_string_afterMulti);
        } else {
            BMR_double_afterMulti = BMR_double_afterMulti + 200;
            BMR_string_afterMulti = Double.toString(BMR_double_afterMulti);
            textViewBMR_afterMulti.setText(BMR_string_afterMulti);
        }

        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getinfo = new Intent(SubActivity.this, MoreInfo.class);
                getinfo.putExtra("GetInfo", GetInfo);
                startActivity(getinfo);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(SubActivity.this);
                builder.setTitle(getString(R.string.add_data_title));
                builder.setMessage(getString(R.string.add_data_message));

                AlertDialog.Builder keyUse = builder.setPositiveButton(getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                                editor = sp.edit();
                                editor.putString("KeyUse", "1");
                                editor.commit();
                                PersonalData person = new PersonalData();
                                person.setpID(pID);
                                person.setpUNAME(textViewName.getText().toString());
                                person.setSEX(textViewSex.getText().toString());
                                person.setAGE(textViewAge.getText().toString());
                                person.setWEIGTH(textViewWeight.getText().toString());
                                person.setHEIGTH(textViewHeight.getText().toString());
                                person.setBMI(textViewBMI.getText().toString());
                                //person.setBMR(textViewBMR_beforeMulti.getText().toString());
                                person.setBMRa(textViewBMR_afterMulti.getText().toString());
                                person.setTextBMI(textViewBMIshowText.getText().toString());
                                person.setH_BEAT(HBEAT);

                                if (ID == -1) {
                                    mHelper.addFriend(person);
                                } else {
                                    person.setpID(ID);
                                    //mHelper.updateFriend(person);
                                }
                                finish();
                                Intent detail = new Intent(SubActivity.this, FristPage.class);

                                startActivity(detail);
                                overridePendingTransition(android.R.anim.fade_in,
                                        android.R.anim.fade_out);

                            }
                        });


                builder.show();builder.setNegativeButton(getString(android.R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
            }
        });
    }
}
