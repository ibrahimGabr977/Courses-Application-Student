package com.hope.igb.aic_student.screens.coursestudying;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Lesson;
import com.hope.igb.aic_student.common.mymediaplayer.CustomPlayerView;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;
import com.hope.igb.aic_student.screens.coursestudying.courselessons.LessonsRecyclerAdapter;
import java.util.ArrayList;
import java.util.Objects;

@SuppressLint({"ClickableViewAccessibility",
        "NotifyDataSetChanged"})
public class StudyViewMvc extends BaseObservableMvc<StudyViewMvc.ViewMvcListener> implements
        LessonsRecyclerAdapter.LessonsAdapterListener {


    private final LessonsRecyclerAdapter recyclerAdapter;



    public interface ViewMvcListener {
        void onLessonClicked(String lesson_id, int position);
    }


    private final CustomPlayerView playerView;
    private RecyclerView bottomRecyclerView;
    private RecyclerView sideRecyclerView;
    private final float recyclerInitialTranslation;
    private final CustomPlayerView.PlayerViewListener listener;


    protected StudyViewMvc(LayoutInflater inflater, ViewGroup parent,
                           ArrayList<Lesson> lessons, boolean isBought,
                           CustomPlayerView.PlayerViewListener listener) {


        this.listener = listener;

        setRootView(inflater.inflate(R.layout.study_course_fragment, parent, false));


        playerView = findViewById(R.id.studying_player_view);


        recyclerInitialTranslation = (float) (getScreenHeight() * 0.25);
        recyclerAdapter = new LessonsRecyclerAdapter(inflater, lessons, isBought);
    }


    private int getScreenHeight() {
        Point size = new Point();
        WindowManager w = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        w.getDefaultDisplay().getSize(size);
        return size.y;
    }

    protected void onFragmentStart(int orientation) {

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            releaseSideRecyclerView();
            bindBottomRecyclerView();

        } else {
            releaseBottomRecyclerView();

            playerView.addPlayerControllerVisibilityListener(listener);

            bindSideRecyclerView();
            showSideRecyclerView();
        }

    }


    private void bindBottomRecyclerView() {
        bottomRecyclerView = findViewById(R.id.studyingRecyclerViewBottom);
        bottomRecyclerView.setVisibility(View.VISIBLE);

        bottomRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerAdapter.setViewOrientation(false);
        bottomRecyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.registerAdapterListener(this);
        recyclerAdapter.notifyDataSetChanged();

    }


    private void releaseBottomRecyclerView() {
        if (bottomRecyclerView != null) {
            bottomRecyclerView.removeAllViews();
            bottomRecyclerView.setVisibility(View.GONE);
            recyclerAdapter.unregisterAdapterListener(this);
            bottomRecyclerView = null;

        }
    }


    private void bindSideRecyclerView() {
        sideRecyclerView = findViewById(R.id.studyingRecyclerViewSide);
        sideRecyclerView.setVisibility(View.VISIBLE);

        sideRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sideRecyclerView.setTranslationX(recyclerInitialTranslation);


        recyclerAdapter.setViewOrientation(true);
        sideRecyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.registerAdapterListener(this);
        recyclerAdapter.notifyDataSetChanged();



    }


    private void releaseSideRecyclerView() {
        if (sideRecyclerView != null) {
            sideRecyclerView.removeAllViews();
            sideRecyclerView.setVisibility(View.GONE);
            recyclerAdapter.unregisterAdapterListener(this);
            sideRecyclerView = null;


        }
    }


    protected void showSideRecyclerView() {
        if (sideRecyclerView == null)
            bindSideRecyclerView();

        sideRecyclerView.setVisibility(View.VISIBLE);


    }

    protected void hideSideRecyclerView() {

        sideRecyclerView.setVisibility(View.GONE);

    }


    @Override
    public void onDragViewItemLeft(int movement_distance) {
        sideRecyclerView.setTranslationX(Math.max(sideRecyclerView.getTranslationX() + movement_distance, 0));
    }

    @Override
    public void onDragViewItemRight(int movement_distance) {
        sideRecyclerView.setTranslationX(Math.min(sideRecyclerView.getTranslationX() + movement_distance,
                recyclerInitialTranslation));
    }


    @Override
    public void onLessonClicked(String lesson_id, int last_clicked_position, int position) {

        if (last_clicked_position != -1)
            ((LessonsRecyclerAdapter.LessonsViewHolder) Objects
                    .requireNonNull(getCurrentRecyclerView().findViewHolderForAdapterPosition(last_clicked_position)))
                    .getViewMvc().resetItemToNonPlayedStatus();


        for (StudyViewMvc.ViewMvcListener listener : getListeners())
            listener.onLessonClicked(lesson_id, position);
    }


    private RecyclerView getCurrentRecyclerView() {
        if (sideRecyclerView == null)
            return bottomRecyclerView;

        return sideRecyclerView;
    }


    public void onMediaPlayed() {
        playerView.hideLoadingMediaProgressBar();
    }


    public void onMediaPrepared() {
        playerView.showLoadingMediaProgressBar();
    }


    public void onPlayerInitialized(ExoPlayer exoPlayer) {
        playerView.setPlayer(exoPlayer);
        playerView.addCustomPlayerProperties(listener);

        playerView.setVisibility(View.VISIBLE);
    }


    protected void hidePlayerView() {
        playerView.setVisibility(View.GONE);
    }


    @SuppressLint("InlinedApi")
    protected void hideSystemUi(Activity activity) {
        WindowCompat.setDecorFitsSystemWindows(activity.getWindow(), false);
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(activity.getWindow(),
                getRootView());
        controller.hide(WindowInsetsCompat.Type.systemBars());
        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

    }


    protected void setPlayerViewTitle(String title){
        playerView.setTitleText(title);
    }
}
