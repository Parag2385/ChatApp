package com.example.parag.chatapp.core_firebase.logout;

/**
 * Created by parag on 09-02-2018.
 */

public class LogoutContract {
    public interface View {
        void onLogoutSuccess(String message);

        void onLogoutFailure(String message);
    }

    interface Presenter {
        void logout();
    }

    interface Interactor {
        void performFirebaseLogout();
    }

    interface OnLogoutListener {
        void onSuccess(String message);

        void onFailure(String message);
    }
}
