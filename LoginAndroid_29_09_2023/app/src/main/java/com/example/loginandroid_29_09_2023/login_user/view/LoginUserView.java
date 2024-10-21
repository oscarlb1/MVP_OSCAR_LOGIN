package com.example.loginandroid_29_09_2023.login_user.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginandroid_29_09_2023.R;
import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.LoginUserContract;
import com.example.loginandroid_29_09_2023.login_user.presenter.LoginUserPresenter;

public class LoginUserView extends AppCompatActivity implements LoginUserContract.view {
    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    private LoginUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_m);
        presenter = new LoginUserPresenter(this);

        initComponents();
    }

    void initComponents() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                presenter.loginAction(user);
            }
        });
    }

    @Override
    public void successLogin(User user) {
        Toast.makeText(this, "Correct login with username: " + user.getUsername(), Toast.LENGTH_LONG).show(); // Cambiado a username
    }

    @Override
    public void failureLogin(String messageError) {
        Toast.makeText(this, messageError, Toast.LENGTH_LONG).show();
    }
}
