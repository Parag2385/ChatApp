package com.example.parag.chatapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by parag on 04-02-2018.
 */

public class RecyclerItemListener implements RecyclerView.OnItemTouchListener  {

    private RecyclerTouchListener listener;
    private GestureDetector gd;

    public interface RecyclerTouchListener {
        public void onClickItem(View v, int position) ;
        public void onLongClickItem(View v, int position);
    }

    public RecyclerItemListener(Context mContext, final RecyclerView mRecycler,
                                final RecyclerTouchListener listener) {
        this.listener = listener;
        gd = new GestureDetector(mContext,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e) {
                        // We find the view
                        View v = mRecycler.findChildViewUnder(e.getX(), e.getY());
                        // Notify the even
                        listener.onLongClickItem(v, mRecycler.getChildAdapterPosition(v));
                    }

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View v = mRecycler.findChildViewUnder(e.getX(), e.getY());
                        // Notify the even
                        listener.onClickItem(v, mRecycler.getChildAdapterPosition(v));
                        return true;
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        return ( child != null && gd.onTouchEvent(e));
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}