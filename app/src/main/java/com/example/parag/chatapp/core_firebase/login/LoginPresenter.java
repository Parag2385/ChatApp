package com.example.parag.chatapp.core_firebase.login;

import android.app.Activity;

/**
 * Created by parag on 08-02-2018.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginContract.OnLoginListener {

    private LoginContract.View mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenter(LoginContract.View view) {
        this.mLoginView = view;
        mLoginInteractor = new LoginInteractor(this);
    }

    @Override
    public void login(Activity activity, String email, String password) {
        mLoginInteractor.performFirebaseLogin(activity, email, password);
    }

    @Override
    public void onSuccess(String message) {
        mLoginView.onLoginSuccess(message);

    }

    @Override
    public void onFailure(String message) {
        mLoginView.onLoginFailure(message);
    }
}
