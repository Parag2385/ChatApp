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
    private ItemClickListener clickListener;

    public MessageAdapter(ArrayList<ChatMessage> chatList, Context mContext) {
        this.chatList = chatList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = chatList.get(position);

        if (chatMessage.getUserId().equals(SendBird.getUserId())) {
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


    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }



    //
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_message, parent, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        boolean isPhoto = chatList.get(position).getPhotoUrl() != null;
//
//        if (isPhoto) {
//            holder.messageTextView.setVisibility(View.GONE);
//            holder.photoImageView.setVisibility(View.VISIBLE);
//            Glide.with(holder.photoImageView.getContext())
//                    .load(chatList.get(position).getPhotoUrl())
//                    .into(holder.photoImageView);
//        } else {
//            holder.messageTextView.setVisibility(View.VISIBLE);
//            holder.photoImageView.setVisibility(View.GONE);
//            holder.messageTextView.setText(chatList.get(position).getText());
//        }
//        holder.authorTextView.setText(chatList.get(position).getName());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return chatList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        TextView messageTextView;
//        TextView authorTextView;
//        ImageView photoImageView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            messageTextView = itemView.findViewById(R.id.messageTextView);
//            authorTextView = itemView.findViewById(R.id.nameTextView);
//            photoImageView = itemView.findViewById(R.id.photoImageView);
//        }
//    }
//

    private class SentMessageHolder extends RecyclerView.ViewHolder {
//        TextView messageText, timeText;

        TextView messageTextView;
        TextView authorTextView;
        ImageView photoImageView;

        public SentMessageHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            authorTextView = itemView.findViewById(R.id.nameTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
        }

        void bind(ChatMessage message) {
            messageTextView.setText(message.getText());

            // Format the stored timestamp into a readable String using method.
//            timeText.setText(Utils.formatDateTime(message.getTime()));
        }
    }


    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        TextView authorTextView;
        ImageView photoImageView;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            authorTextView = itemView.findViewById(R.id.nameTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
        }

        void bind(ChatMessage message) {
            messageTextView.setText(message.getText());

            // Format the stored timestamp into a readable String using method.
//            timeText.setText(Utils.formatDateTime(message.getTime()));
            authorTextView.setText(message.getName());

            // Insert the profile image from the URL into the ImageView.
//            Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }

}
