package com.hope.igb.aic_student.networking.models;

public class Favorite extends Course{

    private final int completed_hours, course_hours;


    public Favorite(String course_id, String course_title, int course_image_res,
                    String tutor_name, String tutor_image_url, String course_category, String academic_year, int lessons_count,
                    int completed_hours, int course_hours) {

        super(course_id, course_title, course_image_res, tutor_name, tutor_image_url, course_category, academic_year, lessons_count);
        this.completed_hours = completed_hours;
        this.course_hours = course_hours;
    }


    public int getCourse_hours() {
        return course_hours;
    }

    public int getCompleted_hours() {
        return completed_hours;
    }
}
