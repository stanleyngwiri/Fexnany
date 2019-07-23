package com.ngwiri.flexnany.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ngwiri.flexnany.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {

@BindView(R.id.splashImage) ImageView mSplashImage;
@BindView(R.id.AppName) TextView mAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Typeface fredokaOneFonts = Typeface.createFromAsset(getAssets(), "fonts/fredoka_one/FredokaOne-Regular.ttf" );
        mAppName.setTypeface(fredokaOneFonts);


        //<--- for FadeIn anim

//    Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
//    fadeIn.setDuration(1000);
//    mAppName.startAnimation(fadeIn);

        //for FadeIn END --->


//        HANDLES TIME TAKEN TO RUN THE SPLASH SCREEN ACTIVITY
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}
