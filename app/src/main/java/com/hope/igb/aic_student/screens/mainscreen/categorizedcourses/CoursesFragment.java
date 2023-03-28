package com.hope.igb.aic_student.screens.mainscreen.categorizedcourses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Course;
import com.hope.igb.aic_student.common.base.BaseObservableFragment;
import com.hope.igb.aic_student.screens.mainscreen.categorizedcourses.courseitems.HorizontalCourseItemViewMvc;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CoursesFragment extends BaseObservableFragment<CoursesFragment.CoursesFragmentListener>
        implements HorizontalCourseItemViewMvc.CoursesItemListener {





    public interface CoursesFragmentListener{
        void onSeeAllCoursesOfCategoryClicked(String category);
        void onCourseItemClicked(String course_id);
    }


    private CoursesViewMvc<HorizontalCourseItemViewMvc.CoursesItemListener> viewMvc;
    private LinkedHashMap<String, ArrayList<Course>> coursesByCategoriesTestSets;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        viewMvc = new CoursesViewMvc<>(inflater, container);


        coursesByCategoriesTestSets = new LinkedHashMap<>(5);
        if (savedInstanceState == null)
            initTestSet();



        return viewMvc.getRootView();
    }


    @Override
    public void onStart() {
        super.onStart();
        viewMvc.registerListener(this);
        viewMvc.bindListView(coursesByCategoriesTestSets);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
    }




    @Override
    public void onSeeAllCoursesOfCategoryClicked(String category) {
        for (CoursesFragmentListener listener: getListeners())
            listener.onSeeAllCoursesOfCategoryClicked(category);
    }

    @Override
    public void onCourseItemClicked(String course_id) {
        for (CoursesFragmentListener listener: getListeners())
            listener.onCourseItemClicked(course_id);
    }








    private void initTestSet(){
        coursesByCategoriesTestSets.put("عام", initTestList1());
        coursesByCategoriesTestSets.put("أزهر", initTestList2());
        coursesByCategoriesTestSets.put("لغات", initTestList3());
    }




    //test data sets




    private ArrayList<Course> initTestList1(){
        ArrayList<Course> courses = new ArrayList<>(5);


        Course physics = new Course(null, "شرح مادة الفيزياء",R.drawable.z_test_physics_image,
                "أ/محمد عبدالمعبود", String.valueOf(R.drawable.z_test_teacher_image1),
                "عام", "3ثانوي", 35);

        Course biology = new Course(null, "شرح مادة الأحياء",R.drawable.z_test_biology_image,
                "أ/عاطف الشرقاوي", String.valueOf(R.drawable.z_test_teacher_image2),
                "عام", "3ثانوي", 23);


        Course chemistry = new Course(null, "شرح مادة الكيمياء",R.drawable.z_test_chemistry_image,
                "أ/أسامة التلاوي", String.valueOf(R.drawable.z_test_teacher_image3),
                "عام", "3ثانوي", 27);


        Course math = new Course(null, "شرح مادة الرياضيات",R.drawable.z_test_math_image,
                "أ/أحمد السيد", String.valueOf(R.drawable.z_test_teacher_image4),
                "عام", "3ثانوي", 30);


        Course history = new Course(null, "شرح مادة التاريخ",R.drawable.z_test_history_image,
                "أ/أحمد عصر", String.valueOf(R.drawable.z_test_teacher_image5),
                "عام", "3ثانوي", 19);

        courses.add(chemistry);
        courses.add(math);
        courses.add(history);
        courses.add(biology);
        courses.add(physics);




        return courses;
    }


    private ArrayList<Course> initTestList2(){
        ArrayList<Course> courses = new ArrayList<>(5);


        Course physics = new Course(null, "شرح مادة الفيزياء",R.drawable.z_test_physics_image,
                "أ/محمد عبدالمعبود", String.valueOf(R.drawable.z_test_teacher_image1),
                "أزهر", "3ثانوي", 35);

        Course biology = new Course(null, "شرح مادة الأحياء",R.drawable.z_test_biology_image,
                "أ/عاطف الشرقاوي", String.valueOf(R.drawable.z_test_teacher_image2),
                "أزهر", "3ثانوي", 23);


        Course chemistry = new Course(null, "شرح مادة الكيمياء",R.drawable.z_test_chemistry_image,
                "أ/أسامة التلاوي", String.valueOf(R.drawable.z_test_teacher_image3),
                "أزهر", "3ثانوي", 27);


        Course math = new Course(null, "شرح مادة الرياضيات",R.drawable.z_test_math_image,
                "أ/أحمد السيد", String.valueOf(R.drawable.z_test_teacher_image4),
                "أزهر", "3ثانوي", 30);


        Course history = new Course(null, "شرح مادة التاريخ",R.drawable.z_test_history_image,
                "أ/أحمد عصر", String.valueOf(R.drawable.z_test_teacher_image5),
                "أزهر", "3ثانوي", 19);

        courses.add(history);
        courses.add(biology);
        courses.add(math);
        courses.add(chemistry);
        courses.add(physics);




        return courses;
    }



    private ArrayList<Course> initTestList3(){
        ArrayList<Course> courses = new ArrayList<>(5);


        Course physics = new Course(null, "شرح مادة الفيزياء",R.drawable.z_test_physics_image,
                "أ/محمد عبدالمعبود", String.valueOf(R.drawable.z_test_teacher_image1),
                "لغات", "3ثانوي", 35);

        Course biology = new Course(null, "شرح مادة الأحياء",R.drawable.z_test_biology_image,
                "أ/عاطف الشرقاوي", String.valueOf(R.drawable.z_test_teacher_image2),
                "لغات", "3ثانوي", 23);


        Course chemistry = new Course(null, "شرح مادة الكيمياء",R.drawable.z_test_chemistry_image,
                "أ/أسامة التلاوي", String.valueOf(R.drawable.z_test_teacher_image3),
                "لغات", "3ثانوي", 27);


        Course math = new Course(null, "شرح مادة الرياضيات",R.drawable.z_test_math_image,
                "أ/أحمد السيد", String.valueOf(R.drawable.z_test_teacher_image4),
                "لغات", "3ثانوي", 30);


        Course history = new Course(null, "شرح مادة التاريخ",R.drawable.z_test_history_image,
                "أ/أحمد عصر", String.valueOf(R.drawable.z_test_teacher_image5),
                "لغات", "3ثانوي", 19);

        courses.add(math);
        courses.add(chemistry);
        courses.add(physics);
        courses.add(biology);
        courses.add(history);




        return courses;
    }



}