package com.codewithsean.pettinderadoption;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    EditText etTo, etSubject, etMessage;
    Button btSend;

    public static final String GET_PETS = "https://api.petfinder.com/v2/animals";
    public static final String GET_TOKEN = "https://api.petfinder.com/v2/oauth2/token";
    public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJ1NkFmQmJGWTZIdnJYR29HWkZHbjB2T3BHdmluckx3SzN3WWZSbFBGelpudTdsZFJPMCIsImp0aSI6ImJkZDY1NTQ4MDY3NjAyYzZkODBlNTlkNDVmOWViNDhiMWUzZDM0NDZmZjgyMDNkZjI1NDIzYTExMThmNTExNjRiNDMzNTJiMTc3MTlmZDYzIiwiaWF0IjoxNjUyNTY0MTU4LCJuYmYiOjE2NTI1NjQxNTgsImV4cCI6MTY1MjU2Nzc1OCwic3ViIjoiIiwic2NvcGVzIjpbXX0.Pg3SVg68LU9BV3D33Hzd1Y0CKj98zaqgvUEi7Kfcrc95ZUpHvT4PgXYAkiaPC7n7gYBmvDo3hGi8oDAUs54dQk5-daoM0HCaUYzhtLNaR0jECCkazQm76u-av9R21CzHE_kAbUOdRH11U9a8wBBe4ZZ7IoKhd3NeLJ46zUNunt95LDGVlBM-gUpRAiXJU1tRY9DJikO7FrvZO2Rx5oHZsv3coe3JuGZsKNc5Jta3qMJunobHrt4wj29XLQ6f3cXVOO-udMVlyIXOpZ5lbB133GdYL2P5PufYDSLN3OH7BIbToNSPBnMHqT9O2SYvhb576-sixoouoeWoh-74ZO19SQ";
    public static final String TAG = "MainActivity";

    List<Profile> profiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTo = findViewById(R.id.et_to);
        etSubject = findViewById(R.id.et_subject);
        etMessage = findViewById(R.id.et_message);
        btSend = findViewById(R.id.bt_send);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW
                , Uri.parse("mailto:" + etTo.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, etMessage.getText().toString());
                startActivity(intent);
            }
        });

//        RecyclerView rvProfiles = findViewById(R.id.rvProfiles);
//        profiles = new ArrayList<>();
//
//        ProfileAdapter profileAdapter = new ProfileAdapter(this, profiles);
//
//        rvProfiles.setAdapter(profileAdapter);
//
//        rvProfiles.setLayoutManager(new LinearLayoutManager(this));
//
//
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        /*params.put("grant_type", "client_credentials");
//        params.put("client_id", "u6AfBbFY6HvrXGoGZFGn0vOpGvinrLwK3wYfRlPFzZnu7ldRO0");
//        params.put("client_secret", "pRZsHOrVL5hflm1hAv7jnXNHz8hjYghWTKe2yQOh");
//        curl -d "grant_type=client_credentials&client_id=u6AfBbFY6HvrXGoGZFGn0vOpGvinrLwK3wYfRlPFzZnu7ldRO0&client_secret=pRZsHOrVL5hflm1hAv7jnXNHz8hjYghWTKe2yQOh" https://api.petfinder.com/v2/oauth2/token
//        */
//        RequestHeaders headers = new RequestHeaders();
//        headers.put("Authorization", "Bearer " + TOKEN);
//
//        client.get(GET_PETS, headers, params ,new JsonHttpResponseHandler() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onSuccess(int statusCode, Headers headers, JSON json) {
//                Log.d(TAG, "OnJsonSuccess" + json);
//                JSONObject jsonObject = json.jsonObject;
//                try {
//                    JSONArray results = jsonObject.getJSONArray("animals");
//                    Log.i(TAG,  "Results: " + results);
//
//                    profiles.addAll(Profile.fromJsonArray(results));
//                    profileAdapter.notifyDataSetChanged();
//                    Log.i(TAG, "Profiles: " + profiles.size());
//
//                } catch (JSONException e) {
//                    Log.e(TAG, "Hit json exception", e);
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
//                Log.d(TAG, "OnJsonFailure " + statusCode);
//            }
//        });
    }
}