package com.hope.igb.aic_student.common.base;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.hope.igb.aic_student.common.screennavigator.ScreenNavigator;
import com.hope.igb.aic_student.screens.BaseActivity;

import java.util.Objects;

public class BaseFragment extends Fragment{


    private static ScreenNavigator screenNavigator;

    private NavHostFragment getNavHostFragment(){
        return ((BaseActivity) Objects.requireNonNull(requireActivity())).getNavHostFragment();
    }


    protected ScreenNavigator getScreenNavigator(){
        if (screenNavigator == null)
            screenNavigator =  new ScreenNavigator(getNavHostFragment());

        return screenNavigator;

    }
}
