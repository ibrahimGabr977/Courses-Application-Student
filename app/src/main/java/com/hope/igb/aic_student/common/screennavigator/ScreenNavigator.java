package com.hope.igb.aic_student.common.screennavigator;


import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.hope.igb.aic_student.screens.displayallcourses.DisplayAllCoursesFragmentDirections;
import com.hope.igb.aic_student.screens.displaycourse.DisplayCourseFragmentDirections;
import com.hope.igb.aic_student.screens.mainscreen.MainFragmentDirections;

public class ScreenNavigator  {




    private final NavController navController;



    public ScreenNavigator(NavHostFragment navHostFragment) {
        navController = navHostFragment.getNavController();

    }


    public void toDisplayAllCertainCategoryCourses(String category) {
        NavDirections action = MainFragmentDirections.actionDisplayCertainCategoryCourses();

        navController.navigate(action);
    }


    public void toDisplayCourse(String from_where) {
        NavDirections action;
        if (from_where.equals("from main"))
            action = MainFragmentDirections.actionDisplayClickedCourse();
        else
            action = DisplayAllCoursesFragmentDirections.actionDisplayClickedCourse();


        navController.navigate(action);
    }


    public void toStudyCourse(int lesson_index) {
        NavDirections action = DisplayCourseFragmentDirections.actionStartStudyingCourse();

        navController.navigate(action);
    }

}
