package com.hope.igb.aic_student.screens.welcome;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.common.customviewpager.NonSwipedViewPager;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

import java.util.ArrayList;


class WelcomeViewMvc extends BaseObservableMvc<WelcomeViewMvc.WelcomeListener> {


    private final TextView next;
    private NonSwipedViewPager viewPager;

    protected interface WelcomeListener {
        void onGetStartedClicked();

    }


    private ArrayList<ImageView> indicators;
    private static int current_page = 0;


    protected WelcomeViewMvc(LayoutInflater inflater, ViewGroup parent){
        setRootView(inflater.inflate(R.layout.activity_welcome, parent, false));



        TextView skip = findViewById(R.id.welcome_skip_text_btn);
        skip.setOnClickListener(v-> onNextClicked(indicators.size() - 1));
        next = findViewById(R.id.welcome_next_text_btn);

        initPagesIndicators();





    }


    @SuppressLint("ClickableViewAccessibility")
    protected void bindPagerAdapter(ArrayList<Integer> imagesRes, ArrayList<String> titles, ArrayList<String> contents){
        viewPager = findViewById(R.id.welcomeViewPager);
        WelcomePagerAdapter adapter = new WelcomePagerAdapter(imagesRes, titles, contents);
        viewPager.setAdapter(adapter);
        viewPager.setSwipedEnabled(false);


        onNormalPagesBind();
        onPageChanged();

    }






    private void initPagesIndicators(){
        LinearLayout indicators_container = findViewById(R.id.pages_indicators_container);
        indicators = new ArrayList<>();

        indicators.add(indicators_container.findViewById(R.id.welcome_indicator_page1));
        indicators.add(indicators_container.findViewById(R.id.welcome_indicator_page2));
        indicators.add(indicators_container.findViewById(R.id.welcome_indicator_page3));
        indicators.add(indicators_container.findViewById(R.id.welcome_indicator_page4));
        indicators.add(indicators_container.findViewById(R.id.welcome_indicator_page5));


    }


    private void onNextClicked(int position){
        if (position < indicators.size()){
            viewPager.setCurrentItem(position, true);
        }

        if (position == indicators.size() -1)
            onLastPageBind();
        else
            onNormalPagesBind();

    }



    private void onNormalPagesBind() {
        next.setText(getContext().getString(R.string.next));
        next.setOnClickListener(v -> onNextClicked(current_page+1));
    }





    private void onLastPageBind() {

        next.setText(getContext().getString(R.string.get_started));
        next.setOnClickListener(v -> {
            for (WelcomeListener listener : getListeners())
                listener.onGetStartedClicked();
        });
    }


    private void onPageChanged(){

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageScrollStateChanged(int state) {}



            @Override
            public void onPageSelected(int position) {
                indicators.get(0).setImageResource(R.drawable.a_welcome_unselected_page_circled);
                indicators.get(position - 1).setImageResource(R.drawable.a_welcome_unselected_page_circled);
                indicators.get(position).setImageResource(R.drawable.a_welcome_selected_page_circled);
                current_page = position ;
            }

        });
    }


}
