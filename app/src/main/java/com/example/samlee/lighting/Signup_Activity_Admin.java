package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup_Activity_Admin extends AppCompatActivity {
    DBOpenHelper dbOpenHelper;
    DatabaseHelperAdmin db;
    EditText e1,e2,e3;
    Button b1;
    String s1,s2,s3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup___admin);
        dbOpenHelper = new DBOpenHelper(this);
        db = new DatabaseHelperAdmin(this);
        e1=(EditText)findViewById(R.id.Username);
        e2=(EditText)findViewById(R.id.pass1);
        e3=(EditText)findViewById(R.id.pass2);
        b1=(Button)findViewById(R.id.register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    s1 = e1.getText().toString().trim();
                    s2 = e2.getText().toString().trim();
                    s3 = e3.getText().toString().trim();
                if (s1.equals("")|| s2.equals("")|| s3.equals("")){
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
                else if(e2.getText().length()<4 && e3.getText().length()<4){
                    Toast.makeText(getApplicationContext(), "กรุณากรอกพาสเวิร์ดอย่างน้อย 4 ตัว", Toast.LENGTH_SHORT).show();
                }
                    else {
                        if(s2.equals(s3)){
                            Boolean chkeusername = db.chkusername(s1);
                            if(chkeusername==true){
                                Boolean insert = db.insert(s1,s2);
                                if(insert==true){
                                    //dbOpenHelper.insertData(s1,s2);
                                    Toast.makeText(getApplicationContext(),"ลงทะเบียนสำเร็จ",Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Signup_Activity_Admin.this,Success_Activity_Admin.class);
                                    startActivity(i);
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"ไอดีนี้มีผู้ใช้งานแล้ว",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "พาสเวิร์ดไม่ตรงกัน", Toast.LENGTH_SHORT).show();
                        }
                    }

//                if(s1.equals("")||s2.equals("")||s3.equals("")){
//                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    if(s2.equals(s3)){
//                        Boolean chkeusername = db.chkusername(s1);
//                        if(chkeusername==true){
//                            Boolean insert = db.insert(s1,s2);
//                            if(insert==true){
//                                //dbOpenHelper.insertData(s1,s2);
//                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
//                                Intent i = new Intent(Signup_Activity_Admin.this,Success_Activity_Admin.class);
//                                startActivity(i);
//                            }
//                        }
//                        else {
//                            Toast.makeText(getApplicationContext(),"User Already exists",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
//                    }
//                }

            }
        });

    }
}
