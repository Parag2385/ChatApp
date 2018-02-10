package com.example.parag.chatapp.core_firebase.users.getall;

import android.text.TextUtils;

import com.example.parag.chatapp.models.Users;
import com.example.parag.chatapp.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by parag on 09-02-2018.
 */

public class GetUsersInteractor implements GetUsersContract.Interactor {

    private GetUsersContract.OnGetAllUsersListener mGetAllUsersListener;

    public GetUsersInteractor(GetUsersContract.OnGetAllUsersListener mGetAllUsersListener) {
        this.mGetAllUsersListener = mGetAllUsersListener;
    }

    @Override
    public void getAllUsersFromFirebase() {
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_USERS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                List<Users> users = new ArrayList<>();
                while (dataSnapshots.hasNext()){
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    Users user = dataSnapshotChild.getValue(Users.class);
                    if (!TextUtils.equals(user.getUserId(), FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        users.add(user);
                    }
                }
                mGetAllUsersListener.onGetAllUsersSuccess(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mGetAllUsersListener.onGetAllUsersFailure(databaseError.getMessage());
            }
        });


    }

    @Override
    public void getChatUsersFromFirebase() {

    }
}
