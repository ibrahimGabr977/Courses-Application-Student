package com.hope.igb.aic_student.screens.mainscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.common.base.BaseFragment;
import com.hope.igb.aic_student.screens.mainscreen.categorizedcourses.CoursesFragment;
import com.hope.igb.aic_student.screens.mainscreen.favorite.FavoriteFragment;
import com.hope.igb.aic_student.screens.mainscreen.rank.RankFragment;
import com.hope.igb.aic_student.screens.mainscreen.statistics.StatisticsFragment;


public class MainFragment extends BaseFragment implements
        MainFragmentViewMvc.MainFragmentListener,
        CoursesFragment.CoursesFragmentListener {

    private MainFragmentViewMvc viewMvc;
    private CoursesFragment coursesFragment;









    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewMvc = new MainFragmentViewMvc(inflater, container);


        if (savedInstanceState == null) {
            coursesFragment =  new CoursesFragment();
            switchToFragment(coursesFragment);
            coursesFragment.registerFragmentListener(this);
        }



        return viewMvc.getRootView();
    }


    @Override
    public void onSeeAllCoursesOfCategoryClicked(String category) {
        getScreenNavigator().toDisplayAllCertainCategoryCourses(category);
    }

    @Override
    public void onCourseItemClicked(String course_id) {
        getScreenNavigator().toDisplayCourse("from main");
    }



    private  void switchToFragment(Fragment fragment){

        FragmentManager manager = requireActivity().getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        manager.beginTransaction().replace(R.id.mainFrameLayout, fragment).commit();

    }

    @Override
    public void onStart() {
        super.onStart();
        viewMvc.registerListener(this);

    }


    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
        viewMvc.closeDrawer();
    }





    @Override
    public void onTabSelected(int tab_id) {
        if (tab_id == R.id.tab_courses) {

            coursesFragment = new CoursesFragment();
            switchToFragment(coursesFragment);
            coursesFragment.registerFragmentListener(this);

        } else if (tab_id == R.id.tab_subscriptions) {
            switchToFragment(new FavoriteFragment());
        }else if (tab_id == R.id.tab_favorite)
            switchToFragment(new StatisticsFragment());
        else
            switchToFragment(new RankFragment());
    }


    @Override
    public void onNavItemSelected(int itemId) {


    }


    @Override
    public void onSearchClicked() {

    }

    @Override
    public void onMainMenuClicked() {
        viewMvc.openDrawer();
    }




}
