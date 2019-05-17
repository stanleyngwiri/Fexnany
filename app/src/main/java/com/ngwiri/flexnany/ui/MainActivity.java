package com.ngwiri.flexnany.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.ngwiri.flexnany.services.MaidService;
import com.ngwiri.flexnany.Network;
import com.ngwiri.flexnany.R;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.ngwiri.flexnany.ui.SignUpActivity.view;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    boolean doubleBackToExitPressedOnce = false;
public static final String TAG = MainActivity.class.getSimpleName();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        getmaids method called

        getMaids();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //<--- CHECKING INTERNET CONNECTION START
        if(Network.isInternetAvailable(MainActivity.this))
            //returns true if internet available
        {

        }
        else
        {
            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "No Internet Connection");

        }

        //CHECKING INTERNET CONNECTION END --->


    }

    @Override
    public void onClick(View v) {

    }

    //FOR OVERLOW MENU TO INSERT LOGOUT ITEM
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // LOGOUT FUNCTION USING F\B
    private void logout() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void getMaids( ) {

        final MaidService maidService = new MaidService();
        
        MaidService.findMaids(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //EXIT APP ON DOUBLE BACK PRESS
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText( MainActivity.this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }



}