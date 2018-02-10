package com.example.parag.chatapp.user_interface.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.parag.chatapp.R;
import com.example.parag.chatapp.core_firebase.login.LoginContract;
import com.example.parag.chatapp.core_firebase.login.LoginPresenter;
import com.example.parag.chatapp.user_interface.activities.RegisterActivity;
import com.example.parag.chatapp.user_interface.activities.UserListActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    LoginPresenter mLoginPresenter;

    private EditText mEditTextEmail, mEditTextPassword;
    private Button mButtonLogin, mButtonRegister;

    private ProgressBar mProgressBar;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        bindViews(view);
        return view;
    }

    private void bindViews(View view) {
        mEditTextEmail = view.findViewById(R.id.edit_text_email_id);
        mEditTextPassword = view.findViewById(R.id.edit_text_password);
        mButtonLogin = view.findViewById(R.id.button_login);
        mButtonRegister = view.findViewById(R.id.button_register);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mLoginPresenter = new LoginPresenter(this);

        mButtonLogin.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        switch (viewId) {
            case R.id.button_login:
                onLogin(view);
                break;

            case R.id.button_register:
                onRegister(view);
                break;
        }
    }

    private void onRegister(View view) {
        RegisterActivity.startActivity(getActivity());
    }

    private void onLogin(View view) {
        String emailId = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();

        mLoginPresenter.login(getActivity(), emailId, password);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoginSuccess(String message) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
        UserListActivity.startActivity(getActivity(), Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void onLoginFailure(String message) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Logged in successfully", Toast.LENGTH_SHORT).show();
    }
}
