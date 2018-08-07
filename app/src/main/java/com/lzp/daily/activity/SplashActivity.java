package com.lzp.daily.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lzp.core.base.BaseActivity;
import com.lzp.daily.R;
import com.lzp.library.view.CountdownCircleView;

public class SplashActivity extends BaseActivity implements CountdownCircleView.CountdownListener {
    private CountdownCircleView mCountdownView;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        //首次启动时如果不在oncreate中设置隐藏底部导航，而是延迟到onWindowFocusChanged设置的话，会闪现底部导航栏黑条
        hideBottomNavigation();

        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_splash);

        mCountdownView = (CountdownCircleView) findViewById(R.id.activity_splash_countdown);
        mCountdownView.setListener(this);
        mCountdownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountdownView.stopCountdown();
                onEnd();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mCountdownView.isCountdowning()) {
            mCountdownView.startCountdown();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Activity从stop-start后，底部导航栏会重新出现，所以需要在此重新设置
        if (hasFocus) {
            hideBottomNavigation();
        }
    }

    @Override
    public void onEnd() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
