package com.example.appdriver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class FinalPage extends AppCompatActivity {

    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 100;
    TextView text, text2;
    Button btn;
    String num1;
    @Override
    public void onBackPressed() {
        FinalPage.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);
        btn =findViewById(R.id.buton);
        text = findViewById(R.id.changeText);
        text2 = findViewById(R.id.changeText2);
        Intent intent = getIntent();
        String s2 = intent.getStringExtra("email");
        String s3= intent.getStringExtra("numaraa");
        num1 =s3;
        text.setText(s2);
        text2.setText(s3);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraPermission()) {
                    enableCamera();
                } else {
                    requestPermission();
                }
            }
        });

    }

    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                CAMERA_PERMISSION,
                CAMERA_REQUEST_CODE
        );
    }

    private void enableCamera() {
        Intent intent = new Intent(this, Camera.class);
        intent.putExtra("numara3",num1);
        startActivity(intent);
    }
}