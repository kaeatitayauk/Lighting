package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cal_cameraupload_MainAdmin extends AppCompatActivity {
    Button btn_upload,btn_camera;
    String ID,X;
    TextView Test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_cameraupload__main_admin);

        btn_upload = (Button)findViewById(R.id.btn_upload);
        btn_camera = (Button)findViewById(R.id.btn_camera);
        Test = (TextView)findViewById(R.id.textView_maincal);

        Intent incomingInteger = getIntent();
        ID = incomingInteger.getStringExtra("ID");
        Test.setText("ID = "+ID);
        X = ID.toString();

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(cal_cameraupload_MainAdmin.this,cal_adminxupload.class);
                its.putExtra("ID",X);
                startActivity(its);

            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(cal_cameraupload_MainAdmin.this,cal_adminxcamera.class);
                its.putExtra("ID",X);
                startActivity(its);

            }
        });

    }
}
