package com.example.parag.chatapp.core_firebase.chat;

import android.content.Context;

import com.example.parag.chatapp.models.ChatMessage;

/**
 * Created by parag on 10-02-2018.
 */

public class ChatPresenter implements ChatContract.Presenter,
        ChatContract.OnSendMessageListener, ChatContract.OnGetMessagesListener {

    private ChatContract.View mView;
    private ChatContract.Interactor mInteractor;

    public ChatPresenter(ChatContract.View mView) {
        this.mView = mView;
        this.mInteractor = new ChatInteractor(this, this);
    }

    @Override
    public void sendMessage(Context context, ChatMessage chat, String receiverFirebaseToken) {
        mInteractor.sendMessageToFirebaseUser(context, chat, receiverFirebaseToken);
    }

    @Override
    public void getMessage(String senderUid, String receiverUid) {
        mInteractor.getMessageFromFirebaseUser(senderUid, receiverUid);
    }

    @Override
    public void onSendMessageSuccess() {
        mView.onSendMessageSuccess();
    }

    @Override
    public void onSendMessageFailure(String message) {
        mView.onSendMessageFailure(message);
    }

    @Override
    public void onGetMessagesSuccess(ChatMessage chat) {
        mView.onGetMessageSuccess(chat);
    }

    @Override
    public void onGetMessagesFailure(String message) {
        mView.onGetMessageFailure(message);
    }

}
