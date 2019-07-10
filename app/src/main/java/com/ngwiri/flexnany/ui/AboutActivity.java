package com.ngwiri.flexnany.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ngwiri.flexnany.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.aboutWebView) WebView mAboutWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);


        Toolbar toolbar = findViewById(R.id.aboutWebViewToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
//        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this , SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

        });


        mAboutWebView.loadUrl("https://stanleyngwiri.github.io/POLICY/");
        mAboutWebView.getSettings().setJavaScriptEnabled(true);
        mAboutWebView.setWebViewClient(new WebViewClient());
    }
}
