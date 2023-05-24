package ClassesAndDatabaseconnection;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Footballer {
    private String name;
    private String club;
    private String position;
    private float cost;
    private float totalPoints = 0;
    private float pointsThisWeek = 0;
    private boolean updated =false ;
    public static Hashtable<String,Footballer> footballers=new Hashtable<>();
    public static List<String>goolKeepers=new ArrayList<>();
    public static List<String>defenders=new ArrayList<>();
    public static List<String>midfielders=new ArrayList<>();
    public static List<String>forwardes=new ArrayList<>();

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

    public void updateCost(float plusCost){
        this.cost+=plusCost;
}

    public float getTotalPoints() {
        return totalPoints;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public static void putFotballerToFootballers(String name , Footballer footballer)
    {
        footballers.put(name,footballer);
    }

    public static Hashtable<String, Footballer> getFootballers() {
        return footballers;
    }


    public static void addNewFootballer(String name, String club, String position, float cost) {

        Footballer footballer = new Footballer(name,club,position,cost);
        putFotballerToFootballers(name,footballer);

    }
    
    public static void addNewFootballerToPositionList(String position , String name){
        if(position.equals("Goalkeeper")){
            goolKeepers.add(name);
        } else if (position.equals("Defender")) {
            defenders.add(name);
        }else if(position.equals("Midfielder")){
            midfielders.add(name);
        }else{
            // Forward
            forwardes.add(name);
        }
    }

    public static void deleteFootballerFromPositionList(String currentPosition , String name){
        if(currentPosition.equals("Forward")) {
            forwardes.remove(name);
        }else if(currentPosition.equals("Midfielder")) {
            midfielders.remove(name);
        }else if(currentPosition.equals("Goalkeeper")) {
            goolKeepers.remove(name);
        }else {
            // currentPosition = Defender
            defenders.remove(name);
        }
    }

    public static void deleteFootballer(String name, String club) {

        // delete footballer from Teams hash table
        Team.getTeams().get(club).remove(name);

        // remove footballer from positions hash table
        String currentPosition = footballers.get(name).position;
        deleteFootballerFromPositionList(currentPosition , name);


        // delete footballer from footballers hash table
        float footballerPrice =footballers.get(name).getCost() ;
        footballers.remove(name);

        // delete footballer from all players that have this footballer.
        for(Map.Entry<String, Player> playerEntry : Player.getPlayers().entrySet()){
            int index=0 ;

            for(Pair<String ,Boolean> playerTeam : playerEntry.getValue().myTeam)
            {
                if(playerTeam.getKey().equals(name))
                {
                    playerEntry.getValue().setBudget(playerEntry.getValue().getBudget()+footballerPrice);
                    playerEntry.getValue().putFootballerInMyTeam(index,"null", false);
                    break ;
                }else{
                    index++;
                }
            }
        }

    }
    public static void updateFootballer(String name, String club, float cost) {

        // get the footballer current club
        String currentClub = footballers.get(name).club;
        // get the footballer current position
        String currentPosition = footballers.get(name).position;

        // check if the footballer's club change or not
        if(!currentClub.equals(club)) {

            // remove the footballer from this club
            Team.getTeams().get(currentClub).remove(name);

            // add it to the new club
            Team.getTeams().get(club).add(name);

        }

        // change footballer's cost
        footballers.get(name).cost = cost;

    }

    public static int addPointsToFootballers(String name, String position,
                                             int goals, int assists,
                                             boolean yellowCard, boolean redCard,
                                             boolean playedMoreThan60Min,
                                             int penaltyMiss, int ownGoal,
                                             boolean cleanSheet, // for Def, Mid and GK only.
                                             int penaltySave)    // for Gk only.
    {

        int localPoints= 0;

        // +3 points for each assist
        localPoints += assists*3;

        // -2 points for each penaltyMiss
        localPoints -= penaltyMiss * 2;

        // -2 points for each ownGoal
        localPoints -= ownGoal * 2;


        // for GoalKeeper and Defender +6 points for each goal
        if(position.equals("GoalKeeper") || position.equals("Defender")) {
            localPoints += goals * 6;
        }
        // for Midfielder +5 points for each goal
        else if(position.equals("Midfielder")){
            localPoints += goals * 5;
        }
        // for Forward +4 points for each goal
        else {
            localPoints += goals * 4;
        }

        // -1 point for yellow card
        if(yellowCard) {
            localPoints --;
        }

        // -3 points for red card
        if(redCard){
            localPoints -= 3;
        }
        // +1 point if footballer played More Than 60 Min
        if(playedMoreThan60Min){
            localPoints ++;
        }

        // For Gk and Def
        // +4 points for clean sheet
        if(cleanSheet && (position.equals("Goalkeeper") || position.equals("Defender"))) {
            localPoints += 4;
        }
        // For Mid
        // +4 points for clean sheet
        else if(cleanSheet && position.equals("Midfielder")) {
            localPoints += 1;
        }

        // For Gk
        // +5 points for each penalty save.
        if(position.equals("Goalkeeper")) {
            localPoints += penaltySave * 5;
        }


        return localPoints;
    }


    /*
     we save data of footballer in the database :
     we put the core data of footballers in the table of footballers i the database
     */
    public void saveToDatebase(Footballer footballer)
    {
        Connection con = DatabaseConnection.getConnection(); // connect with database
        // SQl command to inser in data base
        String query ="INSERT INTO footballer (name ,club ,position ,cost ,totalPoints,pointsThisWeek,updated) VALUES(?,?,?,?,?,?,?);";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) { // insert values of columns for a row
            preparedStatement.setString(1,footballer.getName());
            preparedStatement.setString(2, footballer.getClub());
            preparedStatement.setString(3, footballer.getPosition());
            preparedStatement.setFloat(4,footballer.getCost());
            preparedStatement.setFloat(5,footballer.getTotalPoints());
            preparedStatement.setFloat(6,footballer.getPointsThisWeek());
            preparedStatement.setBoolean(7,footballer.isUpdated());
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
    /*
    load datafrom data of fooltballer from database :
       firsrt we put all footballers in hash table of footballers
       second if the footballer is :
            goolkeeper we put him in the list of goolkeepers
            defender we put it in the list of defenders
            midfilder we put him in the list of midfilders
            forward we put him in the list of the forwards
     */
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
                boolean updated =resultSet.getBoolean("updated");

                // Here I will load data of the teams

                Team.putFootballerInTeams(club , name);

                // Done

                Footballer footballer =new Footballer(name,club,position,cost);
                footballer.setPointsThisWeek(pointsThisWeek);
                footballer.setTotalPoints(totalPoints);
                footballer.setUpdated(updated);
                // save in hash table of players
                footballers.put(name,footballer);
                // save in position list
                if(position.equals("Goalkeeper"))
                    goolKeepers.add(name);
                else  if(position.equals("Defender"))
                    defenders.add(name);
                else if (position.equals("Midfielder"))
                    midfielders.add(name);
                else
                    forwardes.add(name);
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