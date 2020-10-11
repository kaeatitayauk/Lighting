package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_userxcal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_userxcal);

        final Button gotocal = (Button)findViewById(R.id.btn_adminxroom);
        //go to calculate for user
        gotocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(home_userxcal.this,calmainxuser.class);
                startActivity(its);
            }
        });

    }
}
