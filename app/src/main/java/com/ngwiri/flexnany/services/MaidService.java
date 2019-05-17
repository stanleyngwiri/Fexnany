package com.ngwiri.flexnany.services;

import com.ngwiri.flexnany.Constants;
import com.ngwiri.flexnany.models.Maids;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MaidService {


    public static void findMaids( Callback callBack) {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MAIDS_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callBack);
    }


    public ArrayList<Maids> processResults (Response response){
        ArrayList<Maids> maids = new ArrayList<>();


        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONObject maidsJSON
            }

        }catch ()
    }


}
