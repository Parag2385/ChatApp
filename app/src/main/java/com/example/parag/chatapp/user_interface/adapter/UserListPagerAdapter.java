package com.example.parag.chatapp.user_interface.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.parag.chatapp.user_interface.fragments.UserFragment;

/**
 * Created by parag on 09-02-2018.
 */

public class UserListPagerAdapter extends FragmentPagerAdapter {
    private static final Fragment[] sFragments = new Fragment[]{UserFragment.newInstance(UserFragment.TYPE_ALL)};
    private static final String[] sTitle = new String[]{"ALL USERS"};

    public UserListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return sFragments[position];
    }

    @Override
    public int getCount() {
        return sFragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return sTitle[position];
    }
}
