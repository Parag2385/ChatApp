package com.example.parag.chatapp.core_firebase.chat;

import android.content.Context;
import android.util.Log;

import com.example.parag.chatapp.models.ChatMessage;
import com.example.parag.chatapp.utils.Constants;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by parag on 10-02-2018.
 */

public class ChatInteractor implements ChatContract.Interactor {

    private static final String TAG = "ChatInteractor";

    private ChatContract.OnSendMessageListener mOnSendMessageListener;
    private ChatContract.OnGetMessagesListener mOnGetMessageListener;

    public ChatInteractor(ChatContract.OnSendMessageListener onSendMessageListener) {
        this.mOnSendMessageListener = onSendMessageListener;
    }

    public ChatInteractor(ChatContract.OnGetMessagesListener onGetMessagesListener) {
        this.mOnGetMessageListener = onGetMessagesListener;
    }

    public ChatInteractor(ChatContract.OnSendMessageListener mOnSendMessageListener,
                          ChatContract.OnGetMessagesListener mOnGetMessageListener) {
        this.mOnSendMessageListener = mOnSendMessageListener;
        this.mOnGetMessageListener = mOnGetMessageListener;
    }

    @Override
    public void sendMessageToFirebaseUser(Context context, final ChatMessage chat, String receiverFirebaseToken) {
        final String room_type_1 = chat.getSenderUid() + "_" + chat.getReceiverUID();
        final String room_type_2 = chat.getReceiverUID() + "_" + chat.getSenderUid();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(Constants.ARG_CHAT_ROOMS).getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(room_type_1)) {
                    Log.e(TAG, "sendMessageToFirebaseUser1: " + room_type_1 + " exists");
                    databaseReference
                            .child(Constants.ARG_CHAT_ROOMS)
                            .child(room_type_1)
                            .child(String.valueOf(chat.getTime()))
                            .setValue(chat);
                } else if (dataSnapshot.hasChild(room_type_2)) {
                    Log.e(TAG, "sendMessageToFirebaseUser2: " + room_type_2 + " exists");
                    databaseReference
                            .child(Constants.ARG_CHAT_ROOMS)
                            .child(room_type_2)
                            .child(String.valueOf(chat.getTime()))
                            .setValue(chat);
                } else {
                    Log.e(TAG, "sendMessageToFirebaseUser: success");
                    databaseReference
                            .child(Constants.ARG_CHAT_ROOMS)
                            .child(room_type_1)
                            .child(String.valueOf(chat.getTime()))
                            .setValue(chat);
                    getMessageFromFirebaseUser(chat.getSenderUid(), chat.getReceiverUID());
                }
                mOnSendMessageListener.onSendMessageSuccess();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mOnSendMessageListener.onSendMessageFailure("Unable to send message: " + databaseError.getMessage());
            }
        });

    }

    @Override
    public void getMessageFromFirebaseUser(String senderUid, String receiverUid) {
        final String room_type_1 = senderUid + "_" + receiverUid;
        final String room_type_2 = receiverUid + "_" + senderUid;

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child(Constants.ARG_CHAT_ROOMS).getRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(room_type_1)) {
                    Log.e(TAG, "getMessageFromFirebaseUser1: " + room_type_1 + " exists");
                    FirebaseDatabase.getInstance().getReference()
                            .child(Constants.ARG_CHAT_ROOMS)
                            .child(room_type_1).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            ChatMessage chat = dataSnapshot.getValue(ChatMessage.class);
                            mOnGetMessageListener.onGetMessagesSuccess(chat);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            mOnGetMessageListener.onGetMessagesFailure("Unable to get message: " + databaseError.getMessage());
                        }
                    });
                } else if (dataSnapshot.hasChild(room_type_2)) {
                    Log.e(TAG, "getMessageFromFirebaseUser2: " + room_type_2 + " exists");
                    FirebaseDatabase.getInstance().getReference()
                            .child(Constants.ARG_CHAT_ROOMS)
                            .child(room_type_2).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            ChatMessage chat = dataSnapshot.getValue(ChatMessage.class);
                            mOnGetMessageListener.onGetMessagesSuccess(chat);
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            mOnGetMessageListener.onGetMessagesFailure("Unable to get message: " + databaseError.getMessage());
                        }
                    });
                } else {
                    Log.e(TAG, "getMessageFromFirebaseUser: no such room available");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mOnGetMessageListener.onGetMessagesFailure("Unable to get message: " + databaseError.getMessage());
            }
        });
    }
}
