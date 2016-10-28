package com.example.kaow.caltest;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;


public class Drawer extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        //Toolbar mToolbar = (Toolbar) findViewById(android.R.id.toggle);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),toolbar);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        /*fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();*/

        switch (position){
            case 0:
                mTitle = "อาหารทั้งหมด";
                AllFoodFragment allFood = new AllFoodFragment();
                fragmentManager.beginTransaction()
                .replace(R.id.container,allFood)
                .commit();
                break;
            case 1:
                mTitle = "อาหารเช้า";
                BreakfastFragment breakfast = new BreakfastFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, breakfast)
                        .commit();
                break;
            case 2:
                mTitle = "อาหารกลางวัน";
                LunchFragment lunch = new LunchFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, lunch)
                        .commit();
                break;
            case 3:
                mTitle = "อาหารเย็น";
                DinnerFragment dinner = new DinnerFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, dinner)
                        .commit();
                break;
            case 4:
                mTitle = "แนะนำสำหรับผู้ที่มีน้ำหนักเกินเกณฑ์";
                DownFoodFragment downFood = new DownFoodFragment();
                fragmentManager.beginTransaction()
                    .replace(R.id.container, downFood)
                    .commit();
            break;
            case 5:
                mTitle = "แนะนำสำหรับผู้ที่มีน้ำหนักต่ำกว่าเกณฑ์";
                UpFoodFragment upFood = new UpFoodFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, upFood)
                        .commit();
                break;
            case 6:
                mTitle = "อาหารจานหลัก";
                FoodMainFragment mainFood = new FoodMainFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mainFood)
                        .commit();
                break;
            case 7:
                mTitle = "อาหารจานด่วน";
                FoodFastFragment fastFood = new FoodFastFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fastFood)
                        .commit();
                break;
            case 8:
                mTitle = "อาหารว่าง";
                AppetizerFragment appFood = new AppetizerFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, appFood)
                        .commit();
                break;
            case 9:
                mTitle = "ผัก";
                VegetableFragment vet = new VegetableFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, vet)
                        .commit();
                break;
            case 10:
                mTitle = "ผลไม้";
                FruitFragment fruit = new FruitFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fruit)
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = "อาหารทั้งหมด";
                break;
            case 2:
                mTitle = "อาหารเช้า";
                break;
            case 3:
                mTitle = "อาหารกลางวัน";
                break;
            case 4:
                mTitle = "อาหารเย็น";
                break;
            case 5:
                mTitle = "แนะนำสำหรับผู้ที่มีน้ำหนักเกินเกณฑ์";
                break;
            case 6:
                mTitle = "แนะนำสำหรับผู้ที่มีน้ำหนักต่ำกว่าเกณฑ์" ;
                break;
            case 7:
                mTitle = "อาหารจานหลัก";
                break;
            case 8:
                mTitle = "อาหารจานด่วน";
                break;
            case 9:
                mTitle = "ของว่าง";
                break;
            case 10:
                mTitle = "ผัก";
                break;
            case 11:
                mTitle = "ผลไม้";
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.drawer, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Drawer) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
