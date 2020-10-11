package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class firstpage extends AppCompatActivity {

    EditText User,Pass;
    Button btn;
    DatabaseHelperAdmin db;
    DBOpenHelper dbOpenHelper;
    Button btn_admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        dbOpenHelper = new DBOpenHelper(this);
        db = new DatabaseHelperAdmin(this);
        User = (EditText)findViewById(R.id.Username);
        Pass = (EditText)findViewById(R.id.Password);
        btn_admin = (Button)findViewById(R.id.buttonlogin);
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = User.getText().toString();
                String password = Pass.getText().toString();
                Boolean chkuserpass = db.userpassword(user,password);
                if(chkuserpass==true){
                        Toast.makeText(getApplicationContext(),"เข้าสู่ระบบ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(firstpage.this,Admin_Home_Admin.class);
                    i.putExtra("ID",user);
                    firstpage.this.startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"ไม่มีข้อมูลในระบบ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnxtestuser  = (Button)findViewById(R.id.btnuser);
        btnxtestuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(firstpage.this,mainmenu_user.class);
                startActivity(its);
            }
        });

        final TextView registerLink = (TextView)findViewById(R.id.text_signup);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(firstpage.this,Signup_Activity_Admin.class);
                firstpage.this.startActivity(registerIntent);
            }
        });
    }
}
