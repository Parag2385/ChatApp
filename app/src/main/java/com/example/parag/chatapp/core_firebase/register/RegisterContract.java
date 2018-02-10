package com.example.parag.chatapp.core_firebase.register;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by parag on 10-02-2018.
 */

public class RegisterContract {

    public interface View {
        void onRegisterSuccess(FirebaseUser firebaseUser, String userName);

        void onRegisterFailure(String message);
    }

    interface Presenter {
        void register(Activity activity, String userName, String userEmail, String password);
    }

    interface Interactor {
        void registerToFirebase(Activity activity, String userName, String userEmail, String password);
    }

    interface OnRegisterListener {
        void onSuccess(FirebaseUser firebaseUser, String userName);

        void onFailure(String message);
    }
}
