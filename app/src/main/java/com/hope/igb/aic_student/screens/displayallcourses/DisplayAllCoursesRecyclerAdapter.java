package com.hope.igb.aic_student.screens.displayallcourses;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hope.igb.aic_student.networking.models.Course;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;



 class DisplayAllCoursesRecyclerAdapter extends
        RecyclerView.Adapter<DisplayAllCoursesRecyclerAdapter.AllCoursesViewHolder> implements
        DisplayAllCoursesItemViewMvc.DisplayItemListener {


    protected interface AdapterListener{
        void onCourseClicked(String course_id);
    }



    static class AllCoursesViewHolder extends RecyclerView.ViewHolder {

        private final DisplayAllCoursesItemViewMvc viewMvc;
         AllCoursesViewHolder(@NonNull DisplayAllCoursesItemViewMvc viewMvc) {
            super(viewMvc.getRootView());

            this.viewMvc = viewMvc;
        }
    }


    private final ArrayList<Course> courses;
    private final Set<AdapterListener> listeners = Collections.newSetFromMap(
            new ConcurrentHashMap<>(1));


     DisplayAllCoursesRecyclerAdapter(ArrayList<Course> courses) {
        this.courses = courses;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull AllCoursesViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.viewMvc.registerListener(this);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull AllCoursesViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.viewMvc.unregisterListener(this);
    }

    @NonNull
    @Override
    public AllCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DisplayAllCoursesItemViewMvc viewMvc = new DisplayAllCoursesItemViewMvc(LayoutInflater.from(parent.getContext()), parent);

        return new AllCoursesViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCoursesViewHolder holder, int position) {
        holder.viewMvc.bindRecyclerItems(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    protected void registerAdapterListener(AdapterListener listener){
         listeners.add(listener);
    }

    protected void unregisterAdapterListener(AdapterListener listener){
         listeners.remove(listener);
    }


    @Override
    public void onCourseClicked(String course_id) {
         for (AdapterListener listener: listeners)
             listener.onCourseClicked(course_id);
    }

}
