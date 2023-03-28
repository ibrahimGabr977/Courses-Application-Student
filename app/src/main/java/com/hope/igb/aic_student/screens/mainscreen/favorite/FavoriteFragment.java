package com.hope.igb.aic_student.screens.mainscreen.favorite;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Favorite;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment implements FavoritesRecyclerAdapter.FavoriteAdapterListener {



    private FavoritesRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Favorite> favorite_courses;
    private View root_view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root_view = inflater.inflate(R.layout.common_recycler_adapter, container, false);

        favorite_courses = new ArrayList<>(2);


        if (savedInstanceState == null)
            favorite_courses = favorites_test();

        return root_view;


    }




    @SuppressLint("NotifyDataSetChanged")
    private void bindRecyclerView() {
        recyclerView = root_view.findViewById(R.id.commonRecyclerView);
        adapter = new FavoritesRecyclerAdapter(favorite_courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onStop() {
        super.onStop();
        adapter.unregisterAdapterListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        bindRecyclerView();
        adapter.registerAdapterListener(this);

    }






    private ArrayList<Favorite> favorites_test(){

        ArrayList<Favorite> favoriteArrayList = new ArrayList<>(2);

        Favorite physics = new Favorite(null, "شرح مادة الفيزياء",R.drawable.z_test_physics_image,
                "أ/محمد عبدالمعبود", String.valueOf(R.drawable.z_test_teacher_image1),
                "عام", "3ثانوي", 35, 25,45);

        Favorite biology = new Favorite(null, "شرح مادة الأحياء",R.drawable.z_test_biology_image,
                "أ/عاطف الشرقاوي", String.valueOf(R.drawable.z_test_teacher_image2),
                "أزهر", "3ثانوي", 23, 7, 33);


        favoriteArrayList.add(physics);
        favoriteArrayList.add(biology);


        return favoriteArrayList;
    }


    @Override
    public void onFavoriteCourseClicked(String course_id) {

    }

    @Override
    public void onNextLectureClicked() {

    }

    @Override
    public void onUnsubscribeFavoriteCourseClicked(String course_id) {

    }
}
