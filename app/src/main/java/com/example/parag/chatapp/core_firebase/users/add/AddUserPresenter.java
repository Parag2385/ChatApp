package com.example.parag.chatapp.core_firebase.users.add;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by parag on 09-02-2018.
 */

public class AddUserPresenter implements AddUserContract.Presenter, AddUserContract.OnAddUserToDatabaseListener {

    private AddUserContract.View mView;
    private AddUserInteractor mAddUserInteractor;

    public AddUserPresenter(AddUserContract.View mView) {
        this.mView = mView;
        mAddUserInteractor = new AddUserInteractor(this);
    }

    @Override
    public void addUser(Context context, FirebaseUser firebaseUser, String userName) {
        mAddUserInteractor.addUserToDatabase(context, firebaseUser, userName);
    }

    @Override
    public void onSuccess(String message) {
        mView.onAddUserSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        mView.onAddUserFailure(message);
    }


}
