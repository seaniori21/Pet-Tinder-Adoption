package com.codewithsean.pettinderadoption.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.codewithsean.pettinderadoption.LoginActivity;
import com.codewithsean.pettinderadoption.R;
import com.parse.ParseUser;

public class ProfileFragment extends Fragment {

    //PROFILE PAGE AS OF RN ONLY HAS A USERIMAGE AND A USERNAME
    public static final String TAG = "ProfileFragment";
    private ImageView pfpUserImage;
    private TextView pfpUserName;
    private Button btnLogout;
    private TextView pfpEmail;
    private TextView pfpBirthday;
    private TextView pfpPhoneNumber;
    private TextView pfpInterests;
    ParseUser currentUser;



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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pfpUserImage = view.findViewById(R.id.pfpUserImage);
        pfpUserName = view.findViewById(R.id.pfpUserName);
//        btnLogout = view.findViewById(R.id.btnLogout);
        pfpEmail = view.findViewById(R.id.pfpEmail);
        pfpBirthday = view.findViewById(R.id.pfpBirthday);
        pfpPhoneNumber = view.findViewById(R.id.pfpPhoneNumber);
        pfpInterests = view.findViewById(R.id.pfpInterests);

        pfpUserImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_profile_image_backup));
        //.transform(new RoundedCornersTransformation(50, 2))

        currentUser = ParseUser.getCurrentUser();
        if(currentUser != null){
            Log.d(TAG, "User exists and goes into profile");
            pfpUserName.setText(currentUser.getUsername());//for the username
            pfpEmail.setText("  Email: " + currentUser.get("email"));//TODO get real email later
            pfpBirthday.setText(" Birthday: " + currentUser.get("birthday"));
            pfpPhoneNumber.setText("  Phone Number: " + currentUser.get("phoneNumber"));
            pfpInterests.setText(" Interests: " + currentUser.get("interests"));


            //ParseFile image = currentUser.get
//            Glide.with(getContext()).load("http://via.placeholder.com/300.png").into(pfpUserImage);
            Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg")
                    .transform(new CircleCrop())
                    .into(pfpUserImage);

        }
        else{
            Log.d(TAG, "User is logged out for some reason and needs to sign back in");
            goLoginActivity();
        }

//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ParseUser.logOut();
//                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
//                goLoginActivity();
//            }
//        });


    }

    private void goLoginActivity() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        //finish();
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        //menu.findItem(R.id.btnLogOut).setVisible(true);
    }

    //what happens when you click icon????
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnLogOut){
            //icon has been clicked
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
            goLoginActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
