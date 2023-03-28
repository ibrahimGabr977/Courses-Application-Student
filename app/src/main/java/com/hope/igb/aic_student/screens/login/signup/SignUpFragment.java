package com.hope.igb.aic_student.screens.login.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.hope.igb.aic_student.common.base.BaseObservableFragment;
import com.hope.igb.aic_student.screens.login.LoginListener;

public class SignUpFragment extends BaseObservableFragment<SignUpFragment.SignUpListener> implements LoginListener {

    public interface SignUpListener {
        void onSignInClicked();
        void onSuccessfullyLoggedIn();
    }


    private SignUpViewMvc viewMvc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewMvc = new SignUpViewMvc(inflater);


        return viewMvc.getRootView();
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
    }



    @Override
    public void onCreateAccountClicked() {
        for (SignUpListener listener: getListeners())
            listener.onSuccessfullyLoggedIn();
    }

    @Override
    public void onLoginClicked() {
        for (SignUpListener listener: getListeners())
            listener.onSignInClicked();
    }

    @Override
    public void onLoginFacebookClicked() {

    }

    @Override
    public void onLoginGoogleClicked() {

    }


}
