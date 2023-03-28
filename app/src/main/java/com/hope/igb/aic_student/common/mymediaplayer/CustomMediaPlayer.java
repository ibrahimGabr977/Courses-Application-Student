package com.hope.igb.aic_student.common.mymediaplayer;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlayer;

import com.hope.igb.aic_student.common.base.BaseObservable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

@SuppressLint("UnsafeOptInUsageError")
public class CustomMediaPlayer extends BaseObservable<CustomMediaPlayer.Listener> implements Serializable {

    public interface Listener {
        void onMediaPlayed();

        void onMediaPrepared();

        void onPlayerInitialized(ExoPlayer exoPlayer);

        void onActivityResume();
    }


    private ExoPlayer exoPlayer;

    private final Context context;
    private final ArrayList<String> medias_items;


    private boolean playWhenReady = true;
    private final ArrayList<Long> mediaItemsPlaybackPositions;
    private int currentItem = 0;
    private Player.Listener playBackStateProgressBarListener = null;


    public CustomMediaPlayer(Context context, ArrayList<String> medias_items) {

        this.context = context;
        this.medias_items = medias_items;


        mediaItemsPlaybackPositions = new ArrayList<>(Collections.nCopies(medias_items.size(), 0L));

    }


    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }


    private void initPlayer() {

        if (exoPlayer == null) {
            exoPlayer = new ExoPlayer.Builder(context).build();

            for (Listener listener : getListeners())
                listener.onPlayerInitialized(exoPlayer);

            createMediasPlayList();
            prepareAndStart();
        }else

            exoPlayer.play();

    }


    private void createMediasPlayList() {

        MediaItem firstItem = MediaItem.fromUri(medias_items.get(0));
        exoPlayer.setMediaItem(firstItem);

        MediaItem mediaItem;

        for (int i = 1; i < medias_items.size(); i++) {
            mediaItem = MediaItem.fromUri(medias_items.get(i));
            exoPlayer.addMediaItem(mediaItem);
        }

    }

    private void prepareAndStart() {
        exoPlayer.setPlayWhenReady(playWhenReady);
        exoPlayer.seekTo(currentItem, mediaItemsPlaybackPositions.get(currentItem));

        if (playBackStateProgressBarListener == null)
            playBackStateProgressBarListener = playbackStateListener();


        exoPlayer.addListener(playBackStateProgressBarListener);


        exoPlayer.prepare();

    }


    public void playMediaInSpecifiedIndex(int playing_index) {
        if (exoPlayer.isPlaying())
            exoPlayer.stop();

        exoPlayer.seekTo(playing_index, mediaItemsPlaybackPositions.get(playing_index));
        exoPlayer.play();
        currentItem = playing_index;

    }


    public void onActivityStart() {
        if (Util.SDK_INT > 23) {
            initPlayer();

        }
    }

    public void onActivityResume() {
        for (Listener listener : getListeners())
            listener.onActivityResume();

        if ((Util.SDK_INT <= 23 || exoPlayer == null)) {
            initPlayer();
        }
    }


    public void onActivityPause() {

        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }


    public void onActivityStop() {
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }


    public void releasePlayer() {
        if (exoPlayer != null) {
            currentItem = exoPlayer.getCurrentMediaItemIndex();
            mediaItemsPlaybackPositions.set(currentItem, exoPlayer.getContentPosition());
            playWhenReady = exoPlayer.getPlayWhenReady();

            if (playBackStateProgressBarListener != null)
                exoPlayer.removeListener(playBackStateProgressBarListener);


            exoPlayer.release();
        }

        exoPlayer = null;
    }


    private Player.Listener playbackStateListener() {

        return new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                Player.Listener.super.onPlaybackStateChanged(playbackState);

                for (Listener listener : getListeners())
                    if (playbackState == Player.STATE_READY && playWhenReady)
                        listener.onMediaPlayed();
                    else

                        listener.onMediaPrepared();


            }
        };

    }


}
