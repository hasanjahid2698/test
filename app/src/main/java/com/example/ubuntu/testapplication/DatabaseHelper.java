package com.example.ubuntu.testapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Transaction.db";
    public static final String TABLE_NAME = "Transaction_Table";
    public static final String ID_COLUMN_0 = "ID";
    public static final String DATE_COLUMN_1 = "DATE";
    public static final String CATEGORY_COLUMN_2 = "CATEGORY";
    public static final String AMOUNT_COLMUN_3 = "AMOUNT";

    private Button button;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" ( ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, CATEGORY TEXT, AMOUNT REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String date, String category, Double amount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE_COLUMN_1,date);
        contentValues.put(CATEGORY_COLUMN_2,category);
        contentValues.put(AMOUNT_COLMUN_3,amount);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Cursor getAlldata(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public List <ListCollection> listfromdb() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ListCollection> results = new ArrayList<ListCollection>();

        Cursor crs = db.rawQuery("select * from "+TABLE_NAME, null);
        while (crs.moveToNext()) {
            ListCollection item = new ListCollection();
            item.setId(crs.getInt(crs.getColumnIndex(ID_COLUMN_0)));
            item.setDate(crs.getString(crs.getColumnIndex(DATE_COLUMN_1)));
            item.setCategory(crs.getString(crs.getColumnIndex(CATEGORY_COLUMN_2)));
            item.setAmount(crs.getDouble(crs.getColumnIndex(AMOUNT_COLMUN_3)));
            results.add(item);
        }

        db.close();
        return results;
    }

    public List <ListCollection> listfromdbondate(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ListCollection> results = new ArrayList<ListCollection>();

//        Cursor crs = db.rawQuery("select * from "+TABLE_NAME, null);

        Cursor crs = db.query(TABLE_NAME,null,DATE_COLUMN_1+" LIKE ?", new String [] {"%"+date+"%"},null,null,null);
        while (crs.moveToNext()) {
            ListCollection item = new ListCollection();
            item.setId(crs.getInt(crs.getColumnIndex(ID_COLUMN_0)));
            item.setDate(crs.getString(crs.getColumnIndex(DATE_COLUMN_1)));
            item.setCategory(crs.getString(crs.getColumnIndex(CATEGORY_COLUMN_2)));
            item.setAmount(crs.getDouble(crs.getColumnIndex(AMOUNT_COLMUN_3)));
            results.add(item);
        }

        db.close();
        return results;
    }



    public Integer updateData(Integer id , String date, String category, Double amount){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE_COLUMN_1,date);
        contentValues.put(CATEGORY_COLUMN_2,category);
        contentValues.put(AMOUNT_COLMUN_3,amount);

        return sqLiteDatabase.update(TABLE_NAME,contentValues,"id = ?",new String[] { Integer.toString(id) });

    }

    public Integer deleteData(Integer id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "id = ? ",
                new String[] { Integer.toString(id) });
    }
}
