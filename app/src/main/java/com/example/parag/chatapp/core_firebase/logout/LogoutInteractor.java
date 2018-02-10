package com.example.parag.chatapp.core_firebase.logout;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by parag on 09-02-2018.
 */

public class LogoutInteractor implements LogoutContract.Interactor {

    private LogoutContract.OnLogoutListener mLogoutListener;

    public LogoutInteractor(LogoutContract.OnLogoutListener mLogoutListener) {
        this.mLogoutListener = mLogoutListener;
    }

    @Override
    public void performFirebaseLogout() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            mLogoutListener.onSuccess("Successfully logged out!");
        } else {
            mLogoutListener.onFailure("No user logged in yet!");
        }
    }
}
