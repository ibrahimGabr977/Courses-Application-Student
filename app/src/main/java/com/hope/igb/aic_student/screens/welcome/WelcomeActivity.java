package com.hope.igb.aic_student.screens.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.screens.BaseActivity;
import com.hope.igb.aic_student.util.FullScreenInflater;

import java.util.ArrayList;
import java.util.Arrays;

public class WelcomeActivity extends AppCompatActivity implements WelcomeViewMvc.WelcomeListener {

    private WelcomeViewMvc viewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        viewMvc = new WelcomeViewMvc(LayoutInflater.from(this), null);

        setContentView(viewMvc.getRootView());
        FullScreenInflater.setFullScreen(this);
    }



    private ArrayList<Integer> imagesRes(){

        return new ArrayList<>( Arrays.asList(R.drawable.a_welcome_page1_image, R.drawable.a_welcome_page2_image,
                R.drawable.a_welcome_page3_image, R.drawable.a_welcome_page4_image, R.drawable.a_welcome_page5_image));
    }


    private ArrayList<String> titles(){
        return new ArrayList<>(Arrays.asList(getString(R.string.welcome_page1_title), getString(R.string.welcome_page2_title),
                getString(R.string.welcome_page3_title), getString(R.string.welcome_page4_title),
                getString(R.string.welcome_page5_title)));
    }


    private ArrayList<String> contents(){
        return new ArrayList<>(Arrays.asList(getString(R.string.welcome_page1_content), getString(R.string.welcome_page2_content),
                getString(R.string.welcome_page3_content), getString(R.string.welcome_page4_content),
                getString(R.string.welcome_page5_content)));
    }


    @Override
    protected void onStart() {
        super.onStart();
        viewMvc.registerListener(this);
        viewMvc.bindPagerAdapter(imagesRes(), titles(), contents());
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
    }

    @Override
    public void onGetStartedClicked() {
        startActivity(new Intent(WelcomeActivity.this, BaseActivity.class));
    }
}