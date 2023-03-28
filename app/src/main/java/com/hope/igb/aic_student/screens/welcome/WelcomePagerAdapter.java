package com.hope.igb.aic_student.screens.welcome;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

class WelcomePagerAdapter extends PagerAdapter {


     private final ArrayList<String> titles;
     private final ArrayList<String> contents;
     private final ArrayList<Integer> imagesRes;

    WelcomePagerAdapter(ArrayList<Integer> imagesRes, ArrayList<String> titles, ArrayList<String> contents) {
        this.imagesRes = imagesRes;
        this.titles = titles;
        this.contents = contents;

    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        WelcomeAdapterItemViewMvc viewMvc = new WelcomeAdapterItemViewMvc(LayoutInflater.from(container.getContext()), container);
        container.addView(viewMvc.getRootView());

        viewMvc.bindView(imagesRes.get(position), titles.get(position), contents.get(position));

        return viewMvc.getRootView();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }



}
