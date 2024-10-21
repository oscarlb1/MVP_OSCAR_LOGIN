package com.example.loginandroid_29_09_2023.login_user.model;

import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.LoginUserContract;
import com.example.loginandroid_29_09_2023.login_user.data.LoginUserData;
import com.example.loginandroid_29_09_2023.utils.ApiService;
import com.example.loginandroid_29_09_2023.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModel implements LoginUserContract.model {
    private static final String ipAPI = "http://192.168.55.247:3000/";

    @Override
    public void loginUserAPI(User user, OnLoginUserListener onLoginUserListener) {
        ApiService apiService = RetrofitClient.getClient(ipAPI).create(ApiService.class);

        User userL = new User();
        userL.setUsername(user.getUsername());
        userL.setPassword(user.getPassword());

        Call<LoginUserData> call = apiService.login(userL);

        call.enqueue(new Callback<LoginUserData>() {
            @Override
            public void onResponse(Call<LoginUserData> call, Response<LoginUserData> response) {
                if (response.isSuccessful()) {
                    LoginUserData myData = response.body();
                    if (myData != null && myData.getUser() != null) {
                        onLoginUserListener.onFinished(userL);
                    } else {
                        onLoginUserListener.onFailure("No se encuentra el usuario.");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginUserData> call, Throwable t) {
                handleNetworkError(t, onLoginUserListener);
            }
        });
    }

    private void handleNetworkError(Throwable t, OnLoginUserListener listener) {
        listener.onFailure("" + t);
    }
}
