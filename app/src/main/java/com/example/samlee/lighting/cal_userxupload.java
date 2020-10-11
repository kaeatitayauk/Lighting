package com.example.samlee.lighting;

        import  android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.graphics.Color;
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
        import java.io.File;

        import com.google.android.gms.vision.Frame;
        import com.google.android.gms.vision.text.TextBlock;
        import com.google.android.gms.vision.text.TextRecognizer;

public class cal_userxupload extends AppCompatActivity {

    TextView txtResult;
    StringBuilder  stringBuilder;
    String a;
    String data;
    double num0,calunit,money;
    String data2,data3;
    Integer unitxcal;
    float firstmeter;
    TextView txtcalresult,txtresultmoney;
    SharedPreferences sharedPreferences ,sharedPreferences2;
    Button btnProcessPick, btn_actionpick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_userxupload);

        txtcalresult = (TextView) findViewById(R.id.txt_output_cal);
        txtResult = (TextView) findViewById(R.id.txt_output_pick);
        txtresultmoney =(TextView)findViewById(R.id.txt_output_money);
        btn_actionpick = (Button) findViewById(R.id.btn_actionpick);


        btn_actionpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageshowGallery();
                btnProcessPick.setVisibility(View.VISIBLE);
            }
        });

        btnProcessPick = (Button) findViewById(R.id.btn_actionpickprocess);
        btnProcessPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageprocess();
                Bundle bundle = getIntent().getExtras();
                data = txtResult.getText().toString();
                data2 = txtcalresult.getText().toString();
                data3 = txtresultmoney.getText().toString();
                if (!data.isEmpty() && !data2.isEmpty() && !data3.isEmpty()) {
                    Intent intent = new Intent(cal_userxupload.this,calmainxuser.class);
                    intent.putExtra("lastmetercamera", data);
                    intent.putExtra("unitcamera", data2);
                    intent.putExtra("moneycamera", data3);
                    startActivity(intent);

                }
            }
        });
    }
    // process image

    private void imageprocess() {
        ImageView img = (ImageView)findViewById(R.id.imageView);
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
            int num3=N.length-1;
            double num1;
            for(int i=0;i<N.length;i++){
                int num2=10;
                num1= Integer.parseInt(N[i]);
                num0=num0+(num1*Math.pow(num2,num3));
                num3--;
            }
            //get value from setting
            sharedPreferences = getSharedPreferences("unit",Setting.MODE_PRIVATE);
            unitxcal = sharedPreferences.getInt("unit",0);

            sharedPreferences2 = getSharedPreferences("lastmeter",Setting.MODE_PRIVATE);
            firstmeter = sharedPreferences2.getFloat("lastmeter",0);

            //Integer a = Integer.parseInt(String.valueOf(unitxcal.toString()));
            //SharedPreferences sharedPreferences2 = getSharedPreferences("lastmeter",MODE_PRIVATE);
            //String lastmeterxcal = sharedPreferences.getString("lastmeter","");
            Bundle bundle = getIntent().getExtras();
            txtResult.setText(String.valueOf(num0).substring(0,4));
           // calunit = num0-Integer.parseInt(lastmeterxcal);
          //  money = calunit*Integer.parseInt(unitxcal);
            calunit  = num0-firstmeter;
            money = calunit*unitxcal;
            txtcalresult.setText(String.valueOf(calunit));
            //thousand baht
            if(String.valueOf(money).length()==6) {
                txtresultmoney.setText(String.valueOf(money).charAt(0) + "," + String.valueOf(money).substring(1));
            }
            //10K
            else if(String.valueOf(money).length()==7) {
                txtresultmoney.setText(String.valueOf(money).substring(0,2) + "," + String.valueOf(money).substring(2));
            }
            //100K
            else if(String.valueOf(money).length()==8) {
                txtresultmoney.setText(String.valueOf(money).substring(0,3) + "," + String.valueOf(money).substring(3));
            }
            //1M
            else if(String.valueOf(money).length()==9) {
                txtresultmoney.setText(String.valueOf(money).charAt(0)+ "," +String.valueOf(money).substring(1,4) + "," + String.valueOf(money).substring(4));
            }
            //other
            else
            {
                txtresultmoney.setText(String.valueOf(money));
            }
        }
    }
    private void imageshowGallery() {
        Intent intent = new Intent((Intent.ACTION_PICK));
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        intent.setData(uri);
        startActivityForResult(intent, 555);
    }
    @Override
    public void onActivityResult(int rqCode, int resCode, Intent intent) {
        if(resCode != RESULT_OK){
            return;
        }
        if(rqCode == 555){
            Uri uri =intent.getData();
            Intent intentCrop = new Intent("com.android.camera.action.CROP").
                    setDataAndType(uri, "image/*").putExtra("crop", true).
                    putExtra("aspectX", true).putExtra("aspectY",true).
                    putExtra("outputX",true).putExtra("outputY",true).
                    putExtra("scale",true).putExtra("return-data",true);

            File extDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            File imgFile = new File(extDir.getAbsolutePath()+"/profile.jpg");

            intentCrop.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(imgFile));

            intentCrop.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
            startActivityForResult(intentCrop,999);
        }else if (rqCode == 999){
            Bundle bundle = intent.getExtras();
            Bitmap bitmap = bundle.getParcelable("data");
            ImageView imageView =(ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }
    }

}

