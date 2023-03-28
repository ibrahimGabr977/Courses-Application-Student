package com.hope.igb.aic_student.networking.models;

public class WinnerStudent extends RankedStudent {

    private  int place, prize, tournament_points;


    WinnerStudent(){}

    public WinnerStudent(String student_id, String name, String academic_year,String category, String bio,
                          String image_url, int rank, int studying_points,
                         int place, int prize, int tournament_points) {

        super(student_id,academic_year, name, category, bio, image_url, rank, studying_points);
        this.place = place;
        this.prize = prize;
        this.tournament_points = tournament_points;
    }


    public int getPlace() {
        return place;
    }

    public int getPrize() {
        return prize;
    }

    public int getTournament_points() {
        return tournament_points;
    }

}
