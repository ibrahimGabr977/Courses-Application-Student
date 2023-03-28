package com.hope.igb.aic_student.common.navdrawer;


import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

public class NavDrawerViewMvc extends BaseObservableMvc<NavDrawerViewMvc.NavDrawerListener>
        implements
        NavigationView.OnNavigationItemSelectedListener{



    public interface NavDrawerListener {
        void onNavItemSelected(int itemId);
    }




    private final DrawerLayout drawerLayout;


    public NavDrawerViewMvc(DrawerLayout base_layout) {
        this.drawerLayout = base_layout;

        setRootView(base_layout);


        NavigationView mNavigationView = findViewById(R.id.nav_view);


        mNavigationView.setNavigationItemSelectedListener(this);


    }














    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }



    public boolean isDrawerOpen() {
        return drawerLayout.isDrawerOpen(GravityCompat.START);
    }


    public void closeDrawer() {
        drawerLayout.closeDrawers();

    }







    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();



        for (NavDrawerListener listener: getListeners())
            listener.onNavItemSelected(item.getItemId());

        return false;
    }

}
