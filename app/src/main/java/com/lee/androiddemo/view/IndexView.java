package com.lee.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lixinxin on 2017/4/10.
 */

public class IndexView extends View {


    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    private static final String TAG = "TAG";

    private Paint mPaint;

    private int cellWidth;

    private float cellHeight;


    public IndexView(Context context) {
        this(context, null);
    }

    public IndexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextSize(30);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < LETTERS.length; i++) {
            String text = LETTERS[i];
            // 计算坐标
            int x = (int) (cellWidth / 2.0f - mPaint.measureText(text) / 2.0f);
            // 获取文本的高度
            Rect bounds = new Rect();// 矩形
            mPaint.getTextBounds(text, 0, text.length(), bounds);
            int textHeight = bounds.height();
            int y = (int) (cellHeight / 2.0f + textHeight / 2.0f + i * cellHeight);

            // 根据按下的字母, 设置画笔颜色
            mPaint.setColor(touchIndex == i ? Color.GRAY : Color.WHITE);

            // 绘制文本A-Z
            canvas.drawText(text, x, y, mPaint);
        }


    }

    int touchIndex = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = -1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                // 获取当前触摸到的字母索引
                index = (int) (event.getY() / cellHeight);
                if (index >= 0 && index < LETTERS.length) {
                    // Toast.makeText(getContext(), LETTERS[index] + "", Toast.LENGTH_SHORT).show();

                    touchIndex = index;
                    // 判断是否跟上一次触摸到的一样
//                    if(index != touchIndex) {
//                        if(listener != null){
//                            listener.onLetterUpdate(LETTERS[index]);
//                        }
//                        Log.d(TAG, "onTouchEvent: " + LETTERS[index]);
//                        touchIndex = index;
//                    }

                    if (listener != null) {
                        listener.onIndex(LETTERS[index]);
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                index = (int) (event.getY() / cellHeight);
                if (index >= 0 && index < LETTERS.length) {
                    // 判断是否跟上一次触摸到的一样
                    if (index != touchIndex) {
                        touchIndex = index;
                        if (listener != null) {
                            listener.onIndex(LETTERS[index]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                touchIndex = -1;
                if(listener!=null){
                    listener.onHiddenLetter();
                }
                break;
            default:
                touchIndex = -1;
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 获取单元格的宽和高

        cellWidth = getMeasuredWidth();

        int mHeight = getMeasuredHeight();
        cellHeight = mHeight * 1.0f / LETTERS.length;
    }


    private OnIndexTypeListener listener;

    public void setOnIndexTypeListener(OnIndexTypeListener listener) {
        this.listener = listener;
    }


    public interface OnIndexTypeListener {

        void onIndex(String index);

        void onHiddenLetter();
    }

}
