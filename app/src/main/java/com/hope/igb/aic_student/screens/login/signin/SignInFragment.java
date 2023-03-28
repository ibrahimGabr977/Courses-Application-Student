package com.hope.igb.aic_student.screens.login.signin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.hope.igb.aic_student.common.base.BaseObservableFragment;
import com.hope.igb.aic_student.screens.login.LoginListener;

public class SignInFragment extends BaseObservableFragment<SignInFragment.SignInListener> implements LoginListener {


    public interface SignInListener {
        void onRegisterClicked();
        void onSuccessfullyLoggedIn();
    }


    private SignInViewMvc viewMvc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewMvc = new SignInViewMvc(inflater);

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
        for (SignInListener listener: getListeners())
            listener.onRegisterClicked();
    }

    @Override
    public void onLoginClicked() {
        for (SignInListener listener: getListeners())
            listener.onSuccessfullyLoggedIn();
    }

    @Override
    public void onLoginFacebookClicked() {

    }

    @Override
    public void onLoginGoogleClicked() {

    }


}
