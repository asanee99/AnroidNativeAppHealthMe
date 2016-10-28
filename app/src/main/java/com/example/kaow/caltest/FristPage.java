package com.example.kaow.caltest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Kaow on 7/6/15 AD.Button goMain;
 */
public class FristPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.firstpage);

        /*final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);*/

        // Specify that we will be displaying tabs in the action bar.

        Button goMain = (Button) findViewById(R.id.gotoMain);
       // Button goView = (Button) findViewById(R.id.gotoView);
        Button goNews = (Button) findViewById(R.id.gotoNews);
        Button goReset = (Button) findViewById(R.id.gotoReset);
        Button goExer = (Button)  findViewById(R.id.gotoExer);
        Button goDev = (Button) findViewById(R.id.gotoDev);

        goMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FristPage.this, MainFrag.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });

        goNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FristPage.this, ShowNews.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
        goReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FristPage.this, UserInfo.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
        goExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FristPage.this, Exercise_Recly.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
        goDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FristPage.this, Develop.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
    }
}
