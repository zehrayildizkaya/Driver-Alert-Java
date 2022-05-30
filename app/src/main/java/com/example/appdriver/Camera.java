package com.example.appdriver;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.otaliastudios.cameraview.CameraView;


public class Camera extends AppCompatActivity {
    public MediaPlayer alarmsesi;
    Button uygson;
    Integer sayma=0;
    String smsnumber;

    String a3= "Uyku gözlendi";
    String a4= " Az uyku gözlendi";
    String a5= " Çok uyku gözlendi";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        alarmsesi = MediaPlayer.create(Camera.this, R.raw.alarm);
        uygson = findViewById(R.id.btnCamera);


        Intent intent = getIntent();
        String s4 = intent.getStringExtra("numara3");
        smsnumber = s4;

        System.out.println("zehra"+ smsnumber);

        uygson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("smsto:" + smsnumber);
                Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                if(sayma<10) {
                    intent.putExtra("sms_body", a3);
                }
                else if(sayma>=10 && sayma<20){
                    intent.putExtra("sms_body", a5);
                }
                else {
                    intent.putExtra("sms_body", a4);
                }
                startActivity(intent);
            }
        });

        FaceDetectorOptions realTimeFaceDetectOp = new FaceDetectorOptions.Builder()
                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_NONE).setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE).setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .build();
        FaceDetector detector =  FaceDetection.getClient(realTimeFaceDetectOp);

        CameraView camera =  findViewById(R.id.camera);
        camera.setLifecycleOwner(this);

        camera.addFrameProcessor(frame -> {
            InputImage image;
            if(frame.getDataClass() == byte[].class) {
                image = InputImage.fromByteArray(frame.getData(), frame.getSize().getWidth(), frame.getSize().getHeight(), 0, InputImage.IMAGE_FORMAT_YV12);
            } else if(frame.getDataClass() == Image.class) {
                image = InputImage.fromMediaImage(frame.getData(), 0);
            } else {
                Log.i("HATA:", "image null döndü");
                return;
            }
            detector.process(image)
                    .addOnSuccessListener(faces -> {
                        for (Face face : faces) {
                            if(face.getHeadEulerAngleY() <-10) {
                                sayma++;
                                if(sayma>3) {
                                    SmsNumber();
                                }
                            }
                            else if(face.getHeadEulerAngleY() <10) {
                                sayma++;
                                if(sayma>3) {
                                    SmsNumber2();
                                }
                            }
                            else if(face.getHeadEulerAngleZ() >10) {
                                SmsNumber3();
                            }
                            else if(face.getHeadEulerAngleZ() <-10) {
                                sayma++;
                                if(sayma>3) {
                                    SmsNumber4();
                                }
                            }
                            else if(face.getHeadEulerAngleX() >10) {
                                SmsNumber5();
                            }
                            else if(face.getHeadEulerAngleX() <-10) {
                                SmsNumber6();
                            }
                            else if (face.getLeftEyeOpenProbability() <0.6) {
                                SmsNumber7();
                            } else if (face.getRightEyeOpenProbability() <0.6) {
                                SmsNumber8();
                            }

                        }
                    })
                    .addOnFailureListener(e -> Log.i("HATA:", "Proccess Başarısız oldu"));
        });
    }



    private void SmsNumber() {
        //Intent intent = new Intent(Camera.this, FinalPage.class);
        System.out.println("baş 1");
        alarmsesi.start();
        //startActivity(intent);
    }
    private void SmsNumber2() {
        //Intent intent = new Intent(Camera.this, FinalPage.class);
        System.out.println("baş 2");
        alarmsesi.start();
        //startActivity(intent);
    }
    private void SmsNumber3() {
        //Intent intent = new Intent(Camera.this, FinalPage.class);
        System.out.println("3");
        alarmsesi.start();
        //startActivity(intent);
    }
    private void SmsNumber4() {
        //Intent intent = new Intent(Camera.this, FinalPage.class);
        System.out.println("baş 4");
        alarmsesi.start();
        //startActivity(intent);
    }
    private void SmsNumber5() {
        //Intent intent = new Intent(Camera.this, FinalPage.class);
        System.out.println("baş 5");
        alarmsesi.start();
        //startActivity(intent);
    }
    private void SmsNumber6() {
        //Intent intent = new Intent(Camera.this, FinalPage.class);
        System.out.println("baş 6");
        alarmsesi.start();
        //startActivity(intent);
    }
    private void SmsNumber7() {
        //Intent intent = new Intent(Camera.this, FinalPage.class);
        System.out.println("baş 7");
        alarmsesi.start();
        //startActivity(intent);
    }
    private void SmsNumber8() {
        //Intent intent = new Intent(Camera.this, FinalPage.class);
        System.out.println("baş 8");
        alarmsesi.start();
        //startActivity(intent);
    }


}