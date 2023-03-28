package com.hope.igb.aic_student.screens.displaycourse.courselessons;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hope.igb.aic_student.networking.models.Lesson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DisplayLessonsRecyclerAdapter extends RecyclerView.Adapter<DisplayLessonsRecyclerAdapter.LessonsViewHolder>
        implements
        DisplayLessonItemViewMvc.DisplayLessonItemListener {




    public interface DisplayLessonsAdapterListener{
        void onLessonClicked(int lesson_index);
    }



    static class LessonsViewHolder extends RecyclerView.ViewHolder {
        private final DisplayLessonItemViewMvc viewMvc;

        public LessonsViewHolder(DisplayLessonItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            this.viewMvc = viewMvc;

        }
    }


    private final boolean isBought;
    private final ArrayList<Lesson> lessons;
    private final LayoutInflater inflater;
    private final Set<DisplayLessonsAdapterListener> listeners =
            Collections.newSetFromMap(new ConcurrentHashMap<>(1));


    public DisplayLessonsRecyclerAdapter(LayoutInflater inflater, ArrayList<Lesson> lessons, boolean isBought) {
        this.inflater = inflater;
        this.lessons = lessons;
        this.isBought = isBought;

    }


    @Override
    public void onViewAttachedToWindow(@NonNull LessonsViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.viewMvc.registerListener(this);
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull LessonsViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.viewMvc.unregisterListener(this);
    }

    @NonNull
    @Override
    public LessonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DisplayLessonItemViewMvc viewMvc = new DisplayLessonItemViewMvc(inflater, parent);
        return new LessonsViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonsViewHolder holder, int position) {
        holder.viewMvc.bindLessons(lessons.get(position), position, isBought);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }


    public void registerAdapterListener(DisplayLessonsAdapterListener listener){
        listeners.add(listener);
    }


    public void unregisterAdapterListener(DisplayLessonsAdapterListener listener){
        listeners.remove(listener);
    }



    @Override
    public void onLessonClicked(int lesson_index) {
        for (DisplayLessonsAdapterListener listener: listeners)
            listener.onLessonClicked(lesson_index);
    }
}
