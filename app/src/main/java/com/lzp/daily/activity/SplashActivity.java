package com.lzp.daily.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lzp.core.base.BaseActivity;
import com.lzp.daily.R;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        hideSystemUI();
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_splash);
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
