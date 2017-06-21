package com.lee.androiddemo.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.view.View;


import java.util.ArrayList;

/**
 * Created by Single on 2016/3/7.
 */
public class AppointmentView extends RelativeLayout {
    private static final int RIPPLE_COUNT = 5;
    private static final int RIPPLE_DURATION = 4000;
    private static final int RIPPLE_DELAY = RIPPLE_DURATION / RIPPLE_COUNT;
    private static final float RIPPLE_SCALE = 6.0f;
    private static final int RADIUS = 18;
    private static final int STROKE_WIDTH = 4;
    private static final int COLOR = 0Xff34cca5;

    private boolean isRunning;
    private Paint mPaint;
    private AnimatorSet mAnimatorSet;
    private ArrayList<Animator> mAnimators;
    private ArrayList<RippleView> mRippleViews = new ArrayList<>();
    private LayoutParams mLayoutParams;

    public AppointmentView(Context context) {
        super(context);
        init(context);
    }

    public AppointmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppointmentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        if (isInEditMode()) {
            return;
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(COLOR);
        mPaint.setStyle(Paint.Style.FILL);
        mLayoutParams = new LayoutParams(dip2px(context, (RADIUS + STROKE_WIDTH)), dip2px(context, (RADIUS + STROKE_WIDTH)));
        mLayoutParams.addRule(CENTER_IN_PARENT, TRUE);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimators = new ArrayList<>();

        for (int i = 0; i < RIPPLE_COUNT; i++) {
            RippleView rippleView = new RippleView(getContext());
            addView(rippleView, mLayoutParams);

            final ObjectAnimator scaleX = ObjectAnimator
                    .ofFloat(rippleView, "ScaleX", 1.0f, RIPPLE_SCALE);
            scaleX.setRepeatCount(ObjectAnimator.INFINITE);
            scaleX.setRepeatMode(ObjectAnimator.RESTART);
            scaleX.setDuration(RIPPLE_DURATION);
            scaleX.setStartDelay(i * RIPPLE_DELAY);
            mAnimators.add(scaleX);

            final ObjectAnimator scaleY = ObjectAnimator
                    .ofFloat(rippleView, "ScaleY", 1.0f, RIPPLE_SCALE);
            scaleY.setRepeatCount(ObjectAnimator.INFINITE);
            scaleY.setRepeatMode(ObjectAnimator.RESTART);
            scaleY.setDuration(RIPPLE_DURATION);
            scaleY.setStartDelay(i * RIPPLE_DELAY);
            mAnimators.add(scaleY);

            final ObjectAnimator alpha = ObjectAnimator.ofFloat(rippleView, "alpha", 1.0f, 0f);
            alpha.setRepeatCount(ObjectAnimator.INFINITE);
            alpha.setRepeatMode(ObjectAnimator.RESTART);
            alpha.setStartDelay(i * RIPPLE_DELAY);
            alpha.setDuration(RIPPLE_DURATION);
            mAnimators.add(alpha);
        }
        mAnimatorSet.playTogether(mAnimators);
    }

    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void startAppointment() {
        if (!isRunning()) {
            for (RippleView rippleView : mRippleViews) {
                rippleView.setVisibility(VISIBLE);
            }
            mAnimatorSet.start();
            isRunning = true;
        }
    }

    public void stopAppointment() {
        if (isRunning()) {
            mAnimatorSet.end();
            isRunning = false;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int radiu = getWidth() / 6 / 2;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private class RippleView extends View {
        public RippleView(Context context) {
            super(context);
            this.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int radius = Math.min(getWidth(), getHeight()) / 2;
            canvas.drawCircle(radius, radius, radius - STROKE_WIDTH, mPaint);
        }
    }
}