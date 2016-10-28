package com.example.kaow.caltest;

import android.provider.BaseColumns;

/**
 * Created by Kaow on 7/1/15 AD.
 */
public class Foods {
    public static final String DATABASE_NAME = "myFood.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "Fooddata";

    public class fColumn {
        public static final String ID = BaseColumns._ID;
        public static final String COL_FNAME = "fName";
        public static final String COL_FCAL =  "fCal";
        public static final String COL_FTYPE1 = "fType1";
        public static final String COL_FTYPE2 = "fType2";

    }
    private int fId;
    private String foodname;
    private String foodcal;
    private String foodtype1;
    private String foodtype2;
    private Float totalcal;
    private String picurl;

    public Foods(){

    }

    public Foods(int fId,String foodname, String foodcal, String foodtype1, String foodtype2, String picurl) {
        super();
        this.fId = fId;
        this.foodname = foodname;
        this.foodcal = foodcal;
        this.foodtype1 = foodtype1;
        this.foodtype2 = foodtype2;
        this.picurl = picurl;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodcal() {
        return foodcal;
    }

    public void setFoodcal(String foodcal) {
        this.foodcal = foodcal;
    }

    public String getFoodtype1() {
        return foodtype1;
    }

    public void setFoodtype1(String foodtype1) {
        this.foodtype1 = foodtype1;
    }

    public String getFoodtype2() {
        return foodtype2;
    }

    public void setFoodtype2(String foodtype2) {
        this.foodtype2 = foodtype2;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public Float getTotalcal() {
        return totalcal;
    }

    public void setTotalcal(Float totalcal) {
        this.totalcal = totalcal;
    }
}