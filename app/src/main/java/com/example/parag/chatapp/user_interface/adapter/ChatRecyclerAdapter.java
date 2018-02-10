package com.example.parag.chatapp.user_interface.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parag.chatapp.R;
import com.example.parag.chatapp.models.ChatMessage;
import com.example.parag.chatapp.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by parag on 10-02-2018.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ME = 1;
    private static final int VIEW_TYPE_OTHER = 2;

    private List<ChatMessage> mChats;

    public ChatRecyclerAdapter(List<ChatMessage> mChats) {
        this.mChats = mChats;
    }

    public void add(ChatMessage chat) {
        mChats.add(chat);
        notifyItemInserted(mChats.size() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_ME:
                View viewMyChat = layoutInflater.inflate(R.layout.item_message_sent, parent, false);
                viewHolder = new MyChatViewHolder(viewMyChat);
                break;

            case VIEW_TYPE_OTHER:
                View viewOtherChat = layoutInflater.inflate(R.layout.item_message_received, parent, false);
                viewHolder = new OtherChatViewHolder(viewOtherChat);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (TextUtils.equals(mChats.get(position).getSenderUid(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            configureMyChatViewHolder((MyChatViewHolder) holder, position);
        } else {
            configureOtherChatViewHolder((OtherChatViewHolder) holder, position);
        }
    }

    private void configureMyChatViewHolder(MyChatViewHolder holder, int position) {
        ChatMessage chat = mChats.get(position);
        holder.mTextViewChatMessage.setText(chat.getMessage());
        holder.mTextViewUserName.setText("You");
        holder.mTextViewTime.setText(Utils.formatDateTime(chat.getTime()));
    }

    private void configureOtherChatViewHolder(OtherChatViewHolder holder, int position) {
        ChatMessage chat = mChats.get(position);

        String[] name = chat.getSenderName().split(" ");

        holder.mTextViewChatMessage.setText(chat.getMessage());
        holder.mTextViewUserName.setText(name[0]);
        holder.mTextViewTime.setText(Utils.formatDateTime(chat.getTime()));
    }

    @Override
    public int getItemCount() {
        if (mChats != null) {
            return mChats.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        if (TextUtils.equals(mChats.get(position).getSenderUid(),
                FirebaseAuth.getInstance().getCurrentUser().getUid())) {
            return VIEW_TYPE_ME;
        } else {
            return VIEW_TYPE_OTHER;
        }
    }


    public static class MyChatViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewChatMessage, mTextViewUserName, mTextViewTime;

        public MyChatViewHolder(View itemView) {
            super(itemView);
            mTextViewChatMessage = itemView.findViewById(R.id.messageTextView);
            mTextViewUserName = itemView.findViewById(R.id.nameTextView);
            mTextViewTime = itemView.findViewById(R.id.timeTextView);
        }
    }

    public static class OtherChatViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewChatMessage, mTextViewUserName, mTextViewTime;

        public OtherChatViewHolder(View itemView) {
            super(itemView);
            mTextViewChatMessage = itemView.findViewById(R.id.messageTextView);
            mTextViewUserName = itemView.findViewById(R.id.nameTextView);
            mTextViewTime = itemView.findViewById(R.id.timeTextView);
        }
    }
}
