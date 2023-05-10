package ClassesAndDatabaseconnection;

import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class Team {
    private String teamName;
    private String teamLeague;

    // key --> String of team name
    // value --> for each team the value is its league name

    private static Hashtable<String , String> teamsLeagueHashtable = new Hashtable<>();


    // key --> String of team name
    // value --> for each team the value is list of its players
    private static Hashtable<String , ArrayList<String>> teams = new Hashtable<>();


    public static Hashtable<String, String> getTeamsLeagueHashtable() {
        return teamsLeagueHashtable;
    }

    public Team(String teamName, String teamLeague) {
        this.teamName = teamName;
        this.teamLeague = teamLeague;
    }

    public static Hashtable<String ,  ArrayList<String>> getTeams() {
        return teams;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLeague() {
        return teamLeague;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamLeague(String teamLeague) {
        this.teamLeague = teamLeague;
    }

    public static void putTeamsInTeamsLeagueHashtable(String teamName , String teamLeague){
        teamsLeagueHashtable.put(teamName , teamLeague);
    }

    public static void putTeamInTeams(String team)
    {
        teams.put(team , new ArrayList<>());
    }
    public static void putFootballerInTeams(String team , String footballerName){
        if(!teams.containsKey(team)){
            putTeamInTeams(team);
        }
            teams.get(team).add(footballerName);
    }

    public static void saveTeamsToDatabase(String team){
        // connect to the Database
        Connection con = DatabaseConnection.getConnection();

        // SQL command to insert in table
        String query = "INSERT INTO fantasyteams  (team_name ,league) VALUES(?,?);";

        // replace ? with actual data

        try (PreparedStatement preparedStatement = con.prepareStatement(query)){

            preparedStatement.setString(1 , team);
            preparedStatement.setString(2, teamsLeagueHashtable.get(team));

            // make this updates in the dataBase
            preparedStatement.executeUpdate();


        }catch (SQLException ex){
            System.out.println("Insertion in fantasy_teams table failed");
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("connection close failed for fantasy_teams table");
            }
        }

    }

    public static void loadTeamsFromDatabase(){
        // connect to the Database
        Connection con = DatabaseConnection.getConnection();

        // SQL command to read data from table
         String query = "SELECT * FROM fantasyteams;";

         // read data from each row in fantasy_teams table
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String teamName = resultSet.getString("team_name");
                String teamLeague = resultSet.getString("league");

                // put data in teams hash table
                putTeamInTeams(teamName);
                putTeamsInTeamsLeagueHashtable(teamName , teamLeague);

            }

        } catch (SQLException ex) {
            System.out.println("load data from fantasy_teams table failed");
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
