package com.hope.igb.aic_student.common.mytablayout;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

import java.util.HashMap;
import java.util.Objects;

public class MyTabLayout extends BaseObservableMvc<MyTabLayout.MyTabSelectedListener> implements View.OnClickListener {

    private final LinearLayout tabLayout;

    private final HashMap<Integer, TextView> tabs;
    private static int last_selected_tab = R.id.tab_courses;



    public interface MyTabSelectedListener {
        void onTabSelected(int tab_id);
    }


    public MyTabLayout(View rootView) {

        setRootView(rootView);


        tabLayout = findViewById(R.id.customTabLayout);
        tabs = new HashMap<>();



        initTabs();

    }



    private void initTabs(){
        tabs.put(R.id.tab_courses,  tabLayout.findViewById(R.id.tab_courses));
        tabs.put(R.id.tab_subscriptions,  tabLayout.findViewById(R.id.tab_subscriptions));
        tabs.put(R.id.tab_favorite,  tabLayout.findViewById(R.id.tab_favorite));
        tabs.put(R.id.tab_rank,  tabLayout.findViewById(R.id.tab_rank));



        for (Integer tab_id: tabs.keySet())
            Objects.requireNonNull(tabs.get(tab_id)).setOnClickListener(this);


    }



    private void tabSelectedUiChanges(int selected_tab){
        if (selected_tab != last_selected_tab){

            TextView tab = tabs.get(selected_tab);

            if (tab != null){
                tab.setBackgroundResource(R.drawable.a_welcome_nextbg);
                tab.setTextColor(getContext().getResources().getColor(R.color.white_color));
            }


            lastSelectedTabUiChanges();

            last_selected_tab = selected_tab;

        }
    }


    private void lastSelectedTabUiChanges(){
        TextView tab = tabs.get(last_selected_tab);

        if (tab != null){
            tab.setBackgroundResource(R.drawable.c_main_unselected_tabbg);
            tab.setTextColor(getContext().getResources().getColor(R.color.black_color));
        }
    }


    @Override
    public void onClick(View v) {


        tabSelectedUiChanges(v.getId());



        for (MyTabSelectedListener listener : getListeners())
            listener.onTabSelected(v.getId());


    }


}
