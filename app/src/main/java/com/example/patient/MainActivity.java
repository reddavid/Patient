package com.example.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation imageAnim, textAnim;
    ImageView image;
    TextView text, text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        imageAnim = AnimationUtils.loadAnimation(this,R.anim.kep_anim);
        textAnim = AnimationUtils.loadAnimation(this,R.anim.szoveg_anim);

        image = findViewById(R.id.kep);
        text = findViewById(R.id.szoveg);
        text2 = findViewById(R.id.szoveg2);

        image.setAnimation(imageAnim);
        text.setAnimation(textAnim);
        text2.setAnimation(textAnim);

        new Handler().postDelayed((new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        }),2500);
    }

}