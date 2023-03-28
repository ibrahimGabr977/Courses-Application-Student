package com.hope.igb.aic_student.screens.mainscreen.categorizedcourses;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Course;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;
import com.hope.igb.aic_student.screens.mainscreen.categorizedcourses.courseitems.HorizontalCourseItemViewMvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class CoursesViewMvc<LISTENER extends HorizontalCourseItemViewMvc.CoursesItemListener>
        extends BaseObservableMvc<LISTENER> {


    private final RecyclerView coursesRecyclerView;

    CoursesViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.main_courses_recycler_adapter, parent, false));

        coursesRecyclerView = findViewById(R.id.coursesRecyclerView);
    }


    @SuppressLint("NotifyDataSetChanged")
    protected void bindListView(LinkedHashMap<String, ArrayList<Course>> coursesListsByCategories) {
        coursesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VerticalCoursesRecyclerAdapter<LISTENER> adapter =
                new VerticalCoursesRecyclerAdapter<>(coursesListsByCategories, listener());


        coursesRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


    private LISTENER listener(){
        for (LISTENER listener : getListeners())
            return listener;

        return null;
    }
}
