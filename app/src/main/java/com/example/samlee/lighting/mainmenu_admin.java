package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class mainmenu_admin extends AppCompatActivity {

    Button btn,btn1,btn_back,btn_contact;
    String ID,X;
    TextView IDAdmin;
    DatabaseHelperAdmin myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu_admin);

        myDb = new DatabaseHelperAdmin(this);
        IDAdmin = (TextView)findViewById(R.id.textView);
        btn = (Button)findViewById(R.id.btn_userxcal);
        btn1 = (Button)findViewById(R.id.btn_userxreport);
        btn_back = (Button)findViewById(R.id.btn_backhome);
        btn_contact = (Button)findViewById(R.id.btn_userxcontact);

        Intent incomingInteger = getIntent();
        ID = incomingInteger.getStringExtra("ID");
        IDAdmin.setText("ID = "+ID);
        X = ID.toString();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(mainmenu_admin.this,calmain_admin.class);
                its.putExtra("ID",X);
                startActivity(its);

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(mainmenu_admin.this,calendarAdmin.class);
                its.putExtra("ID",X);
                startActivity(its);
            }
        });
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(mainmenu_admin.this,Contact_Admin.class);
                startActivity(its);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(mainmenu_admin.this,firstpage.class);
                startActivity(its);
            }
        });
    }
}
