package com.example.samlee.lighting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by samlee on 5/14/2018.
 */

public class DBOpenHelper extends SQLiteOpenHelper{

    public static  final String DATABASE_NAME = "sqlite.db";
    public static  final int VERSION = 1;

    public static  final String TABLE_NAME = "tbl_student";
    public static  final String S_ID = "s_id";
    public static  final String S_NAME = "s_name";
    public static  final String S_AGE  = "s_age";

//    public static final String CRATE_TABLE = "Create table " + TABLE_NAME + "(" +
//            "" + S_ID + " integer primary key autoincrement," +
//            "" + S_NAME + " text not null," +
//            "" + S_AGE + " text not null);";


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CRATE_TABLE);
        db.execSQL("create table "+TABLE_NAME+"(S_ID integer primary key,S_NAME text not null,S_AGE text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String s_Name,String s_AGE){
        SQLiteDatabase sqLiteDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(S_ID,s_ID);
        contentValues.put(S_NAME,s_Name);
        contentValues.put(S_AGE,s_AGE);
        long ins = sqLiteDB.insert(TABLE_NAME,null,contentValues);
        sqLiteDB.close();
        if(ins==-1)return  false;
        else return  true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public ArrayList<StudentModelAdmin> getAllStudentData(){
        ArrayList<StudentModelAdmin> list = new ArrayList<>();
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                StudentModelAdmin sm = new StudentModelAdmin();
                sm.setiD(cursor.getInt(0)+"");
                sm.setName(cursor.getString(1));
                sm.setAge(cursor.getString(2));
                list.add(sm);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public void updateDAta(int id ,String name , String age){
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_NAME,name);
        contentValues.put(S_AGE,age);

        SQLiteDatabase sqliteDB = this.getWritableDatabase();
        sqliteDB.update(TABLE_NAME, contentValues ,S_ID +"="+id,null);
        sqliteDB.close();

    }
    public void deleteData(int id){
        SQLiteDatabase sqliteDB = this.getWritableDatabase();
        sqliteDB.delete(TABLE_NAME,S_ID+"="+id,null);
        sqliteDB.close();
    }


}
