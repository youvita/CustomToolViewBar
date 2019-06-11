package com.customtoolviewbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import com.customtoolviewbar.tool.ProgressToolbar;

public class ResultActivity extends BassActivity {
    private int i = 0;

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
                        new ProgressToolbar().instance().onShowProgressBar(ResultActivity.this);
                        new CountDownTimer(5000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                i++;
                                Log.d(">>>>", "run...." + i);
                            }

                            public void onFinish() {
                                i = 0;
                                findViewById(R.id.btn_start).setEnabled(true);
                                new ProgressToolbar().instance().onDestroyProgressBar();
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
}
