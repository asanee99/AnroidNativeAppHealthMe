package com.example.kaow.caltest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

	private EditText uName;
	private String uName_string;

	// Sex
	private RadioButton radioWomen, radioMen;
	private boolean boolWomen, boolMen;
	private int numFromBoolRadio_int;

	// Age
	private EditText ageEditText;
	private String age_string = " ";
	private double age_double;

	// Weight
	private EditText weightEditText;
	private String weight_string = " ";
	private double weight_double;

	// Height
	private EditText heightEditText;
	private String height_string = " ";
	private double height_double;

	// Spinner
	private Spinner spinnerFood;
	private List<String> arrList = new ArrayList<String>();
	private int i_int;
	private String i_string;

	// Button
	private Button buttonIntroduce;

	// Context
	private Context context;

	// BMI
	private double BMI_double;
	private String BMI_string;

	// BMR
	private double BMR_double_beforeMulti;
	private double BMR_double_afterMulti;
	private String BMR_string_beforeMulti;
	private String BMR_string_afterMulti;

	private String SEX;
	private double Hbeat_double;
	private String Hbeat_string;


	ProgressDialog dialog;
	boolean cancel;
	private String id = "1";
	MyDbHelper dbHelper;
	PersonalData mPerson;
	String CODE;

	PendingIntent pendingIntent;

	public static final String MyPREFERENCES = "MyPrefs" ;
	SharedPreferences sp;
	@Override
	protected void onResume() {
		super.onResume();
		sp = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);  // ดึง share preference ชื่อ MyPrefs เก็บไว้ในตัวแปร sharedpreferences
		if (sp.contains("KeyUse")) {
				Intent i = new Intent(this, FristPage.class);
				startActivity(i);
			}
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR_OF_DAY, 7);
		calendar.set(Calendar.MINUTE,30);
		//calendar.set(Calendar.SECOND, 0);
		//calendar.set(Calendar.AM_PM, Calendar.AM);

        Calendar calendar2 = Calendar.getInstance();

        calendar2.set(Calendar.HOUR_OF_DAY, 11);
        calendar2.set(Calendar.MINUTE, 0);
        //calendar2.set(Calendar.SECOND, 0);
        //calendar2.set(Calendar.AM_PM, Calendar.PM);

        Calendar calendar3 = Calendar.getInstance();

        calendar3.set(Calendar.HOUR_OF_DAY, 16);
        calendar3.set(Calendar.MINUTE, 30);
        //calendar3.set(Calendar.SECOND, 0);
        //calendar3.set(Calendar.AM_PM,Calendar.PM);


		Intent myIntent = new Intent(this, MyReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent,0);
		AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
		//alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


			uName = (EditText) findViewById(R.id.username);
			radioWomen = (RadioButton) findViewById(R.id.rbtWomen);
			radioMen = (RadioButton) findViewById(R.id.rbtMen);
			ageEditText = (EditText) findViewById(R.id.ageET);
			weightEditText = (EditText) findViewById(R.id.weightET);
			heightEditText = (EditText) findViewById(R.id.heightET);
			spinnerFood = (Spinner) findViewById(R.id.spinnerFoodID);
		ImageView man = (ImageView) findViewById(R.id.man);
		ImageView women = (ImageView) findViewById(R.id.women);

		Picasso.with(this).load(R.drawable.man)
				.resize(100,100)
				.placeholder(R.drawable.ic_launcher)
				.into(man);
		Picasso.with(this).load(R.drawable.women)
				.resize(100, 100)
				.placeholder(R.drawable.ic_launcher)
				.into(women);

		/*
		arrList.add("ทำงานอยู่กับที่ และไม่ออกกำลังกาย");
		arrList.add("ออกกำลังกายอาทิตย์ละ 1-3 วัน");
		arrList.add("ออกกำลังกายอาทิตย์ละ 3-5 วัน");
		arrList.add("ออกกำลังกายอาทิตย์ละ 6-7 วัน");
		arrList.add("ออกกำลังกายทุกวัน");*/
			ActivityList countries[] = new ActivityList[]{
					new ActivityList(R.drawable.never , "ทำงานอยู่กับที่ และไม่ออกกำลังกาย"),
					new ActivityList(R.drawable.week1, "ออกกำลังกายอาทิตย์ละ 1-3 วัน"),
					new ActivityList(R.drawable.week2, "ออกกำลังกายอาทิตย์ละ 3-5 วัน"),
					new ActivityList(R.drawable.week4, "ออกกำลังกายอาทิตย์ละ 6-7 วัน"),
			};

			ActivityAdapter arrAdapter = new ActivityAdapter(this, R.layout.spinitem, countries);

			spinnerFood.setAdapter(arrAdapter);

			spinnerFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
					// TODO Auto-generated method stub
					i_int = i;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}
			});

			buttonIntroduce = (Button) findViewById(R.id.btnIntroduce);

			buttonIntroduce.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					//------------- Radio Button -------------
					boolWomen = radioWomen.isChecked();
					boolMen = radioMen.isChecked();

					if ((boolWomen == true) && (boolMen == false)) {
						numFromBoolRadio_int = 1; // 1 = Women


					} else if ((boolWomen == false) && (boolMen == true)) {
						numFromBoolRadio_int = 2; // 2 = Men

					}
					uName_string = uName.getText().toString();
					age_string = ageEditText.getText().toString();
					weight_string = weightEditText.getText().toString();
					height_string = heightEditText.getText().toString();

					if ((age_string.trim().length() == 0) || (weight_string.trim().length() == 0) || (height_string.trim().length() == 0)) {
						Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
					} else {

						String title = "กำลังคำนวณข้อมูล";
						String msg = "กรุณารอสักครู่";
						dialog = ProgressDialog.show(MainActivity.this, title, msg);

						age_double = Double.parseDouble(age_string);
						weight_double = Double.parseDouble(weight_string);
						height_double = Double.parseDouble(height_string);

						//age_string = Double.toString(age_double);
						if (numFromBoolRadio_int == 1) {
							BMR_double_beforeMulti = (665 + (9.6 * weight_double) + (1.8 * height_double)) - (4.7 * age_double);
							SEX = "หญิง";
						} else if (numFromBoolRadio_int == 2) {
							BMR_double_beforeMulti = (66 + (13.7 * weight_double) + (5 * height_double)) - (6.8 * age_double);
							SEX = "ชาย";
						}
						//------------------------------------------
						Hbeat_double = (220 - age_double);
						Hbeat_string = Double.toString(Hbeat_double);
						//------------- BMR Calculate 2 ------------
						if (i_int == 0) {
							BMR_double_afterMulti = BMR_double_beforeMulti * 1.2;
						} else if (i_int == 1) {
							BMR_double_afterMulti = BMR_double_beforeMulti * 1.375;
						} else if (i_int == 2) {
							BMR_double_afterMulti = BMR_double_beforeMulti * 1.55;
						} else if (i_int == 3) {
							BMR_double_afterMulti = BMR_double_beforeMulti * 1.725;
						} else if (i_int == 4) {
							BMR_double_afterMulti = BMR_double_beforeMulti * 1.9;
						} else {
							BMR_double_afterMulti = BMR_double_beforeMulti * 1;
						}
						//-----------------------------------------

						//------------- BMI Calculate --------------
						BMI_double = (weight_double) / ((height_double / 100) * (height_double / 100));
						BMI_string = Double.toString(BMI_double);
						//------------------------------------------

						//---- BMI Convert by Use DecimalFormat ----
						DecimalFormat decf = new DecimalFormat("#.00");
						BMI_string = decf.format(BMI_double);
						//------------------------------------------

						//---------- BMR Convert Data Type ---------
						BMR_string_beforeMulti = Double.toString(BMR_double_beforeMulti);
						BMR_string_afterMulti = Double.toString(BMR_double_afterMulti);
						//------------------------------------------

						//---- BMR Convert by Use DecimalFormat ----
						BMR_string_beforeMulti = decf.format(BMR_double_beforeMulti);
						BMR_string_afterMulti = decf.format(BMR_double_afterMulti);
						//------------------------------------------

						Intent intent = new Intent(MainActivity.this, SubActivity.class);
						intent.putExtra("CODE", CODE);
						intent.putExtra("BMI_string", BMI_string);
						intent.putExtra("BMR_string_afterMulti", BMR_string_afterMulti);
						intent.putExtra("AGE", age_string);
						intent.putExtra("SEX", SEX);
						intent.putExtra("WEIGHT", weight_string);
						intent.putExtra("HEIGHT", height_string);
						intent.putExtra("uName_string", uName_string);
						intent.putExtra("HBEAT", Hbeat_string);
					/*
						i_string = Integer.toString(i_int);
						intent.putExtra("i_string", i_string);
					*/
						startActivity(intent);
						overridePendingTransition(android.R.anim.fade_in,
								android.R.anim.fade_out);
					}
				}
			});
	}
}
