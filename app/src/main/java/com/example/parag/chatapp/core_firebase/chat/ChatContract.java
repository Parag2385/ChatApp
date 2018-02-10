package com.example.parag.chatapp.core_firebase.chat;

import android.content.Context;

import com.example.parag.chatapp.models.ChatMessage;

/**
 * Created by parag on 08-02-2018.
 */

public class ChatContract {

    public interface View{
        void onSendMessageSuccess();
        void onSendMessageFailure(String message);
        void onGetMessageSuccess(ChatMessage chat);
        void onGetMessageFailure(String message);
    }

    interface Presenter{
        void sendMessage(Context context, ChatMessage chat, String receiverFirebaseToken);
        void getMessage(String senderUid, String receiverUid);
    }

    interface Interactor {
        void sendMessageToFirebaseUser(Context context, ChatMessage chat, String receiverFirebaseToken);

        void getMessageFromFirebaseUser(String senderUid, String receiverUid);
    }

    interface OnSendMessageListener{
        void onSendMessageSuccess();

        void onSendMessageFailure(String message);
    }

    interface OnGetMessagesListener {
        void onGetMessagesSuccess(ChatMessage chat);

        void onGetMessagesFailure(String message);
    }
}
