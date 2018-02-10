package com.example.parag.chatapp.core_firebase.users.add;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.parag.chatapp.R;
import com.example.parag.chatapp.models.Users;
import com.example.parag.chatapp.utils.Constants;
import com.example.parag.chatapp.utils.SharedPreferenceUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by parag on 09-02-2018.
 */

public class AddUserInteractor implements AddUserContract.Interactor {

    private AddUserContract.OnAddUserToDatabaseListener mAddUserListener;

    public AddUserInteractor(AddUserContract.OnAddUserToDatabaseListener mAddUserListener) {
        this.mAddUserListener = mAddUserListener;
    }

    @Override
    public void addUserToDatabase(final Context context, FirebaseUser firebaseUser, String userName) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Users user = new Users(userName,
                firebaseUser.getEmail(),
                firebaseUser.getUid(),
                new SharedPreferenceUtils(context).getString(Constants.ARG_FIREBASE_TOKEN));

        databaseReference.child(Constants.ARG_USERS)
                .child(firebaseUser.getUid())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mAddUserListener.onSuccess(context.getString(R.string.user_successfully_added));
                        } else {
                            mAddUserListener.onFailure(context.getString(R.string.user_unable_to_add));
                        }
                    }
                });
    }
}
