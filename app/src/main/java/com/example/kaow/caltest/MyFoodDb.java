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
import com.example.kaow.caltest.Foods;
public class MyFoodDb extends SQLiteOpenHelper {


    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public MyFoodDb(Context context) {
        super(context, Foods.DATABASE_NAME, null, Foods.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FOOD_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s REAL, %s TEXT, %s TEXT)",
                Foods.TABLE,
                Foods.fColumn.ID,
                Foods.fColumn.COL_FNAME,
                Foods.fColumn.COL_FCAL,
                Foods.fColumn.COL_FTYPE1,
                Foods.fColumn.COL_FTYPE2);

        Log.i(TAG, CREATE_FOOD_TABLE);
        // create friend table
        db.execSQL(CREATE_FOOD_TABLE);

    }


    public  Cursor getBreakFast(){
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query
                (Foods.TABLE, null, Foods.fColumn.COL_FTYPE1 + " = ? ", new String[]{"Breakfast"}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()) {
            Foods foods = new Foods();

            foods.setfId((int) cursor.getLong(0));
            foods.setFoodname(cursor.getString(1));
            foods.setFoodcal(cursor.getString(2));
            foods.setFoodtype1(cursor.getString(3));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        return  cursor;
    }

    public  Cursor getLunch(){
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query
                (Foods.TABLE, null, Foods.fColumn.COL_FTYPE1 + " = ? ", new String[]{"Lunch"}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()) {
            Foods foods = new Foods();

            foods.setfId((int) cursor.getLong(0));
            foods.setFoodname(cursor.getString(1));
            foods.setFoodcal(cursor.getString(2));
            foods.setFoodtype1(cursor.getString(3));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        return  cursor;
    }

    public  Cursor getDinner(){
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query
                (Foods.TABLE, null, Foods.fColumn.COL_FTYPE1 + " = ? ", new String[]{"Dinner"}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()) {
            Foods foods = new Foods();

            foods.setfId((int) cursor.getLong(0));
            foods.setFoodname(cursor.getString(1));
            foods.setFoodcal(cursor.getString(2));
            foods.setFoodtype1(cursor.getString(3));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        return  cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + Foods.TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    public void deleteAllFoodRow() {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Foods.TABLE, null, null);
        sqLiteDatabase.close();

    }

    //CRUD ( CREATE, READ, UPDATE, DELETE )

    //CREATE
    public void addFoods(Foods foods) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(Friend.Column.ID, friend.getId());
        values.put(Foods.fColumn.COL_FNAME, foods.getFoodname());
        values.put(Foods.fColumn.COL_FCAL, foods.getFoodcal());
        values.put(Foods.fColumn.COL_FTYPE1, foods.getFoodtype1());
        values.put(Foods.fColumn.COL_FTYPE2, foods.getFoodtype2());

        sqLiteDatabase.insert(Foods.TABLE, null, values);

        sqLiteDatabase.close();
    }

    //READ
    public Foods getFoods(String id) {

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Foods.TABLE,
                null,
                Foods.fColumn.ID + " = ? ",
                new String[] { id },
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        Foods foods = new Foods();

        foods.setfId((int) cursor.getLong(0));
        foods.setFoodname(cursor.getString(1));
        foods.setFoodcal(cursor.getString(2));
        foods.setFoodtype1(cursor.getString(3));
        foods.setFoodtype2(cursor.getString(4));


        return foods;
    }

    public Foods getSumCal() {

        sqLiteDatabase = this.getReadableDatabase();
        String[] columns = new String[] {
                "sum(" + Foods.fColumn.COL_FCAL + ")"
        };

        Cursor cursor = sqLiteDatabase.query(Foods.TABLE,
                columns,
                null,
                null,
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        Foods foods = new Foods();

        foods.setTotalcal(cursor.getFloat(0));


        return foods;
    }

    public Foods getSumCalBreakfast() {

        sqLiteDatabase = this.getReadableDatabase();
        String[] columns = new String[] {
                "sum(" + Foods.fColumn.COL_FCAL + ")"
        };

        Cursor cursor = sqLiteDatabase.query(Foods.TABLE,
                columns,
                Foods.fColumn.COL_FTYPE1 + " = ? ",
                new String[]{"Breakfast"},
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        Foods foods = new Foods();

        foods.setTotalcal(cursor.getFloat(0));


        return foods;
    }

    public Foods getSumCalLunch() {

        sqLiteDatabase = this.getReadableDatabase();
        String[] columns = new String[] {
                "sum(" + Foods.fColumn.COL_FCAL + ")"
        };

        Cursor cursor = sqLiteDatabase.query(Foods.TABLE,
                columns,
                Foods.fColumn.COL_FTYPE1 + " = ? ",
                new String[]{"Lunch"},
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        Foods foods = new Foods();

        foods.setTotalcal(cursor.getFloat(0));


        return foods;
    }

    public Foods getSumCalDinner() {

        sqLiteDatabase = this.getReadableDatabase();
        String[] columns = new String[] {
                "sum(" + Foods.fColumn.COL_FCAL + ")"
        };

        Cursor cursor = sqLiteDatabase.query(Foods.TABLE,
                columns,
                Foods.fColumn.COL_FTYPE1 + " = ? ",
                new String[]{"Dinner"},
                null,
                null,
                null,
                null);


        if (cursor != null) {
            cursor.moveToFirst();
        }

        Foods foods = new Foods();

        foods.setTotalcal(cursor.getFloat(0));


        return foods;
    }


    public List<String> getFoodsList() {
        List<String> foods = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (Foods.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while(!cursor.isAfterLast()) {

            foods.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2));

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return foods;
    }

    /**
     * Call when need List<Friend> instead of List<String>
     * @return List of friends.
     */
    public List<Foods> getAllFood() {

        String QUERY_ALL_FOOD = "SELECT * FROM " + Foods.TABLE;

        List<Foods> foods = new ArrayList<Foods>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_ALL_FOOD, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {

            Foods food = new Foods();

            food.setfId((int) cursor.getLong(0));
            food.setFoodname(cursor.getString(1));
            food.setFoodcal(cursor.getString(2));
            food.setFoodtype1(cursor.getString(3));
            food.setFoodtype2(cursor.getString(4));

            foods.add(food);

            cursor.moveToNext();
        }

        sqLiteDatabase.close();
        return foods;
    }

    /*public void sumFoodColumn(Foods foods) {
        sqLiteDatabase = this.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT Sum(" + Foods.fColumn.COL_FCAL + ") FROM " + Foods.TABLE, null);
        if (c.moveToFirst()) {
            ;
        }
        sqLiteDatabase.close();
        return foods;}*/

    //UPDATE
    public void updateFriend(Foods foods) {

        sqLiteDatabase  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Foods.fColumn.ID, foods.getfId());
        values.put(Foods.fColumn.COL_FNAME, foods.getFoodname());
        values.put(Foods.fColumn.COL_FCAL, foods.getFoodcal());
        values.put(Foods.fColumn.COL_FTYPE1, foods.getFoodtype1());
        values.put(Foods.fColumn.COL_FTYPE2, foods.getFoodtype2());

        int row = sqLiteDatabase.update(Foods.TABLE,
                values,
                Foods.fColumn.ID + " = ? ",
                new String[] { String.valueOf(foods.getfId()) });

        sqLiteDatabase.close();
    }

    //DELETE
    public void deleteFood(String id) {

        sqLiteDatabase = this.getWritableDatabase();

/*        sqLiteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = ? ",
                new String[] { String.valueOf(friend.getId()) });*/
        sqLiteDatabase.delete(Foods.TABLE, Foods.fColumn.ID + " = " + id, null);

        sqLiteDatabase.close();
    }

}