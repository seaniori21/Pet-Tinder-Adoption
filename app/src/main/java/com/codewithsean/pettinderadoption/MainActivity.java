package com.codewithsean.pettinderadoption;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.codewithsean.pettinderadoption.fragments.HomeFragment;
import com.codewithsean.pettinderadoption.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

//    public static final String GET_PETS = "https://api.petfinder.com/v2/animals";
//    public static final String GET_TOKEN = "https://api.petfinder.com/v2/oauth2/token";
//    public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJ1NkFmQmJGWTZIdnJYR29HWkZHbjB2T3BHdmluckx3SzN3WWZSbFBGelpudTdsZFJPMCIsImp0aSI6IjIwOTk5MWQwNmZiOTU3M2U5NTZjYzJhNDkwYjJjYWUxODYzODQwZWEzOTdhNzExYWVjMzkwNjQ4MWNmMjFiMjE5NzZmZTllYTE0YjYyODRkIiwiaWF0IjoxNjUxNDQ5MTMwLCJuYmYiOjE2NTE0NDkxMzAsImV4cCI6MTY1MTQ1MjczMCwic3ViIjoiIiwic2NvcGVzIjpbXX0.oDfyJ1qCj3KTSxfJmvz2WLCyWr1qODVZnpOwHkMLJtQK6DCq2WZPZw9j_tzv5Hid1KOTlhhR9hOzAR6hvjpXdRunr3t--E6O-3WQ6K_IpPF-H_wyJnuLHyj63qtueYMt9jcCTEUhxk9c-hld_k9ERXsSBwuFZArHnTKGB0PM856F7lCQDjLb-KazQPsMuhe97SVMq-tUXLkCo-WmkDCkcprj-29Z2JAF5-JWCP3Bc2_I0nYTkUUBKcGf9nk9_1iHEL6oeaAtvhNyR-2PT0IpCR2jDVMNd_uOIlD4zszBeNcfO57LyzxzB2ALE3CmluENnFReKJqyaI1jV5WXWKueZw";
    public static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_profile://goes to profile page
                        fragment = new ProfileFragment();
                        Toast.makeText(MainActivity.this,"profile", Toast.LENGTH_SHORT).show();
                        break;
//                    case R.id.action_chat:
//                        Toast.makeText(MainActivity.this,"chat", Toast.LENGTH_SHORT).show();
//                        //goChatActivity();
//                        break;
                    case R.id.action_home://goes to home
                    default:
                        Toast.makeText(MainActivity.this,"home", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        break;
                }

                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        //default view
        bottomNavigationView.setSelectedItemId(R.id.action_home);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu.setGroupVisible(R.menu.menu_bottom_navigation, false);
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        menu.findItem(R.id.btnLogOut).setVisible(false);
//        menu.findItem(R.id.action_home).setVisible(false);
//        menu.findItem(R.id.action_profile).setVisible(false);
//        menu.findItem(R.id.action_chat).setVisible(false);

        //menu.setGroupVisible(R.menu.menu_bottom_navigation, false);
        return true;
    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}