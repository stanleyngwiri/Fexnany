package com.ngwiri.flexnany;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.loginEmail) TextView mLoginEmail;
    @BindView(R.id.loginbuton) Button mLoginbuton;
    @BindView(R.id.SignUp_Text) TextView mSignUp_Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        mLoginbuton.setOnClickListener(this);
        mSignUp_Text.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mLoginbuton){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if (v == mSignUp_Text){
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }
}
