package com.codewithsean.pettinderadoption.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Photos {

    public String full;

    public Photos() {}


    public static Photos fromJson(JSONObject jsonObject) throws JSONException {
        Photos photo = new Photos();
        photo.full = jsonObject.getString("full");
        Log.i("Profile", "photo link: " + photo.full);
        //photo.name = "https://image.tmdb.org/t/p/w780/iPhDToxFzREctUA0ZQiYZamXsMy.jpg";
        return photo;
    }
}
