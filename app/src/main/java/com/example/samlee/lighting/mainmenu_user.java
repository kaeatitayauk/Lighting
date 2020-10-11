package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainmenu_user extends AppCompatActivity {
    DatabaseHelperUser myDb;
    Button btn_userxreport,btn_back,btn_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu_user);
        myDb = new DatabaseHelperUser(this);

        btn_userxreport = (Button)findViewById(R.id.btn_userxreport);
        btn_contact = (Button)findViewById(R.id.btn_userxcontact);
        btn_back = (Button)findViewById(R.id.btn_backhome);


        btn_userxreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(mainmenu_user.this,calendarUser.class);
                startActivity(its);

            }
        });
        Button btnxcaluser  = (Button)findViewById(R.id.btn_userxcal);
        btnxcaluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(mainmenu_user.this,home_userxcal.class);
                startActivity(its);
                finish();
            }
        });
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(mainmenu_user.this,Contact_user.class);
                startActivity(its);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(mainmenu_user.this,firstpage.class);
                startActivity(its);
            }
        });
    }




}
