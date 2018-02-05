package com.example.parag.chatapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by parag on 04-02-2018.
 */

public class MessageAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private ArrayList<ChatMessage> chatList;
    private Context mContext;


    public MessageAdapter(ArrayList<ChatMessage> chatList, Context mContext) {
        this.chatList = chatList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = chatList.get(position);

        if (chatMessage.getUserId().equals(SendBird.getCurrnetUserId())) {
            // If the current user is the sender of the message
            Log.d("Adapter", "type message sent");
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            Log.d("Adapter", "create message sent");
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = chatList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                Log.d("Adapter", "bind message sent");
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        TextView authorTextView;
        TextView mTimeTextView;
        ImageView photoImageView;

        public SentMessageHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            authorTextView = itemView.findViewById(R.id.nameTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
            mTimeTextView = itemView.findViewById(R.id.timeTextView);
        }

        void bind(ChatMessage message) {
            String to = message.getzFriendUserId();

            if (to.equals(SendBird.getFriendUserID())) {
                messageTextView.setText(message.getText());
                authorTextView.setText("You");
                // Format the stored timestamp into a readable String using method.
                mTimeTextView.setText(Utils.formatDateTime(message.getTime()));
            } else {
                itemView.setVisibility(View.GONE);
            }

        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        TextView authorTextView;
        TextView mTimeTextView;
        ImageView photoImageView;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            authorTextView = itemView.findViewById(R.id.nameTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
            mTimeTextView = itemView.findViewById(R.id.timeTextView);
        }

        void bind(ChatMessage message) {
            String friendUserId = message.getzFriendUserId();
            String sendId = message.getUserId();
            if (friendUserId.equals(SendBird.getCurrnetUserId()) && SendBird.getFriendUserID().equals(sendId)) {
                messageTextView.setText(message.getText());
                authorTextView.setText(message.getName());
                // Format the stored timestamp into a readable String using method.
                mTimeTextView.setText(Utils.formatDateTime(message.getTime()));
            } else {
                itemView.setVisibility(View.GONE);
            }
        }
    }

}
