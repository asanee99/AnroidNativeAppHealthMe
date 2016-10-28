package com.example.kaow.caltest.dummy;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kaow.caltest.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import me.grantland.widget.AutofitTextView;

public class CalExer extends Activity {

    TextView exerName;
    ImageView imgExer;
    int pos;
    Spinner exerChoose;
    EditText nuM;
    Button submit;
    AutofitTextView txtResult;
    TextView numResult;
    TextView txtDetail;
    Button exerChoose2;
    String exerText;
    TextView exerTopic;
    TextView numDetail;
    double exerBurn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_exer);
        exerName = (TextView) findViewById(R.id.exer_header);
        imgExer = (ImageView) findViewById(R.id.imgExer);
       // exerChoose = (Spinner) findViewById(R.id.txt02);
        nuM = (EditText) findViewById(R.id.num01);
        submit = (Button) findViewById(R.id.submit);
        txtResult = (AutofitTextView) findViewById(R.id.txtResult);
        numResult = (TextView) findViewById(R.id.numResult);
        txtDetail = (TextView) findViewById(R.id.txtDetail);
        exerChoose2 = (Button) findViewById(R.id.choose_2);
        exerTopic = (TextView) findViewById(R.id.exer_topic);
        numDetail = (TextView) findViewById(R.id.numDetail);

        Bundle bundle = getIntent().getExtras();
        pos = bundle.getInt("pos");

        if (pos >= 6) {
            exerBurn = 2.2;
            exerName.setText("ทำงานบ้าน");
            Picasso.with(this).load(R.drawable.housework)
                    .placeholder(R.drawable.ic_launcher)
                    .into(imgExer);
        } else if (pos >= 5) {
            exerBurn = 9.6;
            exerName.setText("เต้นแอโรบิก");
            Picasso.with(this).load(R.drawable.aerobics)
                    .placeholder(R.drawable.ic_launcher)
                    .into(imgExer);
        } else if (pos >= 4) {
            exerBurn = 3.5;
            exerName.setText("ว่ายน้ำ");
            Picasso.with(this).load(R.drawable.swim)
                    .placeholder(R.drawable.ic_launcher)
                    .into(imgExer);
        } else if (pos >= 3) {
            exerBurn = 6.0;
            exerName.setText("เล่นฟุตบอล");
            Picasso.with(this).load(R.drawable.football)
                    .placeholder(R.drawable.ic_launcher)
                    .into(imgExer);
        } else if (pos >= 2) {
            exerBurn = 3.6;
            exerName.setText("การปั่นจักรยาน");
            Picasso.with(this).load(R.drawable.biking)
                    .placeholder(R.drawable.ic_launcher)
                    .into(imgExer);
        } else if (pos >= 1) {
            exerBurn = 10.3;
            exerName.setText("การวิ่ง");
            Picasso.with(this).load(R.drawable.run)
                    .placeholder(R.drawable.ic_launcher)
                    .into(imgExer);
        } else {
            exerBurn = 4.5;
            exerName.setText("การเดิน");
            Picasso.with(this).load(R.drawable.walking)
                    .placeholder(R.drawable.ic_launcher)
                    .into(imgExer);
        }

        exerChoose2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] items = {"เวลาที่จะใช้ในการออกกำลังกาย(นาที)", "ปริมาณแคลอรี่ที่ต้องการเผาผลาญ(กิโลแคลอรี่)"};
                new AlertDialog.Builder(CalExer.this)
                .setTitle("เลือกข้อมูลที่ต้องการคำนวณ")
                .setSingleChoiceItems(items, 1,null)
                .setPositiveButton("เลือก", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                            ListView lv = ((AlertDialog) dialog).getListView();
                            int selectItem = lv.getCheckedItemPosition();
                            exerText = items[selectItem];
                            exerTopic.setText(exerText);
                        if (exerText.trim().equals("เวลาที่จะใช้ในการออกกำลังกาย(นาที)")) {
                            numDetail.setText(" นาที");
                        } else if (exerText.trim().equals("ปริมาณแคลอรี่ที่ต้องการเผาผลาญ(กิโลแคลอรี่)")) {
                            numDetail.setText(" KCAL");
                        }
                            exerTopic.setVisibility(View.VISIBLE);
                            nuM.setVisibility(View.VISIBLE);
                            numDetail.setVisibility(View.VISIBLE);
                            submit.setVisibility(View.VISIBLE);
                    }
                }).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                //String selectedItem = (String) exerChoose.getSelectedItem();

                if (exerText.trim().equals("เวลาที่จะใช้ในการออกกำลังกาย(นาที)")) {

                    double result = Double.parseDouble(nuM.getText().toString()) * exerBurn;
                    txtResult.setText("ปริมาณแคลอรี่เผาผลาญ คือ");

                    DecimalFormat decf = new DecimalFormat("#.00");
                    String Result_String = decf.format(result);
                    numResult.setText(Result_String);
                    txtDetail.setText(" KCAL");
                    numResult.setVisibility(View.VISIBLE);
                    txtResult.setVisibility(View.VISIBLE);
                    txtDetail.setVisibility(View.VISIBLE);
                } else if (exerText.trim().equals("ปริมาณแคลอรี่ที่ต้องการเผาผลาญ(กิโลแคลอรี่)")) {

                    nuM.setVisibility(View.VISIBLE);
                    numDetail.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    double result = Double.parseDouble(nuM.getText().toString()) / exerBurn;
                    txtResult.setText("เวลาที่ใช้ในการเผาผลาญ คือ");
                    DecimalFormat decf = new DecimalFormat("#.00");
                    String Result_String = decf.format(result);
                    txtDetail.setText(" นาที");
                    numResult.setText(Result_String);
                    numResult.setVisibility(View.VISIBLE);
                    txtResult.setVisibility(View.VISIBLE);
                    txtDetail.setVisibility(View.VISIBLE);
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cal_exer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
