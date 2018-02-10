package com.example.parag.chatapp.user_interface.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parag.chatapp.R;
import com.example.parag.chatapp.models.Users;

import java.util.List;

/**
 * Created by parag on 09-02-2018.
 */

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserListRecyclerAdapter.ViewHolder> {

    private List<Users> mUsers;

    public UserListRecyclerAdapter(List<Users> mUsers) {
        this.mUsers = mUsers;
    }

    public void add(Users user) {
        mUsers.add(user);
        notifyItemInserted(mUsers.size() - 1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Users user = mUsers.get(position);
        Log.v("UserListAdapter", "name: " + user.getUserName());


        String alphabet = user.getUserName().substring(0, 1);

        holder.mUsernameTextView.setText(user.getUserName());
        holder.mAlphabetTextView.setText(alphabet);


    }

    @Override
    public int getItemCount() {
        if (mUsers != null) {
            return mUsers.size();
        }
        return 0;
    }

    public Users getUser(int position) {
        return mUsers.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mAlphabetTextView, mUsernameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mAlphabetTextView = itemView.findViewById(R.id.text_view_user_alphabet);
            mUsernameTextView = itemView.findViewById(R.id.text_view_username);
        }
    }
}
