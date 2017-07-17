package com.lee.androiddemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lee.androiddemo.R;

/**
 * Created by android on 2017/7/17.
 */

public class ViewPaint extends View {

    Bitmap bitmap;
    Paint paint;

    public ViewPaint(Context context) {
        this(context, null);
    }

    public ViewPaint(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mao);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, 0, 0, paint);

    }
}
