package com.example.parag.chatapp.user_interface.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parag.chatapp.R;
import com.example.parag.chatapp.core_firebase.chat.ChatContract;
import com.example.parag.chatapp.core_firebase.chat.ChatPresenter;
import com.example.parag.chatapp.models.ChatMessage;
import com.example.parag.chatapp.user_interface.adapter.ChatRecyclerAdapter;
import com.example.parag.chatapp.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class ChatFragment extends Fragment implements TextView.OnEditorActionListener, View.OnClickListener, ChatContract.View {

    private RecyclerView mChatRecyclerView;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;
    private ChatRecyclerAdapter mChatRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ChatPresenter mChatPresenter;

    public static ChatFragment newInstance(String userName, String userEmail, String receiverUID, String firebaseToken) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_RECEIVER, userName);
        args.putString(Constants.ARG_RECEIVER_EMAIL, userEmail);
        args.putString(Constants.ARG_RECEIVER_UID, receiverUID);
        args.putString(Constants.ARG_FIREBASE_TOKEN, firebaseToken);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_chat, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        // Initialize references to views
        mProgressBar = view.findViewById(R.id.progressBar);
        mChatRecyclerView = view.findViewById(R.id.recycler_view_message);
        mPhotoPickerButton = view.findViewById(R.id.photoPickerButton);
        mMessageEditText = view.findViewById(R.id.messageEditText);
        mSendButton = view.findViewById(R.id.sendButton);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mProgressBar.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(getActivity());
        mMessageEditText.setOnEditorActionListener(this);
        mSendButton.setOnClickListener(this);

        mChatRecyclerView.setLayoutManager(layoutManager);
        mChatPresenter = new ChatPresenter(this);
        mChatPresenter.getMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(),
                getArguments().getString(Constants.ARG_RECEIVER_UID));
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            sendMessage();
            return true;
        }
        return false;
    }

    private void sendMessage() {
        String message = mMessageEditText.getText().toString();
        String receiver = getArguments().getString(Constants.ARG_RECEIVER);
        String receiverEmail = getArguments().getString(Constants.ARG_RECEIVER_EMAIL);
        String receiverUID = getArguments().getString(Constants.ARG_RECEIVER_UID);
        String sender = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String senderUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String receiverFirebaseToken = getArguments().getString(Constants.ARG_FIREBASE_TOKEN);

        ChatMessage chat =
                new ChatMessage(message,
                        null, receiverEmail, receiver,
                        receiverUID, sender, senderUID,
                        System.currentTimeMillis(), receiverFirebaseToken);

        mChatPresenter.sendMessage(getActivity().getApplicationContext(),
                chat, receiverFirebaseToken);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.sendButton:
                sendMessage();
                break;
        }
    }

    @Override
    public void onSendMessageSuccess() {
        mMessageEditText.setText("");
        Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendMessageFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetMessageSuccess(ChatMessage chat) {
        mProgressBar.setVisibility(View.GONE);
        if (mChatRecyclerAdapter == null) {
            mChatRecyclerAdapter = new ChatRecyclerAdapter(new ArrayList<ChatMessage>());
            mChatRecyclerView.setAdapter(mChatRecyclerAdapter);
        }
        mChatRecyclerAdapter.add(chat);
        mChatRecyclerView.smoothScrollToPosition(mChatRecyclerAdapter.getItemCount() - 1);
    }

    @Override
    public void onGetMessageFailure(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
