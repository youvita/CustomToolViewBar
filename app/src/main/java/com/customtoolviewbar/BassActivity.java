package com.customtoolviewbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.customtoolviewbar.tool.ToolViewBar;

public class BassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new ToolViewBar().instance().getTheme(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ToolViewBar().instance()
                .initToolViewBar(this, "Processing...", 5)
                .setOnToolViewBarListener(new ToolViewBar.OnToolViewBarListener() {
                    @Override
                    public void onProgressCompleted() {
                        new ToolViewBar().instance().onHideActionBar(BassActivity.this);
                }
        });
    }
}
