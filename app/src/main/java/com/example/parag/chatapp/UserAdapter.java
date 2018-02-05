package com.example.parag.chatapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by parag on 04-02-2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<Users> userList;
    private Context mContext;
    private ItemClickListener clickListener;

    public UserAdapter(ArrayList<Users> userList, Context mContext) {
        this.userList = userList;
        this.mContext = mContext;
    }

    public interface ItemClickListener {
        void mClick(View view, String userId, String userName);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_users, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mUserName.setText(userList.get(position).getUserName());
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mUserName;

        public ViewHolder(View itemView) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.userName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.mClick(view, userList.get(getAdapterPosition()).getUserId(),
                    userList.get(getAdapterPosition()).getUserName());
        }
    }
}
