package com.example.samlee.lighting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

public class calendarActivityAdmin extends AppCompatActivity {
    private static final String TAG = "calendarActivityUser";
    private CalendarView mCalendarView;
    String ID,X;
    TextView IDAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendaractivityadmin);
        IDAdmin = (TextView)findViewById(R.id.textView1);
        mCalendarView =(CalendarView)findViewById(R.id.calendarView);

        Intent incomingInteger = getIntent();
        ID = incomingInteger.getStringExtra("ID");
        IDAdmin.setText("ID = "+ID);
        X = ID.toString();

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date =  dayOfMonth + "/" + (month+1) + "/" + year;
                Log.d(TAG,"onSelectedDayChange: dd/MM/yyyy: "+date);


                Intent intent = new Intent(calendarActivityAdmin.this,calendarAdmin.class);
                intent.putExtra("date",date);
                intent.putExtra("ID",X);
                startActivity(intent);
            }
        });
    }
}
