package com.example.samlee.lighting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Admin_Home_Admin extends AppCompatActivity {

    Button btn1;
    String ID,X;
    TextView IDAdmin,test;
    String no_room;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatabaseHelperAdmin myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin__home__admin);

        myDb = new DatabaseHelperAdmin(this);
        IDAdmin = (TextView)findViewById(R.id.textView);
        test = (TextView)findViewById(R.id.textView13);

        Intent incomingInteger = getIntent();
        ID = incomingInteger.getStringExtra("ID");
        IDAdmin.setText("ID = "+ID);

        X = ID.toString();

        final Spinner spinner = (Spinner)findViewById(R.id.spinner_admin_seroom);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.string_room,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_room = spinner.getSelectedItem().toString();
                test.setText(no_room.toString());
                preferences = getSharedPreferences("room",Admin_Home_Admin.MODE_PRIVATE);
                editor = preferences.edit();
                editor.putString("numberRoom",no_room);
                editor.commit();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//        editor.putString("numberRoom",no_room);
//        editor.commit();

        btn1 = (Button)findViewById(R.id.btn_adminxroom);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Admin_Home_Admin.this,mainmenu_admin.class);
                i.putExtra("ID",X);
                Admin_Home_Admin.this.startActivity(i);
            }
        });
    }
}
