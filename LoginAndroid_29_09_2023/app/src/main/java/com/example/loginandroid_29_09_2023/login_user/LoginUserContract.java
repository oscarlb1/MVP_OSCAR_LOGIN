package com.example.loginandroid_29_09_2023.login_user;

import com.example.loginandroid_29_09_2023.beans.User;

public interface LoginUserContract {
    interface view{
        void successLogin(User user);
        void failureLogin(String messageError);
    }

    interface presenter{
        void loginAction(User user);
    }

    interface model{
        interface OnLoginUserListener{
            void onFinished(User user);
            void onFailure(String messageError);
        }

        void loginUserAPI(User user, OnLoginUserListener onLoginUserListener);
    }
}
