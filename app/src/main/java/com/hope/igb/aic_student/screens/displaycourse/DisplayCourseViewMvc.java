package com.hope.igb.aic_student.screens.displaycourse;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.databinding.DisplayCourseFragmentBinding;
import com.hope.igb.aic_student.networking.models.Lesson;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;
import com.hope.igb.aic_student.screens.displaycourse.courselessons.DisplayLessonsRecyclerAdapter;
import java.util.ArrayList;

@SuppressLint("NotifyDataSetChanged")
class DisplayCourseViewMvc extends BaseObservableMvc<DisplayCourseViewMvc.Listener>
        implements
        DisplayLessonsRecyclerAdapter.DisplayLessonsAdapterListener {



    protected interface Listener {
        void onBackClicked();
        void onLessonClicked(int lesson_index);
    }


    private final LayoutInflater inflater;
    private final  boolean isBought;

    protected DisplayCourseViewMvc(LayoutInflater inflater, boolean wasBought){
        this.inflater = inflater;
        DisplayCourseFragmentBinding binding = DisplayCourseFragmentBinding.inflate(inflater);
        this.isBought = wasBought;

        setRootView(binding.getRoot());


        binding.courseDescription.displayBack.setOnClickListener(view -> onBackClicked());

    }

    private void onBackClicked() {
        for (Listener listener : getListeners())
            listener.onBackClicked();
    }


    protected void bindObjectivesRecyclerAdapter(ArrayList<String> objectives){
        RecyclerView recyclerView = findViewById(R.id.objectivesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ObjectivesRecyclerAdapter adapter = new ObjectivesRecyclerAdapter(objectives);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

  }

    protected void bindLessonsRecyclerAdapter(ArrayList<Lesson> lessons){
        RecyclerView recyclerView = findViewById(R.id.lessonsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DisplayLessonsRecyclerAdapter adapter = new DisplayLessonsRecyclerAdapter(inflater, lessons, isBought);
        recyclerView.setAdapter(adapter);
        adapter.registerAdapterListener(this);

        adapter.notifyDataSetChanged();


    }


    @Override
    public void onLessonClicked(int lesson_index) {
        for (Listener listener: getListeners())
            listener.onLessonClicked(lesson_index);
    }

}
