package com.hope.igb.aic_student.networking.models;

public class Student {

    private final String email, password, name;

    public Student(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    //
//    private String student_id, email, password, phone_number,  name, category, academic_year, image_url, bio;
//    private int rank, studying_points;
//
//
//    Student() {
//    }
//
//    public Student(String student_id, String email, String password, String phone_number,
//                   String name, String category, String academic_year, String image_url, String bio,
//                   int rank, int studying_points) {
//        this.student_id = student_id;
//        this.email = email;
//        this.password = password;
//        this.phone_number = phone_number;
//        this.name = name;
//        this.category = category;
//        this.academic_year = academic_year;
//        this.image_url = image_url;
//        this.bio = bio;
//        this.rank = rank;
//        this.studying_points = studying_points;
//    }
//
//    public String getStudent_id() {
//        return student_id;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public String getAcademic_year() {
//        return academic_year;
//    }
//
//    public String getImage_url() {
//        return image_url;
//    }
//
//    public String getBio() {
//        return bio;
//    }
//
//    public int getRank() {
//        return rank;
//    }
//
//    public int getStudying_points() {
//        return studying_points;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPhone_number() {
//        return phone_number;
//    }
}
