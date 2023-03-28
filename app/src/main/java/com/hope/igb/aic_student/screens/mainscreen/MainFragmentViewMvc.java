package com.hope.igb.aic_student.screens.mainscreen;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.common.mytablayout.MyTabLayout;
import com.hope.igb.aic_student.common.navdrawer.NavDrawerViewMvc;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

 class MainFragmentViewMvc extends BaseObservableMvc<MainFragmentViewMvc.MainFragmentListener> implements
        NavDrawerViewMvc.NavDrawerListener {


    private NavDrawerViewMvc navDrawerViewMvc;
    private final DrawerLayout main_layout;
    private  ImageView main_menu, search;

    protected interface MainFragmentListener extends MyTabLayout.MyTabSelectedListener{



        @Override
        void onTabSelected(int tab_id);

        void onNavItemSelected(int itemId);

        void onSearchClicked();

        void onMainMenuClicked();
    }



    private final MyTabLayout myTabLayout;

     MainFragmentViewMvc(LayoutInflater inflater, ViewGroup parent) {

        setRootView(inflater.inflate(R.layout.main_fragment, parent, false));

        this.myTabLayout = new MyTabLayout(getRootView());


        main_layout = findViewById(R.id.main_drawer_layout);

        initClickableViews();

    }


    private void initClickableViews(){
        main_menu = findViewById(R.id.main_menu);
        main_menu.setOnClickListener(v -> onMainMenuClicked());
        search = findViewById(R.id.main_search);
        search.setOnClickListener(v -> onSearchClicked());


    }





    private void onMainMenuClicked(){
        for (MainFragmentListener listener : getListeners())
            listener.onMainMenuClicked();
    }

    private void onSearchClicked(){
        for (MainFragmentListener listener : getListeners())
            listener.onSearchClicked();
    }



    @Override
    public void registerListener(MainFragmentListener listener) {
        super.registerListener(listener);
        myTabLayout.registerListener(listener);
    }



    @Override
    public void unregisterListener(MainFragmentListener listener) {
        super.unregisterListener(listener);
        myTabLayout.unregisterListener(listener);
    }







    protected void openDrawer(){
        navDrawerViewMvc = new NavDrawerViewMvc(main_layout);
        navDrawerViewMvc.openDrawer();
        navDrawerViewMvc.registerListener(this);
    }

    protected void closeDrawer(){

        if (isDrawerOpen()){
            navDrawerViewMvc.unregisterListener(this);
            navDrawerViewMvc.closeDrawer();
            navDrawerViewMvc = null;
        }

    }


    private boolean isDrawerOpen(){
        return navDrawerViewMvc != null && navDrawerViewMvc.isDrawerOpen();
    }


    @Override
    public void onNavItemSelected(int itemId) {

        for (MainFragmentListener listener: getListeners())
            listener.onNavItemSelected(itemId);
    }



}
