package com.example.parag.chatapp.core_firebase.logout;

/**
 * Created by parag on 09-02-2018.
 */

public class LogoutPresenter implements LogoutContract.Presenter, LogoutContract.OnLogoutListener {

    private LogoutContract.View mLogoutView;
    private LogoutContract.Interactor mLogoutInteractor;

    public LogoutPresenter(LogoutContract.View logoutView) {
        mLogoutView = logoutView;
        mLogoutInteractor = new LogoutInteractor(this);
    }

    @Override
    public void logout() {
        mLogoutInteractor.performFirebaseLogout();
    }

    @Override
    public void onSuccess(String message) {
        mLogoutView.onLogoutSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        mLogoutView.onLogoutFailure(message);
    }
}
