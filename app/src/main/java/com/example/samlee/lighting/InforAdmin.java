package com.example.samlee.lighting;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InforAdmin extends AppCompatActivity {

    TextView tvName,tvAge,tvId;
    String s_Name , s_Age, s_Id;
    DatabaseHelperAdmin myDb;
    DBOpenHelper dbOpenHelper;
    Button btnUpdate,btnDelete;
    int getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_admin);
        myDb = new DatabaseHelperAdmin(this);
        dbOpenHelper =  new DBOpenHelper(this);

        tvId = (TextView)findViewById(R.id.tvId_Infor);
        tvName = (TextView)findViewById(R.id.tvName_Infor);
        //tvAge = (TextView)findViewById(R.id.tvAge_Infor);

        Intent integer = getIntent();
        if(integer != null){
            s_Id = integer.getStringExtra("id");
            s_Name = integer.getStringExtra("name");
           // s_Age  = integer.getStringExtra("age");

        }

        getId = Integer.parseInt(s_Id);
                tvId.setText(getId + "");
                tvName.setText(s_Name);
                //tvAge.setText(s_Age);


        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(InforAdmin.this,UpdateAdmin.class);
                its.putExtra("id",s_Id);
                its.putExtra("name",s_Name);
                //its.putExtra("age",s_Age);
                startActivity(its);

            }
        });

        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = deleteAndConfirm();
                alertDialog.show();
            }
        });

    }
    private AlertDialog deleteAndConfirm(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Do you want to delete?")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myDb.deleteData(getId);
                            Intent its = new Intent(InforAdmin.this, List_Mani_Admin.class);
                            startActivity(its);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                .create();
        return alertDialog;
    }



}
