package com.example.kaow.caltest;

import android.provider.BaseColumns;

/**
 * Created by Kaow on 7/5/15 AD.
 */
public class PersonalData {
    //Database
    public static final String DATABASE_NAME = "myPersonData.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "PersonData";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String COL_USER = "pUser";
        public static final String COL_PASS =  "pPass";
        public static final String COL_SEX = "pSex";
        public static final String COL_AGE = "pAge";
        public static final String COL_WEIGTH = "pWeight";
        public static final String COL_HEIGHT = "pHeight";
        public static final String COL_BMI = "pBmi";
        public static final String COL_BMR = "pBmr";
        public static final String COL_BMRa = "pBmrA";
        public static final String COL_TEXT = "pText";
        public static final String COL_HBEAT = "pHeart";

    }
    private int pID;
    private String pUNAME;
    private String SEX;
    private String pPASS;
    private String AGE;
    private String WEIGTH;
    private String HEIGTH;
    private String BMI;
    private String textBMI;
    private String BMR;
    private String BMRa;
    private String H_BEAT;
    public PersonalData() {

    }


    //Constructor
    public PersonalData (int id, String username, String password, String sex, String age, String weight, String height,
                         String bmi,String tbmi, String bmr, String bmra, String heart) {

        this.pID = id;
        this.pUNAME = username;
        this.pPASS = password;
        this.SEX = sex;
        this.AGE = age;
        this.WEIGTH = weight;
        this.HEIGTH = height;
        this.BMI = bmi;
        this.textBMI = tbmi;
        this.BMR = bmr;
        this.BMRa = bmra;
        this.H_BEAT = heart;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public String getpUNAME() {
        return pUNAME;
    }

    public void setpUNAME(String pUNAME) {
        this.pUNAME = pUNAME;
    }

    public String getpPASS() {
        return pPASS;
    }

    public void setpPASS(String pPASS) {
        this.pPASS = pPASS;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getWEIGTH() {
        return WEIGTH;
    }

    public void setWEIGTH(String WEIGTH) {
        this.WEIGTH = WEIGTH;
    }

    public String getHEIGTH() {
        return HEIGTH;
    }

    public void setHEIGTH(String HEIGTH) {
        this.HEIGTH = HEIGTH;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getTextBMI() {
        return textBMI;
    }

    public void setTextBMI(String textBMI) {
        this.textBMI = textBMI;
    }

    public String getBMR() {
        return BMR;
    }

    public void setBMR(String BMR) {
        this.BMR = BMR;
    }

    public String getBMRa() {
        return BMRa;
    }

    public void setBMRa(String BMRa) {
        this.BMRa = BMRa;
    }

    public String getH_BEAT() {
        return H_BEAT;
    }

    public void setH_BEAT(String h_BEAT) {
        H_BEAT = h_BEAT;
    }
}
