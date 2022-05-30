package com.monstertechno.btcauthpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Home_Activity extends AppCompatActivity {

    Button left, right;
    ImageView object;
    Animation rotateLeft, rotateRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        left = findViewById(R.id.button);
        right = findViewById(R.id.button1);
        object = findViewById(R.id.object);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_anim);
                object.startAnimation(rotateLeft);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_anim);
                object.startAnimation(rotateRight);
            }
        });

    }
}