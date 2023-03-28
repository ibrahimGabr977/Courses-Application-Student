package com.hope.igb.aic_student.screens.coursestudying;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.exoplayer.ExoPlayer;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Lesson;
import com.hope.igb.aic_student.common.base.BaseFragment;
import com.hope.igb.aic_student.common.mymediaplayer.CustomMediaPlayer;
import com.hope.igb.aic_student.common.mymediaplayer.CustomPlayerView;

import java.util.ArrayList;


@SuppressLint("SourceLockedOrientationActivity")
public class StudyCourseFragment extends BaseFragment implements
        CustomPlayerView.PlayerViewListener,
        StudyViewMvc.ViewMvcListener,
        CustomMediaPlayer.Listener {


    private CustomMediaPlayer customMediaPlayer;
    private ArrayList<String> lessons_urls;
    private ArrayList<Lesson> lessons;
    private StudyViewMvc viewMvc;

    private final OnBackPressedCallback backPressedCallback =
            new OnBackPressedCallback(false /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            onBackPressed();
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        init(inflater, container);

        if (savedInstanceState == null)
            initLessonPlayer();
        else
            customMediaPlayer = (CustomMediaPlayer) savedInstanceState.getSerializable("customMediaPlayer");


        return viewMvc.getRootView();
    }


    private void init(LayoutInflater inflater, ViewGroup parent) {
        boolean isBought = StudyCourseFragmentArgs.fromBundle(getArguments()).getIsBought();
        initTestLessons();
        initTestLessonsUrls();

        viewMvc = new StudyViewMvc(inflater, parent, lessons, isBought, this);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), backPressedCallback);


    }

    private void initLessonPlayer() {

        customMediaPlayer = new CustomMediaPlayer(requireActivity(),
                lessons_urls);

        customMediaPlayer
                .setCurrentItem(StudyCourseFragmentArgs
                        .fromBundle(getArguments())
                        .getStartLessonPosition());
    }

    @Override
    public void onControllerVisibilityChanged(boolean isControllerVisible) {
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            if (isControllerVisible) {
                viewMvc.showSideRecyclerView();
            } else
                viewMvc.hideSideRecyclerView();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        viewMvc.onFragmentStart(getResources().getConfiguration().orientation);
        viewMvc.registerListener(this);
        customMediaPlayer.registerListener(this);
        customMediaPlayer.onActivityStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        customMediaPlayer.onActivityResume();

    }

    @Override
    public void onStop() {
        super.onStop();
        customMediaPlayer.onActivityStop();
        customMediaPlayer.unregisterListener(this);
        viewMvc.unregisterListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        customMediaPlayer.onActivityPause();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("customMediaPlayer", customMediaPlayer);
    }


    private void onBackPressed() {
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        viewMvc.onFragmentStart(Configuration.ORIENTATION_PORTRAIT);
        backPressedCallback.setEnabled(false);
    }


    @Override
    public void onClosePlayerClicked() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            customMediaPlayer.releasePlayer();
            viewMvc.hidePlayerView();
        } else {
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            viewMvc.onFragmentStart(Configuration.ORIENTATION_PORTRAIT);
            backPressedCallback.setEnabled(false);
        }
    }


    @Override
    public void onFullScreenClicked(boolean isFullScreen) {
        if (isFullScreen) {
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            viewMvc.onFragmentStart(Configuration.ORIENTATION_LANDSCAPE);
            backPressedCallback.setEnabled(true);
        } else {
            requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            viewMvc.onFragmentStart(Configuration.ORIENTATION_PORTRAIT);
            backPressedCallback.setEnabled(false);
        }


    }


    private void initTestLessonsUrls() {

        lessons_urls = new ArrayList<>();

        lessons_urls.add(getString(R.string.lesson_url_test1));
        lessons_urls.add(getString(R.string.lesson_url_test2));
        lessons_urls.add(getString(R.string.lesson_url_test3));
        lessons_urls.add(getString(R.string.lesson_url_test4));
        lessons_urls.add(getString(R.string.lesson_url_test5));

    }

    private void initTestLessons() {
        lessons = new ArrayList<>();

        lessons.add(new Lesson("", getString(R.string.lesson_title_test1),
                getString(R.string.lesson_duration_test1), "lesson_url"));

        lessons.add(new Lesson("", getString(R.string.lesson_title_test2),
                getString(R.string.lesson_duration_test2), "lesson_url"));

        lessons.add(new Lesson("", getString(R.string.lesson_title_test3),
                getString(R.string.lesson_duration_test3), "lesson_url"));

        lessons.add(new Lesson("", getString(R.string.lesson_title_test4),
                getString(R.string.lesson_duration_test4), "lesson_url"));

        lessons.add(new Lesson("", getString(R.string.lesson_title_test5),
                getString(R.string.lesson_duration_test5), "lesson_url"));


    }

    @Override
    public void onPlayerInitialized(ExoPlayer exoPlayer) {
        viewMvc.onPlayerInitialized(exoPlayer);
        viewMvc.setPlayerViewTitle(getLessonTitleAt(getStartLessonPosition()));

    }

    private String getLessonTitleAt(int position) {
        return lessons.get(position).getLesson_title();
    }

    private int getStartLessonPosition() {
        return StudyCourseFragmentArgs.fromBundle(getArguments()).getStartLessonPosition();
    }


    @Override
    public void onMediaPrepared() {
        viewMvc.onMediaPrepared();

    }

    @Override
    public void onMediaPlayed() {
        viewMvc.onMediaPlayed();

    }


    @Override
    public void onActivityResume() {
        viewMvc.hideSystemUi(requireActivity());
    }


    @Override
    public void onLessonClicked(String lesson_id, int position) {
        customMediaPlayer.playMediaInSpecifiedIndex(position);
        viewMvc.setPlayerViewTitle(getLessonTitleAt(position));
    }
}
