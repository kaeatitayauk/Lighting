package com.example.samlee.lighting;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class List_Mani_Admin extends AppCompatActivity {
    TextView IDAdmin;
    EditText edName,edAge;
    Button btnAdd,btnShowAll;
    DBOpenHelper dbOpenHelper;
    DatabaseHelperAdmin myDb;
    String id,name,age;
    String ID,X;
    String SUM ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__mani__admin);

        dbOpenHelper = new DBOpenHelper(this);
        myDb = new DatabaseHelperAdmin(this);
        edName = (EditText) findViewById(R.id.edName);
        //edAge  = (EditText) findViewById(R.id.edAge);
        IDAdmin = (TextView)findViewById(R.id.IDAdmin);

        Intent incomingInteger = getIntent();
        ID = incomingInteger.getStringExtra("ID");
        IDAdmin.setText(ID);

        X = IDAdmin.getText().toString();
        showID();




        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //id = IDAdmin.getText().toString();
                name = edName.getText().toString();
                //age = edAge.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please inset name and age", Toast.LENGTH_SHORT).show();
                    edName.setText("");
                    //edAge.setText("");
                } else {
//                    Cursor cursor = myDb.getAllData();
//                    Cursor cursor1 = myDb.getAllAllData();
//                    if(cursor.getCount()==0 && cursor1.getCount()==0){
//                        return;
//                    }
                    //while (cursor.moveToNext()){
                       // SUM = cursor.getString(0);
                        //if(SUM.equals(cursor.getString(0))){
                        myDb.insertData(SUM, name);
                        Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                        edName.setText("");


                            //edAge.setText("");
                    //}
                }
            }
        });

        btnShowAll = (Button)findViewById(R.id.btnShowAll);
        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent its = new Intent(List_Mani_Admin.this, ShowAllAdmin.class);
                        startActivity(its);
                finish();
            }
        });

    }
    private void showID() {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while ((res.moveToNext())) {
            if(X.equals(res.getString(1))){
                IDAdmin.setText(buffer.append("ID = " + res.getString(0) + "\n"));
                IDAdmin.setText(buffer.append("Admin = " + res.getString(1) + "\n"));
                IDAdmin.setText(buffer.append("Pass = " + res.getString(2) + "\n"));
            }
        }
    }
}
