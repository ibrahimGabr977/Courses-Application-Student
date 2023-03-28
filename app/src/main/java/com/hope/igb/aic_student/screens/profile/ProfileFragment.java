package com.hope.igb.aic_student.screens.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.hope.igb.aic_student.R;
import java.util.HashMap;

public class ProfileFragment extends Fragment implements ProfileViewMvc.ProfileListener {

    private ProfileViewMvc viewMvc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        viewMvc = new ProfileViewMvc(inflater, new Teacher("", "", "", "", "", "", "", 0, 0, ""));

        return inflater.inflate(R.layout.nav_drawer_fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
//        viewMvc.registerListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
//        viewMvc.unregisterListener(this);
    }

    @Override
    public void onBackClicked() {

    }

    @Override
    public void onSaveChangedClicked(HashMap<String, String> changedDataList) {
        Toast.makeText(getContext(), "changes saved successfully", Toast.LENGTH_SHORT).show();

    }
}
