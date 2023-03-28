package com.hope.igb.aic_student.screens.mainscreen.favorite;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Favorite;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

 class FavoriteItemViewMvc extends BaseObservableMvc<FavoriteItemViewMvc.FavoriteItemListener> {




     protected interface FavoriteItemListener {
        void onFavoriteCourseClicked(String course_id);
        void onNextLectureClicked();
        void onUnsubscribeFavoriteCourseClicked(String course_id);
    }


    private  TextView title, tutor_name, progress_text, next_video, unsubscribe;
    private  ImageView tutor_image;
    private  SeekBar progress_bar;


    protected FavoriteItemViewMvc(LayoutInflater inflater, ViewGroup parent){
        setRootView(inflater.inflate(R.layout.main_favorite_holder, parent, false));
        initView();
    }

    private void initView(){
        title = findViewById(R.id.favorite_holder_title);
        tutor_name = findViewById(R.id.favorite_holder_teacher_name);
        tutor_image = findViewById(R.id.favorite_holder_teacher_image);
        progress_bar = findViewById(R.id.favorite_holder_course_progress);
        progress_text = findViewById(R.id.favorite_holder_progress_text);


        next_video = findViewById(R.id.favorite_holder_next_video);
        unsubscribe = findViewById(R.id.favorite_holder_unsubscribe_text_btn);
    }



    private void setClickableListeners(String favorite_course_id){

        for (FavoriteItemListener listener : getListeners()){

            getRootView().setOnClickListener(v -> listener.onFavoriteCourseClicked(favorite_course_id));

            next_video.setOnClickListener(v -> listener.onNextLectureClicked());

            unsubscribe.setOnClickListener(v -> listener.onUnsubscribeFavoriteCourseClicked(favorite_course_id));
        }
    }



     protected void bindViewHolder(Favorite favorite) {
        tutor_image.setImageResource(Integer.parseInt(favorite.getTutor_image_url()));

        title.setText(favorite.getCourse_title());

        tutor_name.setText(favorite.getTutor_name());

        int progress = estimateCourseCompletingProgress(favorite.getCourse_hours(), favorite.getCompleted_hours());

        progress_text.setText(progress+"%");

        progress_bar.setProgress(progress);



        setClickableListeners(favorite.getCourse_id());

        //TODO: add next video view and logic

     }

     private int estimateCourseCompletingProgress(int total_hours, int completed_hours) {
         return (int) (((1.0 * completed_hours)/total_hours) * 100.0);
     }


 }
