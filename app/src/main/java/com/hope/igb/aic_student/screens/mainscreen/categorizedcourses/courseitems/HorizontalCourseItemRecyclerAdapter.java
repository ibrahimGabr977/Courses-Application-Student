package com.hope.igb.aic_student.screens.mainscreen.categorizedcourses.courseitems;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.networking.models.Course;

import java.util.ArrayList;

class HorizontalCourseItemRecyclerAdapter<LISTENER extends HorizontalCourseItemViewMvc.CoursesItemListener> extends
        RecyclerView.Adapter<HorizontalCourseItemRecyclerAdapter.CategorizedItemViewHolder>{




    static class CategorizedItemViewHolder extends RecyclerView.ViewHolder {

        private final HorizontalCourseItemViewMvc viewMvc;
        public CategorizedItemViewHolder(@NonNull HorizontalCourseItemViewMvc viewMvc) {
            super(viewMvc.getRootView());


            this.viewMvc = viewMvc;
        }
    }




    private final ArrayList<Course> certainCategoryCourses;
    private final LISTENER listener;




    HorizontalCourseItemRecyclerAdapter(ArrayList<Course> certainCategoryCourses, LISTENER listener) {
        this.certainCategoryCourses = certainCategoryCourses;
        this.listener = listener;
    }





    @Override
    public void onViewAttachedToWindow(@NonNull CategorizedItemViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.viewMvc.registerListener(listener);
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull CategorizedItemViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.viewMvc.unregisterListener(listener);
    }




    @NonNull
    @Override
    public CategorizedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HorizontalCourseItemViewMvc viewMvc = new HorizontalCourseItemViewMvc(LayoutInflater.from(parent.getContext()), parent);
        viewMvc.registerListener(listener);
        return new CategorizedItemViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorizedItemViewHolder holder, int position) {
        if (position< certainCategoryCourses.size())
        holder.viewMvc.bindCardItemData(certainCategoryCourses.get(position));
        else
            holder.viewMvc.bindSeeAll(certainCategoryCourses.get(0).getCourse_category());
    }




    @Override
    public int getItemCount() {
        return certainCategoryCourses.size()+1;
    }











}
