package com.example.samlee.lighting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelperAdmin extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Login.db";
    private  static final int DATABBASE_VERSION = 1;
    private static  final String USER_TABLE = "create table user(ID integer PRIMARY KEY AUTOINCREMENT,user text ," + "password text);";
    private static  final String USER_TABLE1= "create table useruser(ID integer PRIMARY KEY AUTOINCREMENT,S_NAME text not null)";
    private static final String COLUMN_Unit= "create table unit(ID text,Room text,Date text,Lastmeter text,Unit text,Total_pay text);";
    public DatabaseHelperAdmin(Context context){
        super(context,DATABASE_NAME,null,DATABBASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE);
        db.execSQL(USER_TABLE1);
        db.execSQL(COLUMN_Unit);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists useruser");
        db.execSQL("drop table if exists unit");
        onCreate(db);
    }

    public boolean insert(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user",user);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if(ins==-1)return  false;
        else return  true;
    }
    public Boolean chkusername(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where user=?",new String[]{user});
        if(cursor.getCount()>0) return  false;
        else  return  true;
    }
    public Boolean userpassword(String user,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where user=? and password=?", new String[]{user, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from user",null);
        return res;
    }

    public Boolean insertData(String ID,String s_Name){
        SQLiteDatabase sqLiteDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",ID);
        contentValues.put("S_NAME",s_Name);
        //contentValues.put("S_AGE",s_AGE);
        long ins = sqLiteDB.insert("useruser",null,contentValues);
        sqLiteDB.close();
        if(ins==-1)return  false;
        else return  true;
    }
    public ArrayList<StudentModelAdmin> getAllStudentData(){
        ArrayList<StudentModelAdmin> list = new ArrayList<>();
        String sql = "select * from useruser" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                StudentModelAdmin sm = new StudentModelAdmin();
                sm.setiD(cursor.getInt(0)+"");
                sm.setName(cursor.getString(1));
                //sm.setAge(cursor.getString(2));
                list.add(sm);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public void updateDAta(int id ,String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put("S_NAME",name);
        //contentValues.put("S_AGE",age);
        SQLiteDatabase sqliteDB = this.getWritableDatabase();
        sqliteDB.update("useruser", contentValues ,"ID" +"="+id,null);
        sqliteDB.close();

    }
    public void deleteData(int id){
        SQLiteDatabase sqliteDB = this.getWritableDatabase();
        sqliteDB.delete("useruser","ID" +"="+id,null);
        sqliteDB.close();
    }
    public boolean insertDataUnit(String id,String room,String date,String lastmeter,String unitt,String total_payy){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("Room",room);
        contentValues.put("Date",date);
        contentValues.put("Lastmeter",lastmeter);
        contentValues.put("Unit",unitt);
        contentValues.put("Total_pay",total_payy);
        long result = db.insert("unit",null,contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }
    public Cursor getAllDataUnit(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from unit",null);
        return res;
    }
}
//    private static final String DATABASE_NAME = "Login.db";
//    private  static final int DATABBASE_VERSION = 1;
//    private static  final String USER_TABLE = "create table user(user text primary key," + "password text);";
//    public DatabaseHelperAdmin(Context context){
//        super(context,DATABASE_NAME,null,DATABBASE_VERSION);
//    }
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(USER_TABLE);
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists user");
//    }
//
//    public boolean insert(String user, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("user",user);
//        contentValues.put("password",password);
//        long ins = db.insert("user",null,contentValues);
//        if(ins==-1)return  false;
//        else return  true;
//    }
//    public Boolean chkusername(String user){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from user where user=?",new String[]{user});
//        if(cursor.getCount()>0) return  false;
//        else  return  true;
//    }
//    public Boolean userpassword(String user,String password) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("select * from user where user=? and password=?", new String[]{user, password});
//        if (cursor.getCount() > 0) return true;
//        else return false;
//    }
