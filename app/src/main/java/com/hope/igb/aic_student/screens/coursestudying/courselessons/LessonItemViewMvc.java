package com.hope.igb.aic_student.screens.coursestudying.courselessons;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Lesson;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;
import com.hope.igb.aic_student.screens.coursestudying.util.DraggableHorizontalOnTouch;


public class LessonItemViewMvc extends BaseObservableMvc<LessonItemViewMvc.LessonItemListener> implements
        DraggableHorizontalOnTouch.Listener {



    protected interface LessonItemListener {
        void onLessonClicked(String lesson_id, int last_clicked_position, int position);
        void onDragViewItemRight(int movement_distance);
        void onDragViewItemLeft(int movement_distance);
    }


    private  TextView title, duration;
    private  ImageView play_lesson;
    private static int last_clicked_item = -1;
    private final boolean isLandscapeView;
    private final boolean isBought;
    private DraggableHorizontalOnTouch draggableHorizontal;


    LessonItemViewMvc(boolean isLandscapeView, LayoutInflater inflater, ViewGroup parent, boolean isBought) {

        if (!isLandscapeView)
            setRootView(inflater.inflate(R.layout.display_course_lesson_holder, parent, false));
        else {
            setRootView(inflater.inflate(R.layout.study_course_lesson_holder, parent, false));
            draggableHorizontal = new DraggableHorizontalOnTouch();
        }


        this.isLandscapeView = isLandscapeView;
        this.isBought = isBought;

        init();



    }




    private void init(){
        title = findViewById(R.id.lesson_title);
        duration = findViewById(R.id.lesson_duration);
        play_lesson = findViewById(R.id.play_lesson);

        ImageView lesson_image = findViewById(R.id.lesson_image);
        lesson_image.setClipToOutline(true);
    }



    protected void bindLessonsView(Lesson lesson, int position){
        if (!isLandscapeView)
            bindPortraitView(lesson, position);
        else
            bindLandscapeView(lesson, position);


        title.setText(lesson.getLesson_title());
        duration.setText(lesson.getLesson_duration());

    }



    private void bindPortraitView(Lesson lesson, int position) {
        releaseDraggableHorizontal();

        play_lesson.getDrawable().setColorFilter(ContextCompat.getColor(getContext(),
                        (position == 0 || isBought)? R.color.blue_color: R.color.black_color ), PorterDuff.Mode.MULTIPLY);

        play_lesson.setBackgroundResource((position == 0 || isBought)?
                R.drawable.z_blue_stroke_circlebg : R.drawable.z_black_stroke_circlebg);



        getRootView().setOnClickListener(v -> {

            if (last_clicked_item != position && isBought) {
                for (LessonItemListener listener : getListeners())
                    listener.onLessonClicked(lesson.getLesson_id(), last_clicked_item, position);

                play_lesson.setImageResource(R.drawable.s_studying_playing);

                last_clicked_item = position;
            }


        });


    }







    private void bindLandscapeView(Lesson lesson, int position) {

        setDraggableHorizontal();

        getRootView().setOnClickListener(v -> {

            if (last_clicked_item != position && isBought) {
                for (LessonItemListener listener : getListeners())
                    listener.onLessonClicked(lesson.getLesson_id(), last_clicked_item, position);

                play_lesson.setImageResource(R.drawable.s_studying_playing);

                last_clicked_item = position;
            }


        });
    }

    private void setDraggableHorizontal(){
        draggableHorizontal.registerListener(this);
        getRootView().setOnTouchListener(draggableHorizontal);
    }

    private void releaseDraggableHorizontal(){
        if (draggableHorizontal != null){
            draggableHorizontal.unregisterListener(this);
            getRootView().setOnTouchListener(null);

            draggableHorizontal = null;
        }

    }


    public void resetItemToNonPlayedStatus() {
        if (!isLandscapeView)
            play_lesson.setImageResource(R.drawable.s_studying_play_lessond);
        else{
            play_lesson.getDrawable().setColorFilter(ContextCompat.getColor(getContext(),
                   R.color.blue_color), PorterDuff.Mode.MULTIPLY);
        }
    }



    @Override
    public void onDragLeft(int movement_distance) {
        for (LessonItemListener listener: getListeners())
            listener.onDragViewItemLeft(movement_distance);
    }

    @Override
    public void onDragRight(int movement_distance) {
        for (LessonItemListener listener: getListeners())
            listener.onDragViewItemRight(movement_distance);
    }

}
