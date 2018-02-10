package com.example.parag.chatapp.core_firebase.users.getall;

import com.example.parag.chatapp.models.Users;

import java.util.List;

/**
 * Created by parag on 09-02-2018.
 */

public class GetUsersContract {

    public interface View {
        void onGetAllUsersSuccess(List<Users> users);

        void onGetAllUsersFailure(String message);
    }

    interface Presenter {
        void getAllUsers();
    }

    interface Interactor {
        void getAllUsersFromFirebase();

        void getChatUsersFromFirebase();

    }

    interface OnGetAllUsersListener {
        void onGetAllUsersSuccess(List<Users> users);

        void onGetAllUsersFailure(String message);

    }

}
