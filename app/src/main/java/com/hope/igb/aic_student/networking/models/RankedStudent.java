package com.hope.igb.aic_student.networking.models;

public class RankedStudent {

    private String student_id, name, academic_year, category, bio, image_url;
    private int rank, studying_points;


     RankedStudent() {}

    public RankedStudent(String student_id, String name, String academic_year, String category,
                         String bio, String image_url, int rank, int studying_points) {
        this.student_id = student_id;
        this.name = name;
        this.academic_year = academic_year;
        this.category = category;
        this.bio = bio;
        this.image_url = image_url;
        this.rank = rank;
        this.studying_points = studying_points;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getName() {
        return name;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public String getCategory() {
        return category;
    }

    public String getBio() {
        return bio;
    }

    public String getImage_url() {
        return image_url;
    }

    public int getRank() {
        return rank;
    }

    public int getStudying_points() {
        return studying_points;
    }
}
