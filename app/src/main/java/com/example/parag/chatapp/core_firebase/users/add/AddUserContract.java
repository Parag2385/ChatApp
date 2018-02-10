package com.example.parag.chatapp.core_firebase.users.add;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by parag on 09-02-2018.
 */

public class AddUserContract {
    public interface View {
        void onAddUserSuccess(String message);

        void onAddUserFailure(String message);
    }

    interface Presenter {
        void addUser(Context context, FirebaseUser firebaseUser, String userName);
    }

    interface Interactor {
        void addUserToDatabase(Context context, FirebaseUser firebaseUser, String userName);
    }

    interface OnAddUserToDatabaseListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
