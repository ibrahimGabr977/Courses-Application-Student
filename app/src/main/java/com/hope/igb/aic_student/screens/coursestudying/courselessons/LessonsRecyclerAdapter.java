package com.hope.igb.aic_student.screens.coursestudying.courselessons;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.networking.models.Lesson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LessonsRecyclerAdapter extends RecyclerView.Adapter<LessonsRecyclerAdapter.LessonsViewHolder> implements
        LessonItemViewMvc.LessonItemListener {




    public interface LessonsAdapterListener{
        void onLessonClicked(String lesson_id, int last_clicked_position, int position);
        void onDragViewItemRight(int movement_distance);
        void onDragViewItemLeft(int movement_distance);
    }


    public static class LessonsViewHolder extends RecyclerView.ViewHolder {
        private final LessonItemViewMvc viewMvc;

        public LessonsViewHolder(LessonItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            this.viewMvc = viewMvc;
        }

        public LessonItemViewMvc getViewMvc() {
            return viewMvc;
        }
    }




    private final boolean isBought;
    private  boolean isLandscapeView;
    private final ArrayList<Lesson> lessons;
    private final LayoutInflater inflater;
    private final Set<LessonsAdapterListener> listeners =
            Collections.newSetFromMap(new ConcurrentHashMap<>(1));




    public LessonsRecyclerAdapter(LayoutInflater inflater, ArrayList<Lesson> lessons, boolean isBought) {
        this.inflater = inflater;
        this.lessons = lessons;
        this.isBought = isBought;

    }

    public void setViewOrientation(boolean isLandscapeView){
        this.isLandscapeView = isLandscapeView;
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull LessonsViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.viewMvc.unregisterListener(this);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull LessonsViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.viewMvc.registerListener(this);
    }

    @NonNull
    @Override
    public LessonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LessonItemViewMvc viewMvc = new LessonItemViewMvc(isLandscapeView, inflater, parent, isBought);
        return new LessonsViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsViewHolder holder, int position) {
        holder.viewMvc.bindLessonsView(lessons.get(position), position);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }



    public void registerAdapterListener(LessonsAdapterListener listener){
        listeners.add(listener);
    }


    public void unregisterAdapterListener(LessonsAdapterListener listener){
        listeners.remove(listener);
    }
    @Override
    public void onLessonClicked(String lesson_id, int last_clicked_position, int position) {
        for (LessonsAdapterListener listener : listeners)
            listener.onLessonClicked(lesson_id, last_clicked_position, position);
    }

    @Override
    public void onDragViewItemRight(int movement_distance) {
        for (LessonsAdapterListener listener : listeners)
            listener.onDragViewItemRight(movement_distance);
    }

    @Override
    public void onDragViewItemLeft(int movement_distance) {
        for (LessonsAdapterListener listener : listeners)
            listener.onDragViewItemLeft(movement_distance);
    }
}
