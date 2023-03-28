package com.hope.igb.aic_student.common.customviewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NonSwipedViewPager extends ViewPager {

    private boolean swipedEnabled;

    public NonSwipedViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.swipedEnabled = true;
    }





    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.swipedEnabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.swipedEnabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setSwipedEnabled(boolean swipedEnabled) {
        this.swipedEnabled = swipedEnabled;
    }
}



