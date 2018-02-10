package com.example.parag.chatapp.core_firebase.register;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by parag on 10-02-2018.
 */

public class RegisterInteractor implements RegisterContract.Interactor {

    private RegisterContract.OnRegisterListener mRegisterListener;

    public RegisterInteractor(RegisterContract.OnRegisterListener mRegisterListener) {
        this.mRegisterListener = mRegisterListener;
    }

    @Override
    public void registerToFirebase(Activity activity, final String userName, String userEmail, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            try {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(userName).build();
                                user.updateProfile(profileUpdates);
                            } finally {
                                mRegisterListener.onSuccess(task.getResult().getUser(), userName);
                            }

                        } else {
                            mRegisterListener.onFailure(task.getException().toString());
                        }

                    }
                });

    }
}
