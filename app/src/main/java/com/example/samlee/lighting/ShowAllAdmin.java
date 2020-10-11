package com.example.samlee.lighting;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowAllAdmin extends AppCompatActivity {
    DatabaseHelperAdmin myDb;
    DBOpenHelper dbOpenHelper;
    CustomAdapterAdmin customAdapterAdmin;
    ArrayList<StudentModelAdmin> arrayList = new ArrayList<>();
    StudentModelAdmin sm;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_admin);

        list = (ListView)findViewById(R.id.lvShow);
        myDb = new DatabaseHelperAdmin(this);
        dbOpenHelper = new DBOpenHelper(this);

        arrayList = myDb.getAllStudentData();

        customAdapterAdmin = new CustomAdapterAdmin(this,arrayList);

        list.setAdapter(customAdapterAdmin);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               sm = customAdapterAdmin.getItem(position);
               Intent its = new Intent(ShowAllAdmin.this,InforAdmin.class);
               its.putExtra("id",sm.getiD());
               its.putExtra("name",sm.getName());
               //its.putExtra("age",sm.getAge());
               startActivity(its);
           }
       });
    }
}
