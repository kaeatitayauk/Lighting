package com.example.samlee.lighting;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class calendarAdmin extends AppCompatActivity {
    DatabaseHelperAdmin myDb;
    String no_room;
    private static final String TAG = "calendarUser";
    private TextView txtDate,txtdatadate,txtid;
    private Button button,button1;
    String date;
    String ID,X,x;
    TextView IDAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_admin);

        myDb = new DatabaseHelperAdmin(this);
        IDAdmin = (TextView)findViewById(R.id.textView1);
        txtDate = (TextView) findViewById(R.id.textView);
        txtdatadate = (TextView) findViewById(R.id.textView8);
        txtid = (TextView) findViewById(R.id.textView12);

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);

        Intent incomingInteger = getIntent();
        date = incomingInteger.getStringExtra("date");
        txtDate.setText(date);
        x = txtDate.getText().toString();


        ID = incomingInteger.getStringExtra("ID");
        IDAdmin.setText(ID);
        X = IDAdmin.getText().toString();



        final Spinner spinner = (Spinner)findViewById(R.id.spinner_select_room);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.string_room,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_room = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calendarAdmin.this, Admin_Home_Admin.class);
                its.putExtra("ID",X);
                startActivity(its);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(calendarAdmin.this, calendarActivityAdmin.class);
                its.putExtra("ID",X);
                startActivity(its);
            }
        });
        viewAlluserxreport();
    }

    private void viewAlluserxreport() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllDataUnit();
                Cursor res1 = myDb.getAllData();
                if(res.getCount() == 0 && res1.getCount() ==0){
                    //show message
                    showMessage("Error ","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while ((res.moveToNext())){
                    //if(x.equals(res.getString(2))){
                    if(x.equals(res.getString(2)) && X.equals(res.getString(0)) && no_room.equals(res.getString(1))){
                        //txtdatadate.setText(buffer.append("ID =" + res.getString(0) + "\n"));
                       // txtdatadate.setText(buffer.append("ROOM = " + res.getString(1) + "\n"));
                        txtdatadate.setText(buffer.append("วันที่ : " + res.getString(2) + "\n"));
                        txtdatadate.setText(buffer.append("เลขอ่านล่าสุด : " + res.getString(3) + "\n"));
                        txtdatadate.setText(buffer.append("หน่วยที่ใช้ไป : " + res.getString(4) + "\n"));
                        txtdatadate.setText(buffer.append("จำนวนเงินทั้งหมด : " + res.getString(5) + "\n\n"));
                    }else if(x.equals(res.getString(2)) && !no_room.equals(res.getString(1))){
                        txtdatadate.setText(buffer.append(""));
                    }
                }
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
