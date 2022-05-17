package com.codewithsean.pettinderadoption;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codewithsean.pettinderadoption.models.Photos;
import com.codewithsean.pettinderadoption.models.Profile;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    public TextView detail_age;
    public TextView detail_name;
    public TextView detail_gender;
    public TextView detail_description;
    public Photos detail_photo;
    ImageView detail_ivImage;
    public TextView detail_size;
    public TextView detail_contact;
    public TextView detail_attributes;
    public Button btnAdopt;

    public static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        detail_age = findViewById(R.id.detail_age);
        detail_name = findViewById(R.id.detail_PetName);
        detail_gender = findViewById(R.id.detail_gender);
        detail_description = findViewById(R.id.detail_description);
        detail_ivImage = findViewById(R.id.detail_PetImage);
        detail_size = findViewById(R.id.detail_size);
        detail_attributes = findViewById(R.id.detail_attributes);
        detail_contact = findViewById(R.id.detail_contact);
        btnAdopt = findViewById(R.id.btnAdopt);

        Profile profile = Parcels.unwrap(getIntent().getParcelableExtra("profiles"));
        detail_name.setText(profile.name);
        detail_gender.setText(profile.gender);
        detail_age.setText(profile.age);
        detail_size.setText(profile.size);
        detail_description.setText(profile.description);
        detail_attributes.setText(profile.attributes);
        detail_contact.setText(profile.contact);

        Glide.with(this).load(profile.photo.full).into(detail_ivImage);


        btnAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Adopt Me Clicked");
                goAdoptMeActivity();
            }
        });

    }

    private void goAdoptMeActivity() {
        Intent i = new Intent(this, EmailActivity.class);
        startActivity(i);
        finish();
    }

}
