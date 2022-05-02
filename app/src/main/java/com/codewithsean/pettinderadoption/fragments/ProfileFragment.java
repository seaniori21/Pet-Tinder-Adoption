package com.codewithsean.pettinderadoption.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codewithsean.pettinderadoption.LoginActivity;
import com.codewithsean.pettinderadoption.R;
import com.parse.ParseUser;

public class ProfileFragment extends Fragment {

    //PROFILE PAGE AS OF RN ONLY HAS A USERIMAGE AND A USERNAME
    private ImageView pfpUserImage;
    private TextView pfpUserName;
    private Button btnLogout;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pfpUserImage = view.findViewById(R.id.pfpUserImage);
        pfpUserName = view.findViewById(R.id.pfpUserName);
        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity();
            }
        });


    }

    private void goLoginActivity() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        //finish();
    }



}
