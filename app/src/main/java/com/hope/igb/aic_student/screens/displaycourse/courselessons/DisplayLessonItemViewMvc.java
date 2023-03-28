package com.hope.igb.aic_student.screens.displaycourse.courselessons;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Lesson;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;


class DisplayLessonItemViewMvc extends BaseObservableMvc<DisplayLessonItemViewMvc.DisplayLessonItemListener> {



     protected interface DisplayLessonItemListener {
         void onLessonClicked(int lesson_index);
     }


     private final TextView title, duration;
     private final ImageView play_lesson;


    DisplayLessonItemViewMvc(LayoutInflater inflater, ViewGroup parent) {
         setRootView(inflater.inflate(R.layout.display_course_lesson_holder, parent, false));

         title = findViewById(R.id.lesson_title);
         duration = findViewById(R.id.lesson_duration);
         play_lesson = findViewById(R.id.play_lesson);
        ImageView lesson_image = findViewById(R.id.lesson_image);
         lesson_image.setClipToOutline(true);

     }



     protected void bindLessons(Lesson lesson, int position, boolean isBought) {
         play_lesson.getDrawable().setColorFilter(ContextCompat.getColor(getContext(),
                 (position == 0 || isBought)? R.color.blue_color: R.color.black_color ),
                 PorterDuff.Mode.MULTIPLY);

         play_lesson.setBackgroundResource((position == 0 || isBought)?
                 R.drawable.z_blue_stroke_circlebg : R.drawable.z_black_stroke_circlebg);

         title.setText(lesson.getLesson_title());
         duration.setText(lesson.getLesson_duration());




         getRootView().setOnClickListener(v ->{
             if (isBought || position == 0)
                 for (DisplayLessonItemListener listener : getListeners())
                     listener.onLessonClicked(position);

         });



     }



}
