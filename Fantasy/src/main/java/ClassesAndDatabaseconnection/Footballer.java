package main.java.ClassesAndDatabaseconnection;

public class Footballer {

    private String name;
    private String club;
    private String position;
    private float cost;
    private float points = 0;
    private float pointsThisWeek = 0;

    public Footballer(String name,String club,String position,float cost) {
        this.name = name;
        this.club = club;
        this.position = position;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getClub() {
        return club;
    }

    public String getPosition() {
        return position;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setPointsThisWeek(float pointsThisWeek) {
        this.pointsThisWeek = pointsThisWeek;
    }

    public void updateTotalPoints() {
        this.points += this.pointsThisWeek;
    }



}