package com.example.kaow.caltest;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Created by Kaow on 7/4/15 AD.
 */
public class Show_p_data extends Activity  {

    TextView pID;
    TextView showSEX;
    TextView showAGE;
    TextView showWEIGHT;
    TextView showHEIGHT;
    TextView showTextBMI;
    TextView pNAME;
    ImageView showbmi;
    TextView showBMI;
    TextView showBMR;
    TextView showBmrA;
    Button btnReset;
    Button btnMoreInfo;
    int GetInfo;
    ListView list;
    MyDbHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter adapter;
    PersonalData mPerson;
    private String id = "1";
    private ProgressDialog dialog;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_p_data);
        //final ActionBar actionBar = getActionBar();

        pID = (TextView) findViewById(R.id.pID);
        showSEX = (TextView) findViewById(R.id.show_sex);
        showAGE = (TextView) findViewById(R.id.show_age);
        showWEIGHT = (TextView) findViewById(R.id.show_weight);
        showHEIGHT = (TextView) findViewById(R.id.show_height);
        showTextBMI = (TextView) findViewById(R.id.show_TextBmi);
        showBMI = (TextView) findViewById(R.id.show_BMI);
        showBMR = (TextView) findViewById(R.id.show_BMR);
        //showBmrA = (TextView) findViewById(R.id.show_BMRa);
        list = (ListView) findViewById(R.id.list);
        btnReset = (Button) findViewById(R.id.btnResetCal);
        pNAME = (TextView) findViewById(R.id.Uname);
        showbmi = (ImageView) findViewById(R.id.showBmi);
        btnMoreInfo = (Button) findViewById(R.id.moreInfo2);
        //list.setOnItemClickListener(this);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        dbHelper = new MyDbHelper(this);

        mPerson = dbHelper.getPerson(id);

        pNAME.setText(mPerson.getpUNAME());
        showSEX.setText(mPerson.getSEX());
        showAGE.setText(mPerson.getAGE());
        showWEIGHT.setText(mPerson.getWEIGTH());
        showHEIGHT.setText(mPerson.getHEIGTH());
        showTextBMI.setText(mPerson.getTextBMI());
        showBMI.setText(mPerson.getBMI());
        showBMR.setText(mPerson.getBMR());
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
                Intent i = new Intent(Show_p_data.this, MoreInfo.class);
                i.putExtra("GetInfo", GetInfo);
                startActivity(i);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(Show_p_data.this);
                builder.setTitle(getString(R.string.change_data_title));
                builder.setMessage(getString(R.string.change_data_message));

                builder.setPositiveButton(getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sharedpreferences = getSharedPreferences
                                        (MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);  // ดึง share preference ชื่อ MyPrefs เก็บไว้ในตัวแปร sharedpreferences
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.clear();  // ทำการลบข้อมูลทั้งหมดจาก preferences
                                editor.commit();
                                String title = "กำลังคำนวณข้อมูล";
                                String msg = "กรุณารอสักครู่";
                                dialog = ProgressDialog.show(Show_p_data.this, title, msg);
                                dbHelper.deleteFriend(id);
                                Intent change = new Intent(Show_p_data.this, MainActivity.class);
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
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, FristPage.class);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
