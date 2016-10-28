package com.example.kaow.caltest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;

import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;


public class Exercise_Recly extends Activity {
    RecyclerView rv;
    LinearLayoutManager llm;
    private List<Exercise> exers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_recly);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        Button exerInfo = (Button) findViewById(R.id.exer_info);
        initializeData();
        initializeAdapter();
    }

    private void initializeData() {
        exers = new ArrayList<>();
        exers.add(new Exercise("การเดิน", R.drawable.walking));
        exers.add(new Exercise("การวิ่ง", R.drawable.run));
        exers.add(new Exercise("การกระโดดเชือก", R.drawable.jumprode));
        exers.add(new Exercise("แอโรบิกดานซ์", R.drawable.aerobics));
        exers.add(new Exercise("รำมวยจีน", R.drawable.taichi2));
        exers.add(new Exercise("ขี่จักรยาน", R.drawable.biking));

    }

    private void initializeAdapter(){

        EXAdapter adapter = new EXAdapter(exers);
        rv.setAdapter(adapter);
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
