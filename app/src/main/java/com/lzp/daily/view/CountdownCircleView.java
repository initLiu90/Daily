package com.lzp.daily.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CountdownCircleView extends View {
    private Paint mPaint;
    private Paint mTextPaint;
    private static final String TEXT = "跳过";
    private Rect mTextRect = new Rect();

    public CountdownCircleView(Context context) {
        this(context, null);
    }

    public CountdownCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountdownCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10f);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(35f);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mTextPaint.getTextBounds(TEXT, 0, TEXT.length(), mTextRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int lenght = Math.min(getWidth(), getHeight());
        float radius = lenght / 2 - mPaint.getStrokeWidth();
        float x = lenght / 2;
        float y = lenght / 2;
        canvas.drawCircle(x, y, radius, mPaint);

        float textHeight = (-mTextPaint.getFontMetrics().ascent - mTextPaint.getFontMetrics().descent);
        canvas.drawText(TEXT, x - mTextRect.width() / 2, y + textHeight / 2, mTextPaint);
    }
}
