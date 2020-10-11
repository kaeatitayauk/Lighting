package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cal_cameraupload_MainUser extends AppCompatActivity {

    Button btn_upload,btn_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_cameraupload__main_user);

        btn_upload = (Button)findViewById(R.id.btn_upload);
        btn_camera = (Button)findViewById(R.id.btn_camera);



        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(cal_cameraupload_MainUser.this,cal_userxupload.class);
                startActivity(its);

            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(cal_cameraupload_MainUser.this,cal_userxcamera.class);
                startActivity(its);

            }
        });

    }
}
