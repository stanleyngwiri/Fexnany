package com.ngwiri.flexnany.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ngwiri.flexnany.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.helpAction) LinearLayout mHelpAction;
    @BindView(R.id.notificationActions) LinearLayout mNotificationActions;
    @BindView(R.id.adsActions) LinearLayout mAdsActions;
    @BindView(R.id.rateAppAction) LinearLayout mRateAppAction;
    @BindView(R.id.infoAction) LinearLayout mInfoAction;
    @BindView(R.id.logoutAction) TextView mLogoutAction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);



        Toolbar toolbar = findViewById(R.id.settingsToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
//        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this , MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        mLogoutAction.setOnClickListener(this);
        mHelpAction.setOnClickListener(this);
        mAdsActions.setOnClickListener(this);
        mInfoAction.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v == mLogoutAction ){
            logout();
        }

        if (v == mHelpAction ){
            Intent mIntent = new Intent(Intent.ACTION_SENDTO);
            mIntent.setData(Uri.parse("mailto:"));
            mIntent.putExtra(Intent.EXTRA_EMAIL  , new String[] {"customerservice@flexnany.com"});
            mIntent.putExtra(Intent.EXTRA_SUBJECT, "CUSTOMER SERVICE");
            startActivity(Intent.createChooser(mIntent, "Complete Action Using"));
        }

        if (v == mAdsActions){
                Intent intent = new Intent(SettingsActivity.this, PolicyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
        }

        if (v == mInfoAction){
            Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    private void logout() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }



}
