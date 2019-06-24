package com.ngwiri.flexnany.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.ngwiri.flexnany.Network;
import com.ngwiri.flexnany.R;
import com.ngwiri.flexnany.adapters.maidsListAdapter;
import com.ngwiri.flexnany.models.Maids;
import com.ngwiri.flexnany.services.MaidService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.ngwiri.flexnany.ui.SignUpActivity.view;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    boolean doubleBackToExitPressedOnce = false;
    public static final String TAG = MainActivity.class.getSimpleName();
    public ArrayList<Maids> mMaids = new ArrayList<>();
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.maidsRecyclerView)
    RecyclerView mMaidsRecyclerView;
    private maidsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        getmaids method called
        getMaids();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //<--- CHECKING INTERNET CONNECTION START
        if (Network.isInternetAvailable(MainActivity.this))
        //returns true if internet available
        {

        } else {
            new CustomToast().Show_Toast(getApplicationContext(), view,
                    "No Internet Connection");

        }

        //CHECKING INTERNET CONNECTION END --->

        mSwipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
//                        Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        getMaids();
                    }
                }
        );


    }



    /*
     * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
     * performs a swipe-to-refresh gesture.
     */


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

    private void getMaids() {

        final MaidService maidService = new MaidService();

        MaidService.findMaids(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    mMaids = maidService.processResults(response);
                    Log.d("Response", response.body().string());

                } catch (Exception ex) {

                }

                //create and set an instance of the LayoutManager the RecyclerView requires

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new maidsListAdapter(getApplicationContext(), mMaids);
                        mMaidsRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        mMaidsRecyclerView.setLayoutManager(layoutManager);
                        mMaidsRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
