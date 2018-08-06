package com.lzp.daily.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CountdownCircleView extends View {
    public CountdownCircleView(Context context) {
        this(context, null);
    }

    public CountdownCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountdownCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
