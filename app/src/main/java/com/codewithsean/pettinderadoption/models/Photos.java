package com.codewithsean.pettinderadoption.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Photos {

    public String full;


    public static Photos fromJson(JSONObject jsonObject) throws JSONException {
        Photos photo = new Photos();
        photo.full = jsonObject.getString("full");
        Log.i("Profile", "TESTTTTTTTTTTTTT___________________" + photo.full);
        //photo.name = "https://image.tmdb.org/t/p/w780/iPhDToxFzREctUA0ZQiYZamXsMy.jpg";
        return photo;
    }
}
