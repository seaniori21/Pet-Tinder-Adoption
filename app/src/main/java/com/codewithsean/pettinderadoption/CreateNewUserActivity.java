package com.codewithsean.pettinderadoption;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateNewUserActivity extends AppCompatActivity {

    public static final String TAG = "CreateNewUserActivity";
    private EditText etNewUsername;
    private EditText etNewPassword;
    private Button btnSignedUp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnSignedUp = findViewById(R.id.btnSignedUp);

        btnSignedUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Clicked The btnLogin");
                String username = etNewUsername.getText().toString();
                String password = etNewPassword.getText().toString();
                loginUser(username,password);
            }
        });

    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to create new User: " + username);

        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    //TODO better error handling to tell user what is happening
                    Log.e(TAG, "Issue with creating new user", e);
                    return;
                }
                goLoginActivity();
                Toast.makeText(CreateNewUserActivity.this, "New User Created", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}