package com.hope.igb.aic_student.screens.displayallcourses;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Course;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

 class DisplayAllCoursesItemViewMvc extends BaseObservableMvc<DisplayAllCoursesItemViewMvc.DisplayItemListener> {

    protected interface DisplayItemListener {
        void onCourseClicked(String course_id);
    }


     private ImageView tutor_image;
     private TextView course_title, tutor_name, lessons_count, academic_year, category;


    protected DisplayAllCoursesItemViewMvc(LayoutInflater inflater, ViewGroup parent){
        setRootView(inflater.inflate(R.layout.display_all_courses_holder, parent, false));


        initViews();


    }




    private void initViews(){
        tutor_image = findViewById(R.id.display_holder_teacher_image);
        course_title = findViewById(R.id.display_holder_title);
        tutor_name = findViewById(R.id.display_holder_tutor_name);
        lessons_count = findViewById(R.id.display_holder_lessons_count);
        academic_year = findViewById(R.id.display_holder_academic_year);
        category = findViewById(R.id.display_holder_category_text);
    }



    protected void bindRecyclerItems(Course course){

        tutor_image.setImageResource(Integer.parseInt(course.getTutor_image_url()));
        course_title.setText(course.getCourse_title());
        tutor_name.setText(course.getTutor_name());

        lessons_count.setText(String.valueOf(course.getLessons_count()));
        academic_year.setText(course.getAcademic_year());
        category.setText(course.getCourse_category());
    }




}
