package com.example.loginandroid_29_09_2023;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.loginandroid_29_09_2023.login_user.view.LoginUserView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mainIntent = new Intent(MainActivity.this, LoginUserView.class);
        startActivity(mainIntent);
    }
}
