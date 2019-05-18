package com.ngwiri.flexnany.services;

import com.ngwiri.flexnany.Constants;
import com.ngwiri.flexnany.models.Maids;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
                JSONObject maidsServiceJSON = new JSONObject(jsonData);
                JSONArray maidsJSON = maidsServiceJSON.getJSONArray("");


                for (int i=0; i< maidsJSON.length();i++){
                    JSONObject perMaidJSON = maidsJSON.getJSONObject(i);
                    String name = perMaidJSON.getString("name");
                    String msisdn = perMaidJSON.getString("msisdn");
                    String email = perMaidJSON.getString("email");
                    String description = perMaidJSON.getString("description");
                    String address = perMaidJSON.getString("address");
                    String rating = perMaidJSON.getString("rating");
                    String services = perMaidJSON.getString("services");
                    String experience = perMaidJSON.getString("experience");
                    String age = perMaidJSON.getString("age");
                    String status = perMaidJSON.getString("status");
                    Maids perMaid = new Maids(name, msisdn, email, description, address, rating, services, experience, age, status);
                    maids.add(perMaid);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maids;
    }

}
