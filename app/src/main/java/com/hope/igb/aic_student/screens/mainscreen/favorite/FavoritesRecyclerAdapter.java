package com.hope.igb.aic_student.screens.mainscreen.favorite;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.networking.models.Favorite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class FavoritesRecyclerAdapter extends RecyclerView.Adapter<FavoritesRecyclerAdapter.NotificationViewHolder>
         implements FavoriteItemViewMvc.FavoriteItemListener {


     protected interface FavoriteAdapterListener{
         void onFavoriteCourseClicked(String course_id);
         void onNextLectureClicked();
         void onUnsubscribeFavoriteCourseClicked(String course_id);
     }



     static class NotificationViewHolder extends RecyclerView.ViewHolder {

        private final FavoriteItemViewMvc viewMvc;
        public NotificationViewHolder(@NonNull FavoriteItemViewMvc viewMvc) {
            super(viewMvc.getRootView());

            this.viewMvc= viewMvc;
        }
    }


    private final Set<FavoriteAdapterListener> listeners = Collections.newSetFromMap(
            new ConcurrentHashMap<>(1));
    private final ArrayList<Favorite> favorites;




    protected FavoritesRecyclerAdapter(ArrayList<Favorite> favorites) {

        this.favorites = favorites;
    }


    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoriteItemViewMvc viewMvc = new FavoriteItemViewMvc(LayoutInflater.from(parent.getContext()), parent);
        viewMvc.registerListener(this);
        return new NotificationViewHolder(viewMvc);
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull NotificationViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.viewMvc.unregisterListener(this);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull NotificationViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.viewMvc.registerListener(this);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        holder.viewMvc.bindViewHolder(favorites.get(position));

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }



    protected void registerAdapterListener(FavoriteAdapterListener listener){
        listeners.add(listener);
    }

    protected void unregisterAdapterListener(FavoriteAdapterListener listener){
        listeners.remove(listener);
    }



     @Override
     public void onFavoriteCourseClicked(String course_id) {
        for (FavoriteAdapterListener listener : listeners)
            listener.onFavoriteCourseClicked(course_id);
     }

     @Override
     public void onNextLectureClicked() {
         for (FavoriteAdapterListener listener : listeners)
             listener.onNextLectureClicked();
     }

     @Override
     public void onUnsubscribeFavoriteCourseClicked(String course_id) {
         for (FavoriteAdapterListener listener : listeners)
             listener.onUnsubscribeFavoriteCourseClicked(course_id);
     }
}
