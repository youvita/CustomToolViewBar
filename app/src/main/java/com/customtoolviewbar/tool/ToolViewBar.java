package com.customtoolviewbar.tool;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToolViewBar {
    private OnToolViewBarListener mListener;
    private static boolean mRunning;
    private static ActionBar actionBar;
    private static ToolViewBar mInstance;

    public ToolViewBar instance() {
        if (mInstance == null) {
            mInstance = new ToolViewBar();
        }
        return mInstance;
    }

    public void setOnToolViewBarListener(OnToolViewBarListener listener) {
        mListener = listener;
    }

    private boolean isRunning() {
        return mRunning;
    }

    public interface OnToolViewBarListener {
        void onProcessCompleted();
    }

    public void onHideActionBar(AppCompatActivity context) {
        actionBar = context.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
            mRunning = false;
        }
    }

    public void onShowActionBar(AppCompatActivity context) {
        initTitleName(context);
        actionBar = context.getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
            mRunning = true;
        }
    }

    public void onRefresh(AppCompatActivity context) {
        if (!isRunning()) {
            mInstance.onHideActionBar(context);
        }else {
            mInstance.onShowActionBar(context);
        }
    }

    public void onDestroyProcess() {
        mListener.onProcessCompleted();
    }


    @SuppressLint("InflateParams")
    private void initTitleName(AppCompatActivity context) {
        if (context.getSupportActionBar() != null) {
            ActionBar ab = context.getSupportActionBar();
            TextView textview = new TextView(context.getApplicationContext());
            textview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textview.setText("Processing...");
            textview.setTextColor(Color.WHITE);
            textview.setGravity(Gravity.CENTER);
            textview.setTextSize(10);
            ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ab.setDisplayShowCustomEnabled(true);
            ab.setCustomView(textview);
//            ab.setElevation(0);
        }
    }

}
