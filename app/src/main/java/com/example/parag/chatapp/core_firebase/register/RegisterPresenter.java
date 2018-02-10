package com.example.parag.chatapp.core_firebase.register;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by parag on 10-02-2018.
 */

public class RegisterPresenter implements RegisterContract.Presenter, RegisterContract.OnRegisterListener {

    private RegisterContract.View mView;
    private RegisterInteractor mRegisterInteractor;

    public RegisterPresenter(RegisterContract.View mView) {
        this.mView = mView;
        mRegisterInteractor = new RegisterInteractor(this);
    }

    @Override
    public void register(Activity activity, String userName, String userEmail, String password) {
        mRegisterInteractor.registerToFirebase(activity, userName, userEmail, password);
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser, String userName) {
        mView.onRegisterSuccess(firebaseUser, userName);
    }

    @Override
    public void onFailure(String message) {
        mView.onRegisterFailure(message);
    }
}
