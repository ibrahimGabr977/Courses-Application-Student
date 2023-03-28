package com.hope.igb.aic_student.networking.models;

public class Course {

    private String course_id, course_title, tutor_name,
            tutor_image_url, course_category, academic_year;
    private int lessons_count, course_image_res;


    Course() {
    }


    public Course(String course_id, String course_title, int course_image_res,
                  String tutor_name, String tutor_image_url, String course_category,
                  String academic_year, int lessons_count) {

        this.course_id = course_id;
        this.course_title = course_title;
        this.course_image_res = course_image_res;
        this.tutor_name = tutor_name;
        this.tutor_image_url = tutor_image_url;
        this.course_category = course_category;
        this.academic_year = academic_year;
        this.lessons_count = lessons_count;
    }


    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public int getCourse_image_res() {
        return course_image_res;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public String getTutor_image_url() {
        return tutor_image_url;
    }

    public String getCourse_category() {
        return course_category;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public int getLessons_count() {
        return lessons_count;
    }
}
