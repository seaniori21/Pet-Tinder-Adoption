package com.codewithsean.pettinderadoption;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codewithsean.pettinderadoption.adapter.ProfileAdapter;
import com.codewithsean.pettinderadoption.models.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String GET_PETS = "https://api.petfinder.com/v2/animals";
    public static final String GET_TOKEN = "https://api.petfinder.com/v2/oauth2/token";
    public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJ1NkFmQmJGWTZIdnJYR29HWkZHbjB2T3BHdmluckx3SzN3WWZSbFBGelpudTdsZFJPMCIsImp0aSI6Im" +
            "EyYmUwZTI0YjhmNzIwMDA2M2U1MzdkNjUwYTgxN2Q4NTFlODVkNzdmZTcwNmFiY2RkMGFiYzkzZTg0OTNiYmM4ZTkwMTdhNjNiYTVlY2ZjIiwiaWF0IjoxNjQ4NDI4NjM2LCJuYmYiOjE2NDg0Mjg2MzYsImV4cCI6MTY0ODQzMjIzNiwic3ViIj" +
            "oiIiwic2NvcGVzIjpbXX0.SZS4V2p0MEX0ecd7GrGAke_uFGX-M1xCf-LamrW6Zg55T780-XjpcbW4-CCdn8MBqJ664_dV24y4TdFoMMs8d5nQegwkLYqDQv1QnLrWkKAvs0TTw97klsZ5xrAwK8QDq3qoFX8bZatG9bUXHpOnPt_1FaJ5_dPt0j" +
            "lpr2ZSfujyCOLauCItaiWMLjP0MHgg54gA_3s6lzx_R9Vxjs3AQ3DU1gM5f8SIozM1_FH6Uy8E6fImxb4IAKcdzGUo3jxP3eOjUt6JLAo3XmvBnK-hKaEBUAmRvYXsMOGc8HbvXMYgJMsq4i8yMDMoZlDBw4p3tgK4uA5K-PRJam0fjmXGWw";
    public static final String TAG = "MainActivity";

    List<Profile> profiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvProfiles = findViewById(R.id.rvProfiles);
        profiles = new ArrayList<>();

        ProfileAdapter profileAdapter = new ProfileAdapter(this, profiles);

        rvProfiles.setAdapter(profileAdapter);

        rvProfiles.setLayoutManager(new LinearLayoutManager(this));


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