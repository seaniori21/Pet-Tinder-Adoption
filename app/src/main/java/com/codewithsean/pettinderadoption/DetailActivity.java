package com.codewithsean.pettinderadoption;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        detail_age = findViewById(R.id.detail_age);
        detail_name = findViewById(R.id.detail_PetName);
        detail_gender = findViewById(R.id.detail_gender);
        detail_description = findViewById(R.id.detail_description);
        detail_ivImage = findViewById(R.id.detail_PetImage);

        Profile profile = Parcels.unwrap(getIntent().getParcelableExtra("profiles"));
        detail_name.setText(profile.name);
        detail_gender.setText(profile.gender);
        detail_age.setText(profile.age);
        detail_description.setText(profile.description);
        Glide.with(this).load(profile.photo.full).into(detail_ivImage);


    }
}
