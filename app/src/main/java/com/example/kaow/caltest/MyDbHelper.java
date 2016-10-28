package com.example.kaow.caltest;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.kaow.caltest.PersonalData;
public class MyDbHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public MyDbHelper(Context context) {
        super(context, PersonalData.DATABASE_NAME, null, PersonalData.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PERSON_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY , %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s TEXT, %s REAL)",
                PersonalData.TABLE,
                PersonalData.Column.ID,
                PersonalData.Column.COL_USER,
                PersonalData.Column.COL_PASS,
                PersonalData.Column.COL_SEX,
                PersonalData.Column.COL_AGE,
                PersonalData.Column.COL_WEIGTH,
                PersonalData.Column.COL_HEIGHT,
                PersonalData.Column.COL_BMI,
                PersonalData.Column.COL_BMR,
                PersonalData.Column.COL_BMRa,
                PersonalData.Column.COL_TEXT,
                PersonalData.Column.COL_HBEAT);

        Log.i(TAG, CREATE_PERSON_TABLE);

        // create friend table
        db.execSQL(CREATE_PERSON_TABLE);
    }

        //You may also load initial values into the database here
        /*ContentValues cv = new ContentValues();

        cv.put(COL_SEX, "ชาย");
        cv.put(COL_AGE, "00.00");
        cv.put(COL_WEIGTH, "00.00");
        cv.put(COL_HEIGHT, "00.00");
        cv.put(COL_BMI, "00.00");
        cv.put(COL_BMR, "00.00");
        cv.put(COL_BMRa, "00.00");
        cv.put(COL_TEXT, "");


        //Create a formatter for SQL date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cv.put(COL_DATE, dateFormat.format(new Date())); //Insert 'now' as the date

        db.insert(TABLE_NAME, null, cv);*/


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + PersonalData.TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    public void deleteAllRow() {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(PersonalData.TABLE, null, null);
        sqLiteDatabase.close();

    }

    //CRUD ( CREATE, READ, UPDATE, DELETE )

    //CREATE
    public void addFriend(PersonalData person) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(Friend.Column.ID, friend.getId());
        values.put(PersonalData.Column.COL_USER, person.getpUNAME());
        values.put(PersonalData.Column.COL_PASS, person.getpPASS());
        values.put(PersonalData.Column.COL_SEX, person.getSEX());
        values.put(PersonalData.Column.COL_AGE, person.getAGE());
        values.put(PersonalData.Column.COL_WEIGTH, person.getWEIGTH());
        values.put(PersonalData.Column.COL_HEIGHT, person.getHEIGTH());
        values.put(PersonalData.Column.COL_BMI, person.getBMI());
        values.put(PersonalData.Column.COL_BMR, person.getBMR());
        values.put(PersonalData.Column.COL_BMRa, person.getBMRa());
        values.put(PersonalData.Column.COL_TEXT, person.getTextBMI());
        values.put(PersonalData.Column.COL_HBEAT, person.getH_BEAT());

        sqLiteDatabase.insert(PersonalData.TABLE, null, values);

        sqLiteDatabase.close();
    }



    //READ
    public PersonalData getPerson(String id) {

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(PersonalData.TABLE,
                null,
                PersonalData.Column.ID + " = ? ",
                new String[] { id },
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        PersonalData person = new PersonalData();
        person.setpID((int) cursor.getLong(0));
        person.setpUNAME(cursor.getString(1));
        person.setSEX(cursor.getString(3));
        person.setAGE(cursor.getString(4));
        person.setWEIGTH(cursor.getString(5));
        person.setHEIGTH(cursor.getString(6));
        person.setBMI(cursor.getString(7));
        person.setBMR(cursor.getString(8));
        person.setBMRa(cursor.getString(9));
        person.setTextBMI(cursor.getString(10));
        person.setH_BEAT(cursor.getString(11));

        return person;
    }

    public Cursor checkDB(){
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(PersonalData.TABLE,
                null,
                PersonalData.Column.ID + " = ? ",
                new String[] { "1" },
                null,
                null,
                null,
                null);

        return cursor;
    }


    public List<String> getFriendList() {
        List<String> persons = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (PersonalData.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {

            persons.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2));

            cursor.moveToNext();
        }
        sqLiteDatabase.close();

        return persons;
    }

    /**
     * Call when need List<Friend> instead of List<String>
     * @return List of friends.
     */
    public List<PersonalData> getAllFriend() {

        String QUERY_ALL_FRIEND = "SELECT * FROM " + PersonalData.TABLE;

        List<PersonalData> persons = new ArrayList<PersonalData>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_ALL_FRIEND, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            PersonalData person = new PersonalData();

            person.setpID((int) cursor.getLong(0));
            person.setSEX(cursor.getString(3));
            person.setAGE(cursor.getString(4));
            person.setWEIGTH(cursor.getString(5));
            person.setHEIGTH(cursor.getString(6));
            person.setBMI(cursor.getString(7));
            person.setBMR(cursor.getString(8));
            person.setBMRa(cursor.getString(9));
            person.setTextBMI(cursor.getString(10));

            persons.add(person);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();


        return persons;
    }


    //UPDATE
    public void updateFriend(PersonalData person) {

        sqLiteDatabase  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonalData.Column.COL_USER, person.getpUNAME());
        values.put(PersonalData.Column.COL_PASS, person.getpPASS());
        values.put(PersonalData.Column.COL_SEX, person.getSEX());
        values.put(PersonalData.Column.COL_AGE, person.getAGE());
        values.put(PersonalData.Column.COL_WEIGTH, person.getWEIGTH());
        values.put(PersonalData.Column.COL_HEIGHT, person.getHEIGTH());
        values.put(PersonalData.Column.COL_BMI, person.getBMI());
        values.put(PersonalData.Column.COL_BMR, person.getBMR());
        values.put(PersonalData.Column.COL_BMRa, person.getBMRa());
        values.put(PersonalData.Column.COL_TEXT, person.getTextBMI());

        int row = sqLiteDatabase.update(PersonalData.TABLE,
                values,
                PersonalData.Column.ID + " = ? ",
                new String[] { String.valueOf(person.getpID()) });

        sqLiteDatabase.close();
    }

    //DELETE
    public void deleteFriend(String id) {

        sqLiteDatabase = this.getWritableDatabase();

/*        sqLiteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = ? ",
                new String[] { String.valueOf(friend.getId()) });*/
        sqLiteDatabase.delete(PersonalData.TABLE, PersonalData.Column.ID + " = " + id, null);

        sqLiteDatabase.close();
    }

}