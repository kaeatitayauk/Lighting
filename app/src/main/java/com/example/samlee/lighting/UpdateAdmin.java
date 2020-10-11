package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateAdmin extends AppCompatActivity {
    EditText edName,edAge;
    Button btnUpdate;
    String s_Name,s_Age,s_Id;
    int getId;

    DatabaseHelperAdmin myDb;
    DBOpenHelper dbOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);

        dbOpenHelper = new DBOpenHelper(this);
        myDb = new DatabaseHelperAdmin(this);

        edName = (EditText) findViewById(R.id.edName_Update);
        //edAge = (EditText)findViewById(R.id.edAge_Update);

        Intent integer = getIntent();

        s_Id = integer.getStringExtra("id");
        s_Name = integer.getStringExtra("name");
        //s_Age = integer.getStringExtra("age");

        getId = Integer.parseInt(s_Id);
        edName.setText(s_Name);
        //edAge.setText(s_Age);

        btnUpdate = (Button)findViewById(R.id.btnUpdate_Update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateDAta(getId,edName.getText().toString());
                Intent its = new Intent(UpdateAdmin.this,List_Mani_Admin.class);
                startActivity(its);
            }
        });


    }
}
