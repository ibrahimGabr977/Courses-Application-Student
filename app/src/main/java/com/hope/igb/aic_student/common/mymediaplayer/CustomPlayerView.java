package com.hope.igb.aic_student.common.mymediaplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.media3.ui.PlayerView;
import com.hope.igb.aic_student.R;

@SuppressLint("UnsafeOptInUsageError")
public class CustomPlayerView extends PlayerView {


    private ProgressBar progressBar;
    private TextView title_text;


    public interface PlayerViewListener {
        void onClosePlayerClicked();
        void onFullScreenClicked(boolean isFullScreen);
        void onControllerVisibilityChanged(boolean isControllerVisible);
    }




    public CustomPlayerView(Context context) {
        super(context);

    }

    public CustomPlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public CustomPlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public void addCustomPlayerProperties(PlayerViewListener listener){
        setLoadingMediaProgressBar(androidx.appcompat.R.style.Widget_AppCompat_ProgressBar,
                R.color.blue_color);

        addClosePlayerButton(listener);
        showFullScreenButton(listener);
        addTitleTextToPlayer();



    }

    private void addTitleTextToPlayer(){
        title_text = new TextView(getContext());

        title_text.setTextColor(ContextCompat.getColor(getContext(), R.color.white_color));
        title_text.setTextSize(16);
        title_text.setTypeface(Typeface.DEFAULT_BOLD);

        addView(title_text, getChildCount());

        title_text.setLayoutParams(getViewDefaultParams(207));
    }


    public void setTitleText(String title){
        if (title_text != null)
        title_text.setText(title);
    }


    private void setLoadingMediaProgressBar(int progress_style, int progress_color) {
        progressBar = new ProgressBar(new ContextThemeWrapper(getContext(), progress_style),
                null, androidx.appcompat.R.attr.progressBarStyle);





        progressBar.setVisibility(GONE);

        addView(progressBar, getChildCount());

        progressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(getContext(), progress_color),
                PorterDuff.Mode.MULTIPLY);

        addProgressParams();
    }

    private void addProgressParams() {
        LayoutParams childParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        childParams.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(childParams);


    }


    public void showLoadingMediaProgressBar() {
        if (progressBar != null ) {
            progressBar.setVisibility(VISIBLE);
        }
    }

    public void hideLoadingMediaProgressBar() {
        if (progressBar != null ) {
            progressBar.setVisibility(GONE);
        }
    }


    private void addClosePlayerButton(PlayerViewListener listener) {
        ImageView close_player = new ImageView(getContext());
        close_player.setImageResource(R.drawable.s_studying_closed);

        setAsClickableView(close_player);

        close_player.setOnClickListener(v -> listener.onClosePlayerClicked());


        addView(close_player, getChildCount());

        close_player.setLayoutParams(getViewDefaultParams(0));
    }


    private void setAsClickableView(View button) {
        button.setClickable(true);
        button.setFocusable(true);
        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
    }


    private LayoutParams getViewDefaultParams(int initial_margin_left) {
        LayoutParams childParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        childParams.gravity = Gravity.TOP | Gravity.START;
        childParams.leftMargin = initial_margin_left + 37;
        childParams.topMargin = 37;

        return childParams;

    }


    private void showFullScreenButton(PlayerViewListener listener){
        setControllerOnFullScreenModeChangedListener(listener::onFullScreenClicked);

    }

    public void addPlayerControllerVisibilityListener(PlayerViewListener listener){

            this.setControllerVisibilityListener(visibility -> listener.onControllerVisibilityChanged(visibility == VISIBLE));

    }


}
