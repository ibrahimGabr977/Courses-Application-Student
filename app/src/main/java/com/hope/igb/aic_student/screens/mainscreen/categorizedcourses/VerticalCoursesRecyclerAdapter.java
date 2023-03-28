package com.hope.igb.aic_student.screens.mainscreen.categorizedcourses;

import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.networking.models.Course;
import com.hope.igb.aic_student.screens.mainscreen.categorizedcourses.courseitems.HorizontalCourseItemViewMvc;
import com.hope.igb.aic_student.screens.mainscreen.categorizedcourses.courseitems.VerticalCourseItemViewMvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class VerticalCoursesRecyclerAdapter<LISTENER extends HorizontalCourseItemViewMvc.CoursesItemListener>
        extends RecyclerView.Adapter<VerticalCoursesRecyclerAdapter<LISTENER>.CategorizedCoursesHolder> {





     class CategorizedCoursesHolder extends RecyclerView.ViewHolder {
        private final VerticalCourseItemViewMvc<LISTENER> viewMvc;

        public CategorizedCoursesHolder(@NonNull VerticalCourseItemViewMvc<LISTENER> viewMvc) {
            super(viewMvc.getRootView());
            this.viewMvc = viewMvc;
        }
    }


    private final LinkedHashMap<String, ArrayList<Course>> coursesByCategories;
    private final LISTENER listener;

    VerticalCoursesRecyclerAdapter(LinkedHashMap<String, ArrayList<Course>> coursesByCategories, LISTENER listener) {
        this.coursesByCategories = coursesByCategories;
        this.listener = listener;
    }




    @Override
    public void onViewAttachedToWindow(@NonNull CategorizedCoursesHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.viewMvc.registerListener(listener);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CategorizedCoursesHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.viewMvc.unregisterListener(listener);
    }



    @NonNull
    @Override
    public CategorizedCoursesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VerticalCourseItemViewMvc<LISTENER> viewMvc = new VerticalCourseItemViewMvc<>(LayoutInflater.from(parent.getContext()), parent);
        viewMvc.registerListener(listener);
        return new CategorizedCoursesHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorizedCoursesHolder holder, int position) {
        holder.viewMvc.bindItemsViews(categoryKeyFrom(position), getCourseListBy(categoryKeyFrom(position)));
    }


    private String categoryKeyFrom(int position) {
        return (String) coursesByCategories.keySet().toArray()[position];
    }

    private ArrayList<Course> getCourseListBy(String category_key) {
        return coursesByCategories.get(category_key);
    }



    @Override
    public int getItemCount() {
        return coursesByCategories.size();
    }




}
