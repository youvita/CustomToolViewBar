package com.customtoolviewbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.customtoolviewbar.tool.ToolViewBar;

public class DetailActivity extends AppCompatActivity {
    private ToolViewBar mToolViewBar = new ToolViewBar().instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                Log.d(">>>>", "Finished Detail");
                mToolViewBar.onHideActionBar(DetailActivity.this);
            }
        });
    }
}
