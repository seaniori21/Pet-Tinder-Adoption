package com.codewithsean.pettinderadoption.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Profile {
    public String id;

    public String getId() {
        return id;
    }

    public String age;
    public String name;
    public String gender;
    public String description;
    public Photos photo;
    public String contact;
    public String tags;
    public String size;


    //required by parcel
    public  Profile(){};

    public Profile(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString("name");
        gender = jsonObject.getString("gender");
        description = jsonObject.getString("description");
        age = jsonObject.getString("age");
        tags = jsonObject.getString("tags");
        contact = jsonObject.getString("contact");
        size = jsonObject.getString("size");



        //photo = new Photos();

        String temp = jsonObject.getString("photos");

        if(temp.equals("[]")){
            this.photo = new Photos();
            this.photo.full = "https://image.tmdb.org/t/p/w500/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg";
        }
        else {
            this.photo = new Photos();
            temp = temp.replace("[", "");
            temp = temp.replace("]", "");
            //photo = "https://image.tmdb.org/t/p/w780/iPhDToxFzREctUA0ZQiYZamXsMy.jpg";
            JSONObject newJsonArray = new JSONObject(temp);
            this.photo = Photos.fromJson(newJsonArray);
            Log.i("Profile", "RESULTS_________" + this.photo.full);
        }


    }

    public static List<Profile> fromJsonArray(JSONArray profileJsonArray) throws JSONException {
        List<Profile> profiles = new ArrayList<>();
        for(int i=0; i<profileJsonArray.length(); i++){
            profiles.add(new Profile(profileJsonArray.getJSONObject(i)));
        }
        return profiles;
    }







}
