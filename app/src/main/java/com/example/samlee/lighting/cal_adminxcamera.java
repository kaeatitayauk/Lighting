package com.example.samlee.lighting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;

public class cal_adminxcamera extends AppCompatActivity {
    TextView txtResult;
    StringBuilder  stringBuilder;
    String a;
    String lastmeterxcm1,unitcame2,moneycame3;
    double num0,calunit,money;
    TextView txtcalresult,txtmoneyresult;
    SharedPreferences sharedPreferences ,sharedPreferences2;
    Integer unitxcal;
    float firstmeter;
    Button btn_camera, btncamerapro;
    String ID,X;
    TextView Test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_adminxcamera);
        Test = (TextView)findViewById(R.id.textView);
        Intent incomingInteger = getIntent();
        ID = incomingInteger.getStringExtra("ID");
        Test.setText("ID = "+ID);
        X = ID.toString();

        txtResult = (TextView) findViewById(R.id.textView_procamera);
        txtcalresult= (TextView) findViewById(R.id.txt_output_calcamera);
        txtmoneyresult = (TextView)findViewById(R.id.txt_output_moneycamera);
        btn_camera = (Button)findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagecamera();
                btncamerapro.setVisibility(View.VISIBLE);
            }
        });

        btncamerapro = (Button)findViewById(R.id.btn_actioncameraprocess);
        btncamerapro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageprocesscamera();
                lastmeterxcm1= txtResult.getText().toString();
                unitcame2 = txtcalresult.getText().toString();
                moneycame3 = txtmoneyresult.getText().toString();
                if(!lastmeterxcm1.isEmpty()&& !unitcame2.isEmpty()&& !moneycame3 .isEmpty()){
                    Intent intent = new Intent(cal_adminxcamera.this,calmain_admin.class);
                    intent.putExtra("lastmetercamera",lastmeterxcm1);
                    intent.putExtra("unitcamera",unitcame2);
                    intent.putExtra("moneycamera",moneycame3);
                    intent.putExtra("ID",X);
                    startActivity(intent);

                }
            }
        });
    }
    private void imageprocesscamera() {
        ImageView img = (ImageView)findViewById(R.id.imageView_camera);
        BitmapDrawable bitmapDrawable =  (BitmapDrawable)img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational())
            Log.e("ERROR", "Detector dependencies are not yet available");
        else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            stringBuilder = new StringBuilder();

            for (int i = 0; i < items.size(); ++i) {

                TextBlock item = items.valueAt(i);
                stringBuilder.append(item.getValue());
            }
            a = stringBuilder.toString();
            String N[]= a.split(" ");
            txtResult.setText(a);

            int num3=N.length-1;
            double num1;
            for(int i=0;i<N.length;i++){
                int num2=10;

                num1= Integer.parseInt(N[i]);
                num0=num0+(num1*Math.pow(num2,num3));
                num3--;
            }

            sharedPreferences = getSharedPreferences("unit",Setting_ad.MODE_PRIVATE);
            unitxcal = sharedPreferences.getInt("unit",0);

            sharedPreferences2 = getSharedPreferences("lastmeter",Setting_ad.MODE_PRIVATE);
            firstmeter = sharedPreferences2.getFloat("lastmeter",0);

            txtResult.setText(String.valueOf(num0).substring(0,4));
            // double StartMeter =
            calunit = num0-firstmeter;
            money = calunit*unitxcal;
            txtcalresult.setText(String.valueOf(calunit));
            if(String.valueOf(money).length()==6) {
                txtmoneyresult.setText(String.valueOf(money).charAt(0) + "," + String.valueOf(money).substring(1));
            }
            //10K
            else if(String.valueOf(money).length()==7) {
                txtmoneyresult.setText(String.valueOf(money).substring(0,2) + "," + String.valueOf(money).substring(2));
            }
            //100K
            else if(String.valueOf(money).length()==8) {
                txtmoneyresult.setText(String.valueOf(money).substring(0,3) + "," + String.valueOf(money).substring(3));
            }
            //1M
            else if(String.valueOf(money).length()==9) {
                txtmoneyresult.setText(String.valueOf(money).charAt(0)+ "," +String.valueOf(money).substring(1,4) + "," + String.valueOf(money).substring(4));
            }
            //other
            else
            {
                txtmoneyresult.setText(String.valueOf(money));
            }
        }
    }
    private void imagecamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        intent.setData(uri);
        startActivityForResult(intent, 555);
    }

    @Override
    protected void onActivityResult(int rqCode, int resCode, Intent intent) {
        if (resCode != RESULT_OK) {
            return;
        }
        if (rqCode == 555) {
            Uri uri = intent.getData();
            Intent intentCrop = new Intent("com.android.camera.action.CROP").
                    setDataAndType(uri, "image/*").putExtra("crop", true).
                    putExtra("aspectX", true).putExtra("aspectY", true).
                    putExtra("outputX", true).putExtra("outputY", true).
                    putExtra("scale", true).putExtra("return-data", true);


            File extDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            File imgFile = new File(extDir.getAbsolutePath() + "/profile.jpg");

            intentCrop.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));

            intentCrop.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
            startActivityForResult(intentCrop, 999);
        } else if (rqCode == 999) {
            Bundle bundle = intent.getExtras();
            Bitmap bitmap = bundle.getParcelable("data");
            ImageView imageView = (ImageView) findViewById(R.id.imageView_camera);
            imageView.setImageBitmap(bitmap);
        }
    }
}