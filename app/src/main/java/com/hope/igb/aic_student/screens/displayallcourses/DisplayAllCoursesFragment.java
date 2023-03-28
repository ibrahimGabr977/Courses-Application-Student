package com.hope.igb.aic_student.screens.displayallcourses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Course;
import com.hope.igb.aic_student.common.base.BaseFragment;

import java.util.ArrayList;

public class DisplayAllCoursesFragment extends BaseFragment
        implements DisplayAllCoursesViewMvc.ViewMvcListener {

    private DisplayAllCoursesViewMvc viewMvc;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        viewMvc = new DisplayAllCoursesViewMvc(inflater, container);


        return viewMvc.getRootView();
    }


    @Override
    public void onStart() {
        super.onStart();
        viewMvc.bindRecyclerView(testSet());
        viewMvc.registerListener(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
    }

    private ArrayList<Course> testSet() {
        ArrayList<Course> courses = new ArrayList<>(5);


        Course physics = new Course(null, "شرح مادة الفيزياء", R.drawable.z_test_physics_image,
                "أ/محمد عبدالمعبود", String.valueOf(R.drawable.z_test_teacher_image1),
                "أزهر", "3ثانوي", 35);

        Course biology = new Course(null, "شرح مادة الأحياء", R.drawable.z_test_biology_image,
                "أ/عاطف الشرقاوي", String.valueOf(R.drawable.z_test_teacher_image2),
                "أزهر", "3ثانوي", 23);


        Course chemistry = new Course(null, "شرح مادة الكيمياء", R.drawable.z_test_chemistry_image,
                "أ/أسامة التلاوي", String.valueOf(R.drawable.z_test_teacher_image3),
                "أزهر", "3ثانوي", 27);


        Course math = new Course(null, "شرح مادة الرياضيات", R.drawable.z_test_math_image,
                "أ/أحمد السيد", String.valueOf(R.drawable.z_test_teacher_image4),
                "أزهر", "3ثانوي", 30);


        Course history = new Course(null, "شرح مادة التاريخ", R.drawable.z_test_history_image,
                "أ/أحمد عصر", String.valueOf(R.drawable.z_test_teacher_image5),
                "أزهر", "3ثانوي", 19);

        courses.add(history);
        courses.add(biology);
        courses.add(math);
        courses.add(chemistry);
        courses.add(physics);


        return courses;


    }



    @Override
    public void onCourseItemClicked(String course_id) {
        getScreenNavigator()
                .toDisplayCourse("from display all courses");
    }


    @Override
    public void onBackClicked() {
        requireActivity().onBackPressed();
    }
}