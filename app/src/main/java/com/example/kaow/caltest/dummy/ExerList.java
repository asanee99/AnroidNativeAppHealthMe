package com.example.kaow.caltest.dummy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaow.caltest.R;

import java.util.ArrayList;

public class ExerList extends Activity {

    ListView exerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exer_list);

        exerList = (ListView) findViewById(R.id.exer_list);

        String[] exer = new String[]{
                "1. การเดิน",
                "2. การวิ่ง",
                "3. การปั่นจักรยาน",
                "4. เล่นฟุตบอล",
                "5. ว่ายน้ำ",
                "6. เต้นแอโรบิก",
                "7. ทำงานบ้าน",

        };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < exer.length; ++i) {
            list.add(exer[i]);
            ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, list);
            exerList.setAdapter(adapter);
        }

        exerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent gotoCalcu = new Intent(ExerList.this, CalExer.class);
               gotoCalcu.putExtra("pos", position);
               startActivity(gotoCalcu);
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exer_list, menu);
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
