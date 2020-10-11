package com.example.samlee.lighting;

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

public class Setting_ad extends AppCompatActivity {
    TextView input_lastmeter, Test, Test1, Test2;
    DatabaseHelperAdmin myDb;
    Button savetocalad;
    SharedPreferences preferences, preferences2, preferences3;
    SharedPreferences.Editor editor, editor2;
    String ID, X, room;
    EditText iputunit, iputlmeter;
    Integer iput_unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_ad);
        Test = (TextView) findViewById(R.id.textView14);
        Test1 = (TextView) findViewById(R.id.textView15);
        input_lastmeter = (TextView) findViewById(R.id.input_lastmeter);
        savetocalad = (Button) findViewById(R.id.btn_backtocal);

        Intent incomingInteger = getIntent();
        ID = incomingInteger.getStringExtra("ID");
        Test.setText("ID = " + ID);
        X = ID.toString();

        preferences = getSharedPreferences("unit", Setting_ad.MODE_PRIVATE);
        editor = preferences.edit();

        preferences2 = getSharedPreferences("lastmeter", Setting_ad.MODE_PRIVATE);
        editor2 = preferences2.edit();

        preferences3 = getSharedPreferences("room", Admin_Home_Admin.MODE_PRIVATE);
        room = preferences3.getString("numberRoom", "");
        Test1.setText(room);

        myDb = new DatabaseHelperAdmin(this);

        viewAlluserxreport1();

        savetocalad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText iputunit = (EditText)findViewById(R.id.input_unit);
//                EditText iputlmeter = (EditText)findViewById(R.id.input_lastmeter);
//                Integer iput_unit = Integer.parseInt(iputunit.getText().toString());
//                Float iput_lastmeter = Float.parseFloat(iputlmeter.getText().toString());
                iputunit = (EditText) findViewById(R.id.input_unit);
                iputlmeter = (EditText) findViewById(R.id.input_lastmeter);
                if (iputunit.getText().toString().equals("") || iputlmeter.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                } else {
                    iput_unit = Integer.parseInt(iputunit.getText().toString());
                    Float iput_lastmeter = Float.parseFloat(iputlmeter.getText().toString());
                    editor.putInt("unit", iput_unit);
                    editor2.putFloat("lastmeter", iput_lastmeter);
                    editor2.commit();
                    editor.commit();
                    Toast.makeText(getBaseContext(), "บันทึกข้อมูลเสร็จสิ้น", Toast.LENGTH_LONG).show();
                    Intent its = new Intent(Setting_ad.this, calmain_admin.class);
                    its.putExtra("ID", X);
                    startActivity(its);
                }
            }
        });
    }
    private void viewAlluserxreport1() {
        Cursor res = myDb.getAllDataUnit();
        Cursor ID = myDb.getAllData();
        String ROOM = room.toString();
        while (res.moveToNext()){
                if (X.equals(res.getString(0)) && ROOM.equals(res.getString(1))) {
                    input_lastmeter.setText(res.getString(3) + "\n");
                }

        }
    }

//    private void viewAlluserxreport1() {
//        Cursor res = myDb.getAllDataUnit();
//        Cursor res1 = myDb.getAllData();
//        String ROOM = room.toString();
//        if(res.getCount() == 0 && res1.getCount() == 0){
//            return;
//        }
//        StringBuffer buffer = new StringBuffer();
//        while ((res.moveToNext())&&res.moveToLast()&&res1.moveToLast()){
//            if(res.getString(1).equals(ROOM.toString()) && res1.getString(1).equals(X.toString())){
//                    if(X.equals(res1.getString(1)) && ROOM.equals(res.getString(1))){
//                        input_lastmeter.setText(buffer.append(res.getString(3) + "\n"));
//                    }
//            }
//        }
//    }

}


























