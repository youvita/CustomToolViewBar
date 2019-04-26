package com.customtoolviewbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.customtoolviewbar.tool.ToolViewBar;

public class ResultActivity extends AppCompatActivity {
    private int i = 0;
    private ToolViewBar mToolViewBar = new ToolViewBar().instance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.btn_start).setEnabled(false);
                        mToolViewBar.onShowActionBar(ResultActivity.this);
                        new CountDownTimer(5000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                i++;
                                Log.d(">>>>", "run...." + i);
                            }

                            public void onFinish() {
                                findViewById(R.id.btn_start).setEnabled(true);
                                new ToolViewBar().instance().onDestroyProcess();
                            }
                        }.start();
                    }
                });
            }
        });

        findViewById(R.id.btn_go_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_back_main).setOnClickListener(new View.OnClickListener() {
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
                Log.d(">>>>", "Finished Result");
                mToolViewBar.onHideActionBar(ResultActivity.this);
            }
        });
    }
}
