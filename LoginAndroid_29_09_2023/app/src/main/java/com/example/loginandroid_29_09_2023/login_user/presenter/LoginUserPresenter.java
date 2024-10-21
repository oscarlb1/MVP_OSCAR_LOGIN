package com.example.loginandroid_29_09_2023.login_user.presenter;

import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.LoginUserContract;
import com.example.loginandroid_29_09_2023.login_user.model.LoginUserModel;

public class LoginUserPresenter implements LoginUserContract.presenter, LoginUserContract.model.OnLoginUserListener {
    private LoginUserContract.view view;
    private LoginUserContract.model model;

    public LoginUserPresenter(LoginUserContract.view view){
        this.view = view;
        model = new LoginUserModel();
    }

    @Override
    public void loginAction(User user) {
        if(user != null){
            model.loginUserAPI(user, this);
        } else {
            view.failureLogin("Thre are many empty fields");
        }
    }



    @Override
    public void onFinished(User user) {
        view.successLogin(user);
    }

    @Override
    public void onFailure(String messageError) {
        view.failureLogin(messageError);
    }
}
