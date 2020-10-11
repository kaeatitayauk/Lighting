package com.example.samlee.lighting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class calmainxuser extends AppCompatActivity {
    DatabaseHelperUser myDb;
    Button btn_save;
    EditText lastmeter,unit,total_pay;
    TextView txtdate;
    private String strShowDate;
    String data,Unitpick,Moneypick,datacamera,unitcamera,moneycamera;
    SharedPreferences sharedPreferences,preferences2;
    SharedPreferences.Editor editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calmainxuser);

        myDb = new DatabaseHelperUser(this);

        txtdate = (TextView) findViewById(R.id.txtdate);
        lastmeter = (EditText) findViewById(R.id.lastmeter);
        unit = (EditText) findViewById(R.id.unit);
        total_pay = (EditText) findViewById(R.id.total_pay);
        btn_save = (Button) findViewById(R.id.btn_save);




        preferences2 = getSharedPreferences("lastmeter", Setting_ad.MODE_PRIVATE);
        editor2 = preferences2.edit();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            datacamera = bundle.getString("lastmetercamera");
            lastmeter.setText(datacamera);

            unitcamera = bundle.getString("unitcamera");
            unit.setText(unitcamera);

            moneycamera = bundle.getString("moneycamera");
            total_pay.setText(moneycamera);

        }

        Button gohome = (Button) findViewById(R.id.btn_home);
        Button uploadpic = (Button) findViewById(R.id.btn_upload);
        Button settings = (Button) findViewById(R.id.btn_set);


        getTimeFromDevice();
        //get Time from Device
        AddData();
        //AddData
        //go back to first page
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calmainxuser.this, mainmenu_user.class);
                startActivity(its);
            }
        });

        //go to upload pic to calculate
        uploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calmainxuser.this, cal_cameraupload_MainUser.class);
                startActivity(its);
            }
        });

        //go to setting
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calmainxuser.this, Setting.class);
                startActivity(its);
            }
        });


    }



    private void getTimeFromDevice() {
        DateFormat objDateFormat = new SimpleDateFormat("dd/M/yyyy");
        Date objDate = new Date();
        strShowDate = objDateFormat.format(objDate);
        txtdate.setText(strShowDate);
    }//getTimeFromDevice

    public void AddData(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sdate = txtdate.getText().toString();
                String Slastmeter = lastmeter.getText().toString();
                String Sunit = unit.getText().toString();
                String Stotal_pay = total_pay.getText().toString();
                Float iput_lastmeter = Float.parseFloat(lastmeter.getText().toString());
                editor2.putFloat("lastmeter", iput_lastmeter);
                editor2.commit();
                if(Slastmeter.equals("")&&Sunit.equals("")&&Stotal_pay.equals("")){
                    Toast.makeText(getApplicationContext(),"no Data Inserted",Toast.LENGTH_SHORT).show();
                }else{
                    boolean isInserted = myDb.insertData(Sdate,Slastmeter,Sunit,Stotal_pay);
                    if(isInserted == true)
                        Toast.makeText(calmainxuser.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }
//                boolean isInserted = myDb.insertData(Sdate,Slastmeter,Sunit,Stotal_pay);
//                if(isInserted == true)
//                    Toast.makeText(calmainxuser.this,"Data Inserted",Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(calmainxuser.this,"no Data Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }


}
