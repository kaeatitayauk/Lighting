package com.example.samlee.lighting;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperUser extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "DatabaseHelperUser.db";
    private  static final int DATABBASE_VERSION = 1;
    public static final String TABLE_NAMBER = "namber_table";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_Lastmeter = "Lastmeter";
    public static final String COLUMN_Unit = "Unit";
    public static final String COLUMN_Total_pay = "Total_pay";
    private static DatabaseHelperUser sqliteHelper;



    public DatabaseHelperUser(Context context){
        super(context,DATABASE_NAME,null,DATABBASE_VERSION);
    }
    public static synchronized DatabaseHelperUser getInstance(Context c){
        if(sqliteHelper == null){
            sqliteHelper = new DatabaseHelperUser(c.getApplicationContext());
        }
        return sqliteHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAMBER+"(ID integer PRIMARY KEY AUTOINCREMENT,Date text,Lastmeter text,Unit text,Total_pay text )");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAMBER);
        onCreate(db);
    }
    public boolean insertData(String Date,String Lastmeter,String Unit,String Total_pay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DATE,Date);
        contentValues.put(COLUMN_Lastmeter,Lastmeter);
        contentValues.put(COLUMN_Unit,Unit);
        contentValues.put(COLUMN_Total_pay,Total_pay);
        long result = db.insert(TABLE_NAMBER,null,contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAMBER,null);
        return res;
    }
}



