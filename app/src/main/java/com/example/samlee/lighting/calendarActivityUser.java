package com.example.samlee.lighting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

/**
 * Created by samlee on 5/8/2018.
 */

public class calendarActivityUser extends AppCompatActivity{

    private static final String TAG = "calendarActivityUser";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendaractivityuser_layout);
        mCalendarView =(CalendarView)findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date =  dayOfMonth + "/" + (month+1) + "/" + year;
                Log.d(TAG,"onSelectedDayChange: dd/MM/yyyy: "+date);

                Intent intent = new Intent(calendarActivityUser.this,calendarUser.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }
}
