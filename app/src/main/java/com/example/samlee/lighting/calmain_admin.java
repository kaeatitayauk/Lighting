package com.example.samlee.lighting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

public class calmain_admin extends AppCompatActivity {

    Button btn_save;
    EditText lastmeter,unit,total_pay;
    TextView txtdate,Test,roomadmin;
    private String strShowDate;
    String datacamera,unitcamera,moneycamera;
    DatabaseHelperAdmin myDb;
    String txtroom;
    SharedPreferences sharedPreferences,preferences2;
    SharedPreferences.Editor editor2;
    String ID,X;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calmaim_admin);

        myDb = new DatabaseHelperAdmin(this);

        Test = (TextView) findViewById(R.id.textView);
        txtdate = (TextView) findViewById(R.id.txtdate);
        lastmeter = (EditText)findViewById(R.id.lastmeter);
        unit = (EditText)findViewById(R.id.unit);
        total_pay = (EditText)findViewById(R.id.total_pay);
        btn_save = (Button)findViewById(R.id.btn_save);

        Intent incomingInteger = getIntent();
        ID = incomingInteger.getStringExtra("ID");
        Test.setText(ID);
        X = ID.toString();

        roomadmin = (TextView)findViewById(R.id.text_room);

        sharedPreferences = getSharedPreferences("room",Admin_Home_Admin.MODE_PRIVATE);
        txtroom = sharedPreferences.getString("numberRoom","");
        roomadmin.setText(txtroom);

        preferences2 = getSharedPreferences("lastmeter", Setting_ad.MODE_PRIVATE);
        editor2 = preferences2.edit();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            datacamera = bundle.getString("lastmetercamera");
            lastmeter.setText(datacamera);


            unitcamera = bundle.getString("unitcamera");
            unit.setText(unitcamera);



            moneycamera= bundle.getString("moneycamera");
            total_pay.setText(moneycamera);


        }

        Button gohome = (Button)findViewById(R.id.btn_home);
        Button uploadpic = (Button)findViewById(R.id.btn_upload);
        Button settings = (Button)findViewById(R.id.btn_set);




        getTimeFromDevice();
        //get Time from Device
        AddData();
        //AddData


        //go back to first page
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calmain_admin.this,mainmenu_admin.class);
                its.putExtra("ID",X);
                startActivity(its);

            }
        });

        //go to upload pic to calculate
        uploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calmain_admin.this,cal_cameraupload_MainAdmin.class);
                its.putExtra("ID",X);
                startActivity(its);

            }
        });

        //go to setting
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calmain_admin.this,Setting_ad.class);
                its.putExtra("ID",X);
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
                String SID = Test.getText().toString();
                String Sroom = roomadmin.getText().toString();
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
                    boolean insertDataUnit = myDb.insertDataUnit(SID,Sroom,Sdate,Slastmeter,Sunit,Stotal_pay);
                    if(insertDataUnit == true)
                        Toast.makeText(calmain_admin.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }
//                boolean insertDataUnit = myDb.insertDataUnit(SID,Sroom,Sdate,Slastmeter,Sunit,Stotal_pay);
//                if(insertDataUnit == true)
//                    Toast.makeText(calmain_admin.this,"Data Inserted",Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(calmain_admin.this,"no Data Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }


}
