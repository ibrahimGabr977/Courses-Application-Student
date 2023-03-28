package com.hope.igb.aic_student.screens.coursestudying.util;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import com.hope.igb.aic_student.common.base.BaseObservable;

public class DraggableHorizontalOnTouch extends BaseObservable<DraggableHorizontalOnTouch.Listener> implements
        View.OnTouchListener {


    public interface Listener {
        void onDragLeft(int movement_distance);

        void onDragRight(int movement_distance);
    }

    private int dx = 0;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dx = (int) motionEvent.getRawX();
                break;

            case MotionEvent.ACTION_MOVE:
                int x = (int) motionEvent.getRawX();
                int horizontal_distance = (int) (x - dx);


                for (Listener listener : getListeners())
                    if (horizontal_distance < 0)
                        listener.onDragLeft(horizontal_distance);
                    else
                        listener.onDragRight(horizontal_distance);


                break;
        }
        return true;
    }

}
