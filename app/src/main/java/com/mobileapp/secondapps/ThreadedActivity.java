package com.mobileapp.secondapps;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreadedActivity extends AppCompatActivity {

    ImageView iv;
    TextView txtVwHello;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        //get variable from previous activity
        iv=(ImageView) findViewById(R.id.imgVwProfile);
        Intent intent=getIntent();
        String strMsg= intent.getStringExtra("varStr1");
        txtVwHello=(TextView) findViewById(R.id.txtVwHello);
        txtVwHello.setText("Welcome to new activty wahai "+strMsg);


        btnSend=(Button)findViewById(R.id.btnSendBack);
        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Bitmap bp=((BitmapDrawable)iv.getDrawable()).getBitmap();
                Intent intent= new Intent();
                intent.putExtra("data",bp);
                setResult(2,intent);
                finish();
            }
        });
    }

    public void fnTakePic(View vw)
    {
        Runnable run=new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

                runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                txtVwHello.setText(txtVwHello.getText().toString()+" .. This is your Picture..");
                            }
                        }
                );
            }
        };
        Thread thr = new Thread(run);
        thr.start();
    }

    public void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        Bitmap bp=(Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }
}
