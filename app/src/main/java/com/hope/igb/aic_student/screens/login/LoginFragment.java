package com.hope.igb.aic_student.screens.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.screens.login.signin.SignInFragment;
import com.hope.igb.aic_student.screens.login.signup.SignUpFragment;

public class LoginFragment extends Fragment implements
        SignInFragment.SignInListener,
        SignUpFragment.SignUpListener {


    private SignInFragment signInFragment;
    private SignUpFragment signUpFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.login_fragment, container, false);

        if (savedInstanceState == null) {
            signInFragment = new SignInFragment();
            switchToFragment(signInFragment);
        }


        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        registerListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterListener();
    }


    private void registerListener() {
        if (signInFragment != null)
            signInFragment.registerFragmentListener(this);
        else
            signUpFragment.registerFragmentListener(this);
    }


    private void unregisterListener() {
        if (signInFragment != null)
            signInFragment.unregisterFragmentListener(this);
        else
            signUpFragment.unregisterFragmentListener(this);
    }



    private void switchToFragment(Fragment fragment) {

        FragmentManager manager = requireActivity().getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        manager.beginTransaction().replace(R.id.loginFrameLayout, fragment).commit();

    }


    @Override
    public void onRegisterClicked() {
        unregisterListener();
        signInFragment = null;

        signUpFragment = new SignUpFragment();
        switchToFragment(signUpFragment);
        registerListener();

    }

    @Override
    public void onSignInClicked() {
        unregisterListener();
        signUpFragment = null;

        signInFragment = new SignInFragment();
        switchToFragment(signInFragment);
        registerListener();
    }

    @Override
    public void onSuccessfullyLoggedIn() {

    }
}
