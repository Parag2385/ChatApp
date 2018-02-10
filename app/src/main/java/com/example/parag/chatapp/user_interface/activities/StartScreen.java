package com.example.parag.chatapp.user_interface.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.parag.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class StartScreen extends AppCompatActivity {
    private static final int DELAY_TIME_MS = 2000;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    UserListActivity.startActivity(StartScreen.this);
                } else {
                    LoginActivity.startIntent(StartScreen.this);
                }

                finish();

            }
        };

        mHandler.postDelayed(mRunnable, DELAY_TIME_MS);
    }
}
