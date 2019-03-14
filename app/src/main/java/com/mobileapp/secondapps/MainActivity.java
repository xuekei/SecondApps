package com.mobileapp.secondapps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {



    TextView txtvwAge;
    EditText edtName,edtYear;
    Button btnClick;
    ImageView iv;
    Calendar calendar=Calendar.getInstance();
    int year=calendar.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtvwAge =(TextView) findViewById(R.id.txtvwAge);
        edtName=(EditText) findViewById(R.id.edtTxtName);
        edtYear=(EditText) findViewById(R.id.edtYear);
        btnClick=(Button)findViewById(R.id.btnClick);
    }

    public void fnGreet(View vw)
    {
        String strName=edtName.getText().toString();
        String strYear=edtYear.getText().toString();
        if(strName.equalsIgnoreCase(""))
        {
            edtName.setError("Fill in Your Name.");
        }else if(strYear.equalsIgnoreCase(""))
        {
            edtYear.setError("Fill in your year of birth. ");
        }
        else

         {
        int Age = year - Integer.valueOf(strYear);
        txtvwAge.setText(" Helloo and welcome " + strName +". You are "+Age+" years old.");
         }
     }

    public void fnThreadActivity(View vw)
    {
       String strMsg=edtName.getText().toString();

       if(strMsg.equalsIgnoreCase(""))
       {
           edtName.setError("Fill in your name.");
       }
       else
       {
           Intent intent=new Intent(this,ThreadedActivity.class);
           intent.putExtra("varStr1",strMsg);
           startActivityForResult(intent,2);
       }
    }

    public void onActivityResult(int requestCode, int resultCode , Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==2)
        {
            iv=(ImageView) findViewById(R.id.imgFrmActivity2);
            Bitmap bp=(Bitmap) data.getExtras().get("data");
            txtvwAge.setText("Picture get from activity 2 as shown below :");
            iv.setImageBitmap(bp);
        }
    }
}
