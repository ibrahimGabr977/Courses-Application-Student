package com.hope.igb.aic_student.screens.mainscreen.categorizedcourses.courseitems;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Course;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

import java.util.ArrayList;

public class VerticalCourseItemViewMvc<LISTENER extends HorizontalCourseItemViewMvc.CoursesItemListener>
        extends BaseObservableMvc<LISTENER> {





    private final TextView category_text;
    private final RecyclerView item_recyclerView;


    public VerticalCourseItemViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.main_courses_vertical_holder, parent, false));

        category_text = findViewById(R.id.category_text);
        item_recyclerView = findViewById(R.id.categorizedRecyclerView);

    }



    public void bindItemsViews(String category, ArrayList<Course> coursesList){
        category_text.setText(category);
        bindCategorizedRecyclerView(coursesList);
    }




    @SuppressLint("NotifyDataSetChanged")
    private void bindCategorizedRecyclerView(ArrayList<Course> coursesList){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        item_recyclerView.setLayoutManager(layoutManager);

        HorizontalCourseItemRecyclerAdapter<LISTENER> adapter = new HorizontalCourseItemRecyclerAdapter<>(coursesList, listener());

        item_recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private LISTENER listener(){
        for (LISTENER listener : getListeners())
            return listener;

        return null;
    }





}
