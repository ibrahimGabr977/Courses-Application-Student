package com.hope.igb.aic_student.screens.displayallcourses;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Course;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

import java.util.ArrayList;

class DisplayAllCoursesViewMvc extends BaseObservableMvc<DisplayAllCoursesViewMvc.ViewMvcListener> implements
        DisplayAllCoursesRecyclerAdapter.AdapterListener {


    private DisplayAllCoursesRecyclerAdapter adapter;

    protected interface ViewMvcListener {
        void onCourseItemClicked(String course_id);
        void onBackClicked();
    }



    DisplayAllCoursesViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.display_all_courses_fragment, parent, false));

        ImageView back = findViewById(R.id.display_back);

        back.setOnClickListener(v -> onBackClicked());

    }

    private void onBackClicked() {
        for (ViewMvcListener listener : getListeners())
            listener.onBackClicked();
    }


    @SuppressLint("NotifyDataSetChanged")
    protected void bindRecyclerView(ArrayList<Course> courses) {
        RecyclerView recyclerView = findViewById(R.id.displayRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DisplayAllCoursesRecyclerAdapter(courses);
        adapter.registerAdapterListener(this);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


    @Override
    public void unregisterListener(ViewMvcListener listener) {
        super.unregisterListener(listener);

        if (adapter != null)
            adapter.unregisterAdapterListener(this);
    }

    @Override
    public void onCourseClicked(String course_id) {
        for (ViewMvcListener listener : getListeners())
            listener.onCourseItemClicked(course_id);
    }


}
