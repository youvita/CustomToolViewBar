package com.customtoolviewbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.customtoolviewbar.tool.ToolViewBar;

public class MainActivity extends AppCompatActivity {
    private ToolViewBar mToolViewBar = new ToolViewBar().instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mToolViewBar.onRefresh(this);
        mToolViewBar.setOnToolViewBarListener(new ToolViewBar.OnToolViewBarListener() {
            @Override
            public void onProcessCompleted() {
                Log.d(">>>>", "Finished Main");
                mToolViewBar.onHideActionBar(MainActivity.this);
            }
        });
    }
}
