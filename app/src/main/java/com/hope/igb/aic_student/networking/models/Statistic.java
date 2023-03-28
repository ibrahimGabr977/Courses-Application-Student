package com.hope.igb.aic_student.networking.models;

public class Statistic {

    private String title, value, date;





    Statistic(){}


    public Statistic(String title, String value, String date) {
        this.title = title;
        this.value = value;
        this.date = date;

    }


    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }



}
