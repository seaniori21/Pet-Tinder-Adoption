package com.codewithsean.pettinderadoption.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codewithsean.pettinderadoption.R;
import com.codewithsean.pettinderadoption.adapter.ProfileAdapter;
import com.codewithsean.pettinderadoption.models.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class HomeFragment extends Fragment {

    public static final String GET_PETS = "https://api.petfinder.com/v2/animals";
    public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJ1NkFmQmJGWTZIdnJYR29HWkZHbjB2T3BHdmluckx3SzN3WWZSbFBGelpudTdsZFJPMCIsImp0aSI6I" +
            "jg4ZjI3MDMwNGJkZTFiOTY5NTE4MzA1MTdlYWM1YzgzZDY2YzRjZjUzY2U1OGE2NTNjYjJhYzhkYjI0NDk0OTk0NzM2OTI4MjQ2OGJjNzMwIiwiaWF0IjoxNjUyNzUyNjc1LCJuYmYiOjE2NTI3NTI2NzUsImV4cCI6MTY1Mjc1NjI3NSwic3Vi" +
            "IjoiIiwic2NvcGVzIjpbXX0.rbVDfEYDr5jvYSEad2S4l6IxBdIzL-CdhSrI_CebPnkd_Zc81QgcELsIp8aY9J6AJLLXf5Gaqz79FHL9hYw1QQ69kTal352yBPlrKBsVVdTFtLSOvJlZWCCSa4P33JcfXMAYzB_EAsZNbN8iRS2DpJ-WZocKQVi" +
            "lsedB2jfnp3gkc5l_-8yqPhGQajtfkEqtgoKEkW9FzHxnlGfvgagXXSxpPq-P29jE3BEpONQttAq6OohaFfpJfaOh46n3_R1dAJM_wOS5c4NOBy_K5xMPsZjRIrvyfKtIoJMqZwD_qV4iRPzkEjLDobfxftpxwM5k9ZRSY2nz-waWGyuig9awzg";
    public static final String TAG = "HomeFragment";
    private RecyclerView rvProfiles;
    List<Profile> profiles;
    protected ProfileAdapter profileAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvProfiles = view.findViewById(R.id.rvMatch);

        profiles = new ArrayList<>();
        //create an adapter
        profileAdapter = new ProfileAdapter(getContext(), profiles);



        //set the adapter to RV
        rvProfiles.setAdapter(profileAdapter);

        //set layout manager to Rv
        rvProfiles.setLayoutManager(new LinearLayoutManager(getContext()));

        ProduceProfiles();

    }

    private void ProduceProfiles() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        /*params.put("grant_type", "client_credentials");
        params.put("client_id", "u6AfBbFY6HvrXGoGZFGn0vOpGvinrLwK3wYfRlPFzZnu7ldRO0");
        params.put("client_secret", "pRZsHOrVL5hflm1hAv7jnXNHz8hjYghWTKe2yQOh");
        curl -d "grant_type=client_credentials&client_id=u6AfBbFY6HvrXGoGZFGn0vOpGvinrLwK3wYfRlPFzZnu7ldRO0&client_secret=pRZsHOrVL5hflm1hAv7jnXNHz8hjYghWTKe2yQOh" https://api.petfinder.com/v2/oauth2/token
        */
        RequestHeaders headers = new RequestHeaders();
        headers.put("Authorization", "Bearer " + TOKEN);

        client.get(GET_PETS, headers, params ,new JsonHttpResponseHandler() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "OnJsonSuccess" + json);
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("animals");
                    Log.i(TAG,  "Results: " + results);

                    profiles.addAll(Profile.fromJsonArray(results));
                    profileAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Profiles: " + profiles.size());

                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "OnJsonFailure " + statusCode);
            }
        });
    }

}
