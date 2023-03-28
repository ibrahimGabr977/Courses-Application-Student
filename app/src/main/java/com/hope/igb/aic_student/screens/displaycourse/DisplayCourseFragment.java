package com.hope.igb.aic_student.screens.displaycourse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Lesson;
import com.hope.igb.aic_student.common.base.BaseFragment;
import java.util.ArrayList;

public class DisplayCourseFragment extends BaseFragment
        implements
        DisplayCourseViewMvc.Listener {

    private DisplayCourseViewMvc viewMvc;
    private ArrayList<String> objectives;
    private ArrayList<Lesson> lessons;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewMvc = new DisplayCourseViewMvc(inflater, false);


        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        if (savedInstanceState == null) {
            initTestObjectives();
            initTestLessons();
        }


        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewMvc.registerListener(this);
        viewMvc.bindObjectivesRecyclerAdapter(objectives);
        viewMvc.bindLessonsRecyclerAdapter(lessons);
    }


    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);

    }


    private void initTestObjectives() {
        objectives = new ArrayList<>();

        objectives.add(getString(R.string.course_objective_test1));
        objectives.add(getString(R.string.course_objective_test2));
        objectives.add(getString(R.string.course_objective_test3));
        objectives.add(getString(R.string.course_objective_test4));

    }


    private void initTestLessons() {
        lessons = new ArrayList<>();

        lessons.add(new Lesson("", getString(R.string.lesson_title_test1),
                getString(R.string.lesson_duration_test1), "lesson_url"));

        lessons.add(new Lesson("", getString(R.string.lesson_title_test2),
                getString(R.string.lesson_duration_test2), "lesson_url"));

        lessons.add(new Lesson("", getString(R.string.lesson_title_test3),
                getString(R.string.lesson_duration_test3), "lesson_url"));

        lessons.add(new Lesson("", getString(R.string.lesson_title_test4),
                getString(R.string.lesson_duration_test4), "lesson_url"));

        lessons.add(new Lesson("", getString(R.string.lesson_title_test5),
                getString(R.string.lesson_duration_test5), "lesson_url"));


    }

    @Override
    public void onBackClicked() {
        requireActivity().onBackPressed();
    }


    @Override
    public void onLessonClicked(int lesson_index) {
        getScreenNavigator().toStudyCourse(lesson_index);

    }
}
