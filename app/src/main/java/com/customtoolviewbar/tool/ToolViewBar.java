package com.customtoolviewbar.tool;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.customtoolviewbar.R;

public class ToolViewBar {
    private static boolean mRunning;
    private static ActionBar mActionBar;
    private static ToolViewBar mInstance;

    private OnToolViewBarListener mListener;

    public void setOnToolViewBarListener(OnToolViewBarListener listener) {
        mListener = listener;
    }

    public ToolViewBar instance() {
        if (mInstance == null) {
            mInstance = new ToolViewBar();
        }
        return mInstance;
    }

    /*
     * init tool progress bar
     */
    public ToolViewBar initToolViewBar(AppCompatActivity context, String text, int elevation) {
        initTitleName(context, text, elevation);
        if (!isRunning()) {
            mInstance.onHideActionBar(context);
        }else {
            mInstance.onShowActionBar(context);
        }
        return mInstance;
    }

    /*
     * override theme
     */
    public void getTheme(AppCompatActivity context) {
        Resources.Theme theme = context.getTheme();
        theme.applyStyle(R.style.AppProgressBarTheme, true);
    }

    /*
     * checking progress is running...
     */
    private boolean isRunning() {
        return mRunning;
    }


    /*
     * hide progress bar
     */
    public void onHideActionBar(AppCompatActivity context) {
        mActionBar = context.getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.hide();
            mRunning = false;
        }
    }

    /*
     * show progress bar
     */
    public void onShowActionBar(AppCompatActivity context) {
        mActionBar = context.getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.show();
            mRunning = true;
        }
    }

    /*
     * destroy progress bar
     */
    public void onDestroyProgress() {
        mListener.onProgressCompleted();
    }

    @SuppressLint("InflateParams")
    /*
     * text: message for progress bar
     * elevation: under line for progress bar (0: no line, other have line by increasing value from 1 up)
     */
    private void initTitleName(AppCompatActivity context, String text, int elevation) {
        if (context.getSupportActionBar() != null) {
            ActionBar ab = context.getSupportActionBar();
            TextView textview = new TextView(context.getApplicationContext());
            textview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textview.setText(text);
            textview.setTextColor(Color.WHITE);
            textview.setGravity(Gravity.CENTER);
            textview.setTextSize(10);
            ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ab.setDisplayShowCustomEnabled(true);
            ab.setCustomView(textview);
            ab.setElevation(elevation);
        }
    }

    public interface OnToolViewBarListener {
        void onProgressCompleted();
    }
}
