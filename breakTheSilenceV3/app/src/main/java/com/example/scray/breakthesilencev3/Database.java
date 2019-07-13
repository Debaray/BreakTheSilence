package com.example.scray.breakthesilencev3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masru on 20-Nov-18.
 */

public class Database extends SQLiteOpenHelper{

    public static final String db_name="db";
    public static final String table1="transport";
    public static final String table2="restaurent";
    public static final String table3="shopping";
    public static final String table4="communication";
    public static final String table5="hospital";
    public static final String c1="id";
    public static final String c2="sentence";
    public static final String c3="freq";

    public Database(Context context) {
        super(context,db_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //transport
        sqLiteDatabase.execSQL ("create table " + table1 +"(" + c1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                c2 + " TEXT," + c3 +" INTEGER"+")");
        //restaurent
        sqLiteDatabase.execSQL ("create table " + table2 +"(" + c1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                c2 + " TEXT," + c3 +" INTEGER"+")");
        //shopping
        sqLiteDatabase.execSQL ("create table " + table3 +"(" + c1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                c2 + " TEXT," + c3 +" INTEGER"+")");
        //communication
        sqLiteDatabase.execSQL ("create table " + table4 +"(" + c1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                c2 + " TEXT," + c3 +" INTEGER"+")");
        //hospital
        sqLiteDatabase.execSQL ("create table " + table5 +"(" + c1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                c2 + " TEXT," + c3 +" INTEGER"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table1 );//transport
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table2 );//restaurent
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table3 );//shopping
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table4 );//communication
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table5 );//hospital
        onCreate(sqLiteDatabase);
    }

    //transport
    void insertTable1(String sentence,int count)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        String insert = "insert into transport(sentence,freq) values('"+sentence+"','"+count+"')";
        database.execSQL(insert);

        database.close();

    }

    //restaurent
    void insertTable2(String sentence,int count)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        String insert = "insert into restaurent(sentence,freq) values('"+sentence+"','"+count+"')";
        database.execSQL(insert);

        database.close();

    }

    //shopping
    void insertTable3(String sentence,int count)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        String insert = "insert into shopping(sentence,freq) values('"+sentence+"','"+count+"')";
        database.execSQL(insert);

        database.close();

    }
    //communication
    void insertTable4(String sentence,int count)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        String insert = "insert into communication(sentence,freq) values('"+sentence+"','"+count+"')";
        database.execSQL(insert);

        database.close();

    }
    //hospital
    void insertTable5(String sentence,int count)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        String insert = "insert into hospital(sentence,freq) values('"+sentence+"','"+count+"')";
        database.execSQL(insert);

        database.close();

    }

    //transport
    public List<TableInfo> getAllDataTable1() {
        List<TableInfo> tbl = new ArrayList<TableInfo>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectquery = "SELECT * FROM " + table1;
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            do {
                TableInfo t = new TableInfo(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                t.setId(Integer.parseInt(cursor.getString(0)));
                t.setSentence(cursor.getString(1));

                tbl.add(t);
            } while (cursor.moveToNext());
        }
        db.close();
        return tbl;
    }

    //restaurent
    public List<TableInfo> getAllDataTable2() {
        List<TableInfo> tbl = new ArrayList<TableInfo>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectquery = "SELECT * FROM " + table2;
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            do {
                TableInfo t = new TableInfo(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                t.setId(Integer.parseInt(cursor.getString(0)));
                t.setSentence(cursor.getString(1));

                tbl.add(t);
            } while (cursor.moveToNext());
        }
        db.close();
        return tbl;
    }

    //shopping
    public List<TableInfo> getAllDataTable3() {
        List<TableInfo> tbl = new ArrayList<TableInfo>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectquery = "SELECT * FROM " + table3;
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            do {
                TableInfo t = new TableInfo(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                t.setId(Integer.parseInt(cursor.getString(0)));
                t.setSentence(cursor.getString(1));

                tbl.add(t);
            } while (cursor.moveToNext());
        }
        db.close();
        return tbl;
    }

    //communication
    public List<TableInfo> getAllDataTable4() {
        List<TableInfo> tbl = new ArrayList<TableInfo>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectquery = "SELECT * FROM " + table4;
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            do {
                TableInfo t = new TableInfo(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                t.setId(Integer.parseInt(cursor.getString(0)));
                t.setSentence(cursor.getString(1));

                tbl.add(t);
            } while (cursor.moveToNext());
        }
        db.close();
        return tbl;
    }

    //hospital
    public List<TableInfo> getAllDataTable5() {
        List<TableInfo> tbl = new ArrayList<TableInfo>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectquery = "SELECT * FROM " + table5;
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            do {
                TableInfo t = new TableInfo(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
                t.setId(Integer.parseInt(cursor.getString(0)));
                t.setSentence(cursor.getString(1));

                tbl.add(t);
            } while (cursor.moveToNext());
        }
        db.close();
        return tbl;
    }

    public void deleteAll()
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete(table1,null,null);//transport
        db.delete(table2,null,null);//restaurent
        db.delete(table3,null,null);//shopping
        db.delete(table4,null,null);//communication
        db.delete(table5,null,null);//hospital
        db.close();

    }
}
