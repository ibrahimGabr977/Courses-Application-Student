package com.hope.igb.aic_student.screens.login.signup;

import android.view.LayoutInflater;
import com.google.android.material.snackbar.Snackbar;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.databinding.SignupFragmentBinding;
import com.hope.igb.aic_student.common.Constants;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;
import com.hope.igb.aic_student.screens.login.LoginListener;

class SignUpViewMvc extends BaseObservableMvc<LoginListener> {


    private final SignupFragmentBinding binding;

    protected SignUpViewMvc(LayoutInflater inflater) {
        binding = SignupFragmentBinding.inflate(inflater);
        setRootView(binding.getRoot());

        init();
    }



    private void init() {
        binding.signIn.setOnClickListener(v -> onSignInClicked());
        binding.register.setOnClickListener(v -> onRegisterClicked());
        binding.loginGoogle.setOnClickListener(v -> onLoginGoogleClicked());
        binding.loginFacebook.setOnClickListener(v -> onLoginFacebookClicked());
    }



    private void onSignInClicked() {
     for (LoginListener listener: getListeners())
         listener.onLoginClicked();

    }


    private void onRegisterClicked() {
        if (isEmailValid() && isNameValid()) {
            for (LoginListener listener : getListeners())
                listener.onCreateAccountClicked();

        }else {
            Snackbar.make(getRootView(), R.string.invalid_login, Snackbar.LENGTH_LONG).show();
        }
    }

    private void onLoginGoogleClicked() {
        for (LoginListener listener: getListeners())
            listener.onLoginGoogleClicked();

    }

    private void onLoginFacebookClicked() {
        for (LoginListener listener: getListeners())
            listener.onLoginFacebookClicked();
    }


    private boolean isEmailValid() {
        return android.util.Patterns.EMAIL_ADDRESS
                .matcher(binding.editEmail.getText().toString()).matches();
    }

    private boolean isNameValid() {
        return binding.editName.getText().toString()
                .trim().length() <= Constants.MIN_NAME_LENGTH;
    }
}
