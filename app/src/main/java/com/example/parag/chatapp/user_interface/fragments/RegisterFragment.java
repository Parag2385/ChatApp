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
import com.example.parag.chatapp.core_firebase.register.RegisterContract;
import com.example.parag.chatapp.core_firebase.register.RegisterPresenter;
import com.example.parag.chatapp.core_firebase.users.add.AddUserContract;
import com.example.parag.chatapp.core_firebase.users.add.AddUserPresenter;
import com.example.parag.chatapp.user_interface.activities.UserListActivity;
import com.google.firebase.auth.FirebaseUser;


public class RegisterFragment extends Fragment implements RegisterContract.View, AddUserContract.View, View.OnClickListener {

    private EditText mEditTextEmail, mEditTextPassword, mEditTextUserName;
    private Button mButtonRegister;
    private ProgressBar mProgressBar;

    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        mEditTextEmail = view.findViewById(R.id.edit_text_email_id);
        mEditTextUserName = view.findViewById(R.id.edit_text_user_name);
        mEditTextPassword = view.findViewById(R.id.edit_text_password);
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
        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);
        mButtonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.button_register:
                onRegister(view);
                break;
        }

    }

    private void onRegister(View view) {
        String email = mEditTextEmail.getText().toString();
        String name = mEditTextUserName.getText().toString();
        String password = mEditTextPassword.getText().toString();

        mRegisterPresenter.register(getActivity(), name, email, password);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRegisterSuccess(FirebaseUser firebaseUser, String userName) {
        Toast.makeText(getActivity(), "Registration Successful! \nAdding user to database! ", Toast.LENGTH_SHORT).show();
        mAddUserPresenter.addUser(getActivity().getApplicationContext(), firebaseUser, userName);
    }

    @Override
    public void onRegisterFailure(String message) {
        mProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getActivity(), "Registration failed!+\n" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        mProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        UserListActivity.startActivity(getActivity(),
                Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void onAddUserFailure(String message) {
        mProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
