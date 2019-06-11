package com.customtoolviewbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.customtoolviewbar.tool.ProgressToolbar;

@SuppressLint("Registered")
public class BassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new ProgressToolbar().instance().setProgressBarTheme(this, R.style.AppProgressBarTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ProgressToolbar().instance()
                .initProgressBar(this, "Processing...", 14, android.R.color.white, 5)
                .setOnProgressBarListener(new ProgressToolbar.OnProgressToolbarListener() {
                    @Override
                    public void onProgressCompleted() {
                        new ProgressToolbar().instance().onHideProgressBar(BassActivity.this);
                }
        });
    }
}
