package com.example.samlee.lighting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    DatabaseHelperUser myDb;
    DatabaseHelperAdmin UNIT_adminanduser;
    TextView input_lastmeter;
    SharedPreferences preferences,preferences2,preferences3;
    SharedPreferences.Editor editor ,editor2;
    Button backtocal;
    Integer input_unit;
    EditText ip_unit;
    Integer UnitAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_2);
        input_lastmeter = (TextView)findViewById(R.id.input_lastmeter);
        ip_unit = (EditText)findViewById(R.id.input_unit);
        backtocal = (Button)findViewById(R.id.btn_backtocal);
        UNIT_adminanduser = new DatabaseHelperAdmin(this);
        myDb = new DatabaseHelperUser(this);

        viewAlluserxreport();

        preferences = getSharedPreferences("unit",Setting.MODE_PRIVATE);
        editor = preferences.edit();

        preferences2 = getSharedPreferences("lastmeter",Setting.MODE_PRIVATE);
        editor2 = preferences2.edit();

        preferences3 = getSharedPreferences("unit",Setting_ad.MODE_PRIVATE);
        UnitAdmin = preferences3.getInt("unit",0);

        ip_unit.setText(String.valueOf(UnitAdmin.toString().trim()));

        // back to cal again{
//        final Spinner spinner = findViewById(R.id.spin_input_unit);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.string_unit,android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0 :
//                        input_unit = Integer.parseInt(spinner.getSelectedItem().toString());
//                        break;
//                    case 1 :
//                        input_unit = Integer.parseInt(spinner.getSelectedItem().toString());
//                        spinner.onSaveInstanceState();
//                        break;
//                    case 2 :
//                        input_unit = Integer.parseInt(spinner.getSelectedItem().toString());
//                        break;
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        backtocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // EditText iputunit = (EditText)findViewById(R.id.input_unit);
                EditText iputlmeter = (EditText)findViewById(R.id.input_lastmeter);
              // input_unit = Integer.parseInt(iputunit.getText().toString());
                Integer unit_cal = Integer.parseInt(ip_unit.getText().toString());
                Float iput_lastmeter = Float.parseFloat(iputlmeter.getText().toString());
                    editor.putInt("unit",unit_cal);
                    editor2.putFloat("lastmeter",iput_lastmeter);
                    editor2.commit();
                    editor.commit();
                    Toast.makeText(getBaseContext(),"บันทึกข้อมูลเสร็จสิ้น",Toast.LENGTH_LONG).show();
                    Intent its = new Intent(Setting.this,calmainxuser.class);
                    startActivity(its);
            }
        });
    }
    private void viewAlluserxreport() {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while ((res.moveToNext())){
            if(res.moveToLast()){
                input_lastmeter.setText(buffer.append(res.getString(2) + "\n"));
            }
        }
    }
}
