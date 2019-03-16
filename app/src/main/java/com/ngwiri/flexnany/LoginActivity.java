package com.ngwiri.flexnany;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static View view;
    private static Animation shakeAnimation;

    boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.loginLayout) LinearLayout mLoginLayout;
    @BindView(R.id.loginbuton) Button mLoginbuton;
    @BindView(R.id.SignUp_Text) TextView mSignUp_Text;
    @BindView(R.id.loginEmail) EditText mLoginEmail;
    @BindView(R.id.password) EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        mLoginbuton.setOnClickListener(this);
        mSignUp_Text.setOnClickListener(this);

         shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);


    }

    @Override
    public void onClick(View v) {
        if (v == mLoginbuton) {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);

            checkValidation();



        }

        if (v == mSignUp_Text){
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }

    // Check Validation before login
    private void checkValidation () {
        // Get email id and password
        String getEmailId = mLoginEmail.getText().toString();
        String getPassword = mPassword.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not

        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            mLoginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "Enter both credentials.");

        }
        // Check if email id is valid or not
        else if (!m.find())
            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "Your Email Id is Invalid.");
            // Else do login and do your stuff
        else
            Toast.makeText(getApplicationContext(), "Do Login.", Toast.LENGTH_SHORT)
                    .show();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
