package com.customtoolviewbar.tool;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProgressToolbar {
    private static boolean mRunning;
    private static ActionBar mActionBar;
    private static ProgressToolbar mInstance;

    private OnProgressToolbarListener mListener;

    public void setOnProgressBarListener(OnProgressToolbarListener listener) {
        mListener = listener;
    }

    public ProgressToolbar instance() {
        if (mInstance == null) {
            mInstance = new ProgressToolbar();
        }
        return mInstance;
    }

    /*
     * init tool progress bar
     */
    public ProgressToolbar initProgressBar(AppCompatActivity context, String text, int size, int color, int elevation) {
        initTitleName(context, text, size, color, elevation);

        if (!isRunning()) {
            mInstance.onHideProgressBar(context);
        }else {
            mInstance.onShowProgressBar(context);
        }
        return mInstance;
    }

    /*
     * override theme
     */
    public void setProgressBarTheme(AppCompatActivity context, int theme_progress) {
        Resources.Theme theme = context.getTheme();
        theme.applyStyle(theme_progress, true);
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
    public void onHideProgressBar(AppCompatActivity context) {
        mActionBar = context.getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.hide();
            mRunning = false;
        }
    }

    /*
     * show progress bar
     */
    public void onShowProgressBar(AppCompatActivity context) {
        mActionBar = context.getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.show();
            mRunning = true;
        }
    }

    /*
     * destroy progress bar
     */
    public void onDestroyProgressBar() {
        mListener.onProgressCompleted();
    }

    @SuppressLint("InflateParams")
    /*
     * text: message for progress bar
     * elevation: under line for progress bar (0: no line, other have line by increasing value from 1 up)
     */
    private void initTitleName(AppCompatActivity context, String text, int size, int color, int elevation) {
        if (context.getSupportActionBar() != null) {
            ActionBar ab = context.getSupportActionBar();
            TextView textview = new TextView(context.getApplicationContext());
            textview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textview.setText(text);
            textview.setTextColor(ContextCompat.getColor(context, color));
            textview.setGravity(Gravity.CENTER);
            textview.setTextSize(size);
            ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ab.setDisplayShowCustomEnabled(true);
            ab.setCustomView(textview);
            ab.setElevation(elevation);
        }
    }

    public interface OnProgressToolbarListener {
        void onProgressCompleted();
    }
}
