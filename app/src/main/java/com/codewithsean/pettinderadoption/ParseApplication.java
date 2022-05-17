package com.codewithsean.pettinderadoption;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("WbLnXtqMvD1qWaUTSj204dlJe5gruWpLNjy5bbYC")
                .clientKey("PdhIZ2dbBoSMQHoTLPoruSAO993VRDjLI29XfZQ5")
                .server("https://parseapi.back4app.com").build());
    }

}
