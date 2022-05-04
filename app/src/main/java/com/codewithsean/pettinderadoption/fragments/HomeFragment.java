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
    public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJ1NkFmQmJGWTZIdnJYR29HWkZHbjB2T3BHdmluckx3SzN3WWZSbFBGelpudTdsZFJPMCIsImp0aSI6IjA5NDdjZjVlMTM2MzA1NDk0ZGJiMmNkYjljNmQ4NzQwYjZhOTFlMWIxOTMxMWNhY2EwOTNhNmM3MjdlYTFjOGNlNjYwN2U1MDVhOTIzNGIyIiwiaWF0IjoxNjUxNjMwNTc4LCJuYmYiOjE2NTE2MzA1NzgsImV4cCI6MTY1MTYzNDE3OCwic3ViIjoiIiwic2NvcGVzIjpbXX0.K87pdSbyrHnJgLYmZXmO2OlSQZqjp8bswlImeoRXfCruqSw_mH3EPLQnBHAQ2C5pb2xp7Az4EL8qM-c3kCF-UkfnwyOZ34Q2Zgm85uQ-_omEqdxoSkScQW5mz5EZfGLXPSugW_T1OHykH-G1091G7qc9nTmkT77Zmh9vX2GNiGatwGHhm2Y2C3elVEQ89XyghcM3LIGONB9VWo6gErDStQaykqD_ICNGLJiUWLHOrNn4fUaQpaa9Lh17bwq0-svGZOCJ-aISQOAGGkDfDpAIaDkcJnxoqhNHONnezGq5k9Y4nMDtwGCzhIlf7OoNUDlugW8dGDSu_MfWlXEyL3EyWw";
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

        rvProfiles = view.findViewById(R.id.rvProfiles);

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
