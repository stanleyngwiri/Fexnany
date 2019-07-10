package com.ngwiri.flexnany.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ngwiri.flexnany.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PolicyActivity extends AppCompatActivity {
    @BindView(R.id.policyWebView) WebView mPolicyWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        ButterKnife.bind(this);



        Toolbar toolbar = findViewById(R.id.policyWebViewToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
//        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PolicyActivity.this , SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

        });


        mPolicyWebView.loadUrl("https://stanleyngwiri.github.io/POLICY/");
        mPolicyWebView.getSettings().setJavaScriptEnabled(true);
        mPolicyWebView.setWebViewClient(new WebViewClient());

    }
}
