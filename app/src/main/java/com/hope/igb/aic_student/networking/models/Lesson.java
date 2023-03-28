package com.hope.igb.aic_student.networking.models;

public class Lesson {
    private   String lesson_id, lesson_title, lesson_duration, lesson_url;


    Lesson(){}



    public Lesson(String lesson_id, String lesson_title, String lesson_duration, String lesson_url){
        this.lesson_id = lesson_id;
        this.lesson_title = lesson_title;
        this.lesson_duration = lesson_duration;
        this.lesson_url = lesson_url;
    }



    public String getLesson_id() {
        return lesson_id;
    }

    public String getLesson_title() {
        return lesson_title;
    }

    public String getLesson_duration() {
        return lesson_duration;
    }


    public String getLesson_url() {
        return lesson_url;
    }

    public void setLesson_url(String lesson_url) {
        this.lesson_url = lesson_url;
    }
}
