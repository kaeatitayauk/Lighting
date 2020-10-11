package com.example.samlee.lighting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contact_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_user);

        TextView detail = (TextView)findViewById(R.id.text_contact);
        detail.setText("        Lighting เป็นแอพลิเคชั่นเพื่อแจ้งค่าใช้ไฟฟ้า ซึ่งจะช่วยคุณให้สามารถทราบถึงค่าไฟฟ้า ณ เวลาดังกล่าว โดยแอพลิเคชั่นจะช่วยอำนวยความสะดวกโดยที่คุณไม่จำเป็นต้องคำนวณด้วยตนเองและยังมีคุณสมบัติในการประมวลผลรูปภาพมิเตอร์จากการอัปโหลดหรือถ่ายภาพ เพื่อให้คุณได้รับความสะดวกสบายในการใช้งานอย่างที่สุด\n" +
                "\n" +
                "       หากพบปัญหากรุณาติดต่อ\n" +
                "          081 - 436 - 1071  \n" +
                "            ทีมพัฒนาไลท์นิ่ง");

        Button back = (Button)findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent its = new Intent(Contact_user.this,mainmenu_user.class);
                startActivity(its);
            }
        });
    }
}
