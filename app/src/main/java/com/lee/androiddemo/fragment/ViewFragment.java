package com.lee.androiddemo.fragment;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lee.androiddemo.R;

public class ViewFragment extends BaseFragment {


    ImageView ivLogo;

    @Override
    protected int getContextView() {
        return R.layout.fragment_view;
    }

    @Override
    protected void findView() {
        ivLogo = (ImageView) rootView.findViewById(R.id.iv_logo);
    }

    @Override
    protected void initData() {

        ValueAnimator anim = ValueAnimator.ofFloat(0f, 3f, 2f, -5f);
        anim.setDuration(5000);
        anim.setRepeatCount(10000);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();

                ViewGroup.LayoutParams param = ivLogo.getLayoutParams();
                param.height = (int) (ivLogo.getHeight() + currentValue);
                param.width = (int) (ivLogo.getWidth() + currentValue);
                ivLogo.setLayoutParams(param);
            }
        });
        anim.setStartDelay(2000);
        anim.start();

        ObjectAnimator animator = ObjectAnimator.ofFloat(ivLogo, "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.setRepeatCount(1000);
        animator.setStartDelay(2000);
        animator.start();
    }

    @Override
    protected void initListener() {

    }
}
