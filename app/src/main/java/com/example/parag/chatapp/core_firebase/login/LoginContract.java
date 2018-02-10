package com.example.parag.chatapp.core_firebase.login;

import android.app.Activity;

/**
 * Created by parag on 08-02-2018.
 */

public class LoginContract {
    public interface View {
        void onLoginSuccess(String message);

        void onLoginFailure(String message);
    }

    interface Presenter {
        void login(Activity activity, String email, String password);
    }

    interface Interactor {
        void performFirebaseLogin(Activity activity, String email, String password);
    }

    interface OnLoginListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
