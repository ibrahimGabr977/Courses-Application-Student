package com.hope.igb.aic_student.screens.mainscreen.categorizedcourses.courseitems;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hope.igb.aic_student.R;
import com.hope.igb.aic_student.networking.models.Course;
import com.hope.igb.aic_student.common.views.BaseObservableMvc;

 public class HorizontalCourseItemViewMvc extends BaseObservableMvc<HorizontalCourseItemViewMvc.CoursesItemListener> {


    public interface CoursesItemListener {
        void onSeeAllCoursesOfCategoryClicked(String category);
        void onCourseItemClicked(String course_id);
    }

    private ConstraintLayout container_view;
    private  TextView see_all, course_title, tutor_name;
    private  ImageView course_image, tutor_image;


    public HorizontalCourseItemViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.main_courses_horizontal_holder, parent, false));

        ViewGroup.LayoutParams params = getRootView().getLayoutParams();
        params.width = (int) (getScreenWidth() * 0.31);

        getRootView().setLayoutParams(params);
        initViews();
    }



    private int getScreenWidth(){

        Point size = new Point();
        WindowManager w = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);

        w.getDefaultDisplay().getSize(size);

        return size.x;
        //        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }


    private void initViews(){
        container_view = findViewById(R.id.container_view);
        course_title = findViewById(R.id.course_holder_title);
        tutor_name =findViewById(R.id.display_holder_teacher_name);
        course_image = findViewById(R.id.course_holder_image);
        tutor_image = findViewById(R.id.course_holder_teacher_image);

        see_all = findViewById(R.id.course_holder_see_all_text_btn);
    }


//    protected void bindViewHolder(int position, Course course){
//
//        if (isLastScrolledItem(position))
//            bindSeeAll(course.getCourse_category());
//
//
//        else
//            bindCardItemData(course);
//
//    }





    protected void bindSeeAll(String category){
        see_all.setVisibility(View.VISIBLE);
        see_all.setOnClickListener(v -> {
            for (CoursesItemListener listener : getListeners())
                listener.onSeeAllCoursesOfCategoryClicked(category);
        });

        container_view.setOnClickListener(null);
        container_view.setVisibility(View.INVISIBLE);
    }


    protected void bindCardItemData(Course course){
        course_image.setImageResource(course.getCourse_image_res());

        //just for testing it should be
        //imageLoader.loadImage(course.getTutor_image_url()).into(tutor_image);
        tutor_image.setImageResource(Integer.parseInt(course.getTutor_image_url()));

        course_title.setText(course.getCourse_title());
        tutor_name.setText(course.getTutor_name());


        bindCardItemsClicks(course.getCourse_id());

    }



    private void bindCardItemsClicks(String course_id){
        see_all.setOnClickListener(null);
        see_all.setVisibility(View.GONE);

        container_view.setOnClickListener(v -> {
            for (CoursesItemListener listener : getListeners())
                listener.onCourseItemClicked(course_id);
        });
    }









}
