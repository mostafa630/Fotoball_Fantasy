package ClassesAndDatabaseconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class Footballer {
    private String name;
    private String club;
    private String position;
    private float cost;
    private float totalPoints = 0;
    private float pointsThisWeek = 0;
    public static Hashtable<String,Footballer> footballers=new Hashtable<>();
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

    public float getPointsThisWeek() {
        return pointsThisWeek;
    }

    public void updateTotalPoints() {
        this.totalPoints += this.pointsThisWeek;
    }

    public void setTotalPoints(float totalPoints) {
        this.totalPoints = totalPoints;
    }

    public float getTotalPoints() {
        return totalPoints;
    }

    public static void putFotballerToFootballers(String name , Footballer footballer)
    {
        footballers.put(name,footballer);
    }

    public static Hashtable<String, Footballer> getFootballers() {
        return footballers;
    }

    public void saveToDatebase(Footballer footballer)
    {
        Connection con = DatabaseConnection.getConnection(); // connect with database
        // SQl command to inser in data base
        String query ="INSERT INTO footballer (name ,club ,position ,cost ,totalPoints,pointsThisWeek) VALUES(?,?,?,?,?,?);";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) { // insert values of columns for a row
            preparedStatement.setString(1,footballer.getName());
            preparedStatement.setString(2, footballer.getClub());
            preparedStatement.setString(3, footballer.getPosition());
            preparedStatement.setFloat(4,footballer.getCost());
            preparedStatement.setFloat(5,footballer.getTotalPoints());
            preparedStatement.setFloat(6,footballer.getPointsThisWeek());
            preparedStatement.executeUpdate(); // make this updates appear in the database
        }
        catch (SQLException ex)
        {
            System.out.println("insertion at footballer table failed");
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("connection close failed for footballer");
            }
        }
    }
    // function to load data of footballers from database
    public static void loadFootballerFromDatabase() {
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT * FROM footballer;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String name =resultSet.getString("name");
                String club=resultSet.getString("club");
                String position=resultSet.getString("position");
                float cost =resultSet.getFloat("cost");
                float totalPoints=resultSet.getFloat("totalPoints");
                float pointsThisWeek=resultSet.getFloat("pointsThisWeek");

                Footballer footballer =new Footballer(name,club,position,cost);
                footballer.setPointsThisWeek(pointsThisWeek);
                footballer.setTotalPoints(totalPoints);
                footballers.put(name,footballer);
            }

        } catch (SQLException ex) {
            System.out.println("load data from footballer table failed");
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Footballer{" +
                "name='" + name + '\'' +
                ", club='" + club + '\'' +
                ", position='" + position + '\'' +
                ", cost=" + cost +
                ", totalPoints=" + totalPoints +
                ", pointsThisWeek=" + pointsThisWeek +
                '}';
    }
}