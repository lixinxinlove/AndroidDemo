package com.lee.androiddemo.view;

/**
 * Created by lixinxin on 2017/4/23.
 * 水波纹动画
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;

import com.lee.androiddemo.R;

public class WaveBezierView extends View implements View.OnClickListener {

//    private float mStartPointX;
//    private float mStartPointY;
//
//    private float mEndPointX;
//    private float mEndPointY;
//
//    private float mFlagPointOneX;
//    private float mFlagPointOneY;
//    private float mFlagPointTwoX;
//    private float mFlagPointTwoY;

    private Path mPath;
    private Path mPath1;
    private Path mPath2;

    private Paint mPaintBezier;
    private Paint mPaintBezier1;
    private Paint mPaintBezier2;
//    private Paint mPaintFlag;
//    private Paint mPaintFlagText;

    private int mWaveLength;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mCenterY;
    private int mWaveCount;

    private ValueAnimator mValueAnimator;
    private ValueAnimator mValueAnimator1;
    private ValueAnimator mValueAnimator2;
    private int mOffset;
    private int mOffset1;
    private int mOffset2;

    public WaveBezierView(Context context) {
        super(context);
    }

    public WaveBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setColor(getResources().getColor(R.color.myColor));
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setStyle(Paint.Style.FILL_AND_STROKE);


        mPaintBezier1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier1.setColor(getResources().getColor(R.color.color2));
        mPaintBezier1.setStrokeWidth(8);
        mPaintBezier1.setStyle(Paint.Style.FILL_AND_STROKE);


        mPaintBezier2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier2.setColor(getResources().getColor(R.color.color1));
        mPaintBezier2.setStrokeWidth(8);
        mPaintBezier2.setStyle(Paint.Style.FILL_AND_STROKE);


        mWaveLength = 500;
    }

    public WaveBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPath = new Path();
        mPath1 = new Path();
        mPath2 = new Path();
        setOnClickListener(this);

        mScreenHeight = h;
        mScreenWidth = w;
        mCenterY = h / 2;

        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath1.reset();
        mPath2.reset();

        mPath.moveTo(-mWaveLength + mOffset, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + mOffset, mCenterY + 60, -mWaveLength / 2 + i * mWaveLength + mOffset, mCenterY);
            mPath.quadTo(-mWaveLength / 4 + i * mWaveLength + mOffset, mCenterY - 60, i * mWaveLength + mOffset, mCenterY);
        }
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaintBezier);


        mPath1.moveTo(-mWaveLength + mOffset1, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath1.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + mOffset1, mCenterY + 70, -mWaveLength / 2 + i * mWaveLength + mOffset1, mCenterY);
            mPath1.quadTo(-mWaveLength / 4 + i * mWaveLength + mOffset1, mCenterY - 70, i * mWaveLength + mOffset1, mCenterY);
        }
        mPath1.lineTo(mScreenWidth, mScreenHeight);
        mPath1.lineTo(0, mScreenHeight);
        mPath1.close();
        canvas.drawPath(mPath1, mPaintBezier1);


        mPath2.moveTo(-mWaveLength + mOffset2, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath2.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + mOffset2, mCenterY + 75, -mWaveLength / 2 + i * mWaveLength + mOffset2, mCenterY);
            mPath2.quadTo(-mWaveLength / 4 + i * mWaveLength + mOffset2, mCenterY - 75, i * mWaveLength + mOffset2, mCenterY);
        }
        mPath2.lineTo(mScreenWidth, mScreenHeight);
        mPath2.lineTo(0, mScreenHeight);
        mPath2.close();
        canvas.drawPath(mPath2, mPaintBezier2);
    }

    @Override
    public void onClick(View view) {
        mValueAnimator = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset = (int) valueAnimator.getAnimatedValue();
            }
        });
        mValueAnimator.start();

        mValueAnimator1 = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimator1.setDuration(2000);
        mValueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator1.setInterpolator(new AnticipateOvershootInterpolator());
        mValueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset1 = (int) valueAnimator.getAnimatedValue();
            }
        });
        mValueAnimator1.start();

        mValueAnimator2 = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimator2.setDuration(2500);
        mValueAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator2.setInterpolator(new AnticipateOvershootInterpolator());
        mValueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset2 = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator2.start();
    }
}

