package com.example.fantasy;
import ClassesAndDatabaseconnection.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

//
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
        stage.setTitle("Fantasy");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {


        /*
            load data of teams from database:
            call function in the Team class that load data from fantasy_teams table in database to teams hashtable
         */
        Team.loadTeamsFromDatabase();

        /*
            load data of footballers from database:
            call function in the Footballer class that load data from database to footballers hashtable
         */
        Footballer.loadFootballerFromDatabase();
         /*
            load data of players in database:
            call function in the player class that load data from database to players hashtable
         */
        Player.loadPlayersFromDatabase();
         /*
              connect with the database :
                if connection completed print done on console
                if connection failed print failed on console
          */
            Connection con = DatabaseConnection.getConnection();
            if (con == null) {
                System.out.println("failed");
            } else
                System.out.println("done");

            launch();  // launch the stage

        /*
        // just for test that Teams functions runs successfully
        for(Map.Entry<String , ArrayList<String>> team : Team.getTeams().entrySet()){
            System.out.println(team.getKey() + " team From " + Team.getTeamsLeagueHashtable().get(team.getKey())+ " with players: ");
            for(String footballerName : team.getValue()){
                System.out.println(footballerName);
            }
            System.out.println("----------------------");
        }
        // Done
        */

        /*
        // test the team name validation and footballer Name Validation

        System.out.print("Manchester  United ");
        if(Validation.teamNameValidation("Manchester  United")){
            System.out.println("is valid team name");
        }else {
            System.out.println("is not valid team name");
        }

        System.out.print("Farouk");
        if(Validation.footballerNameValidation("Faro+uk")){
            System.out.println("is valid footballer name");
        }else {
            System.out.println("is not valid footballer name");
        }

        System.out.print("Paris san Germain ");
        if(Validation.teamNameValidation("Paris Sa1n Germain")){
            System.out.println("is valid team name");
        }else {
            System.out.println("is not valid team name");
        }
        System.out.print("Paris San Germain ");
        if(Validation.footballerNameValidation("ParisSan Germain")){
            System.out.println("is valid team name");
        }else {
            System.out.println("is not valid team name");
        }

        // Done
        */

         /*
           save data to player tale in ata base :
            1-we remove all records from database
            2-we put all data in players has table
          */
            String query = "DELETE FROM player";
            con = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            query = "DELETE FROM teams";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            for (Map.Entry<String, Player> player : Player.getPlayers().entrySet()) {
                player.getValue().saveToDatebase(player.getValue());
            }
          /*
           save data to Footballer table in ata base :
            1-we remove all records from database
            2-we put all data in footballers has table
          */
            query = "DELETE FROM footballer";
            con = DatabaseConnection.getConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            for (Map.Entry<String, Footballer> footballer : Footballer.getFootballers().entrySet()) {
                footballer.getValue().saveToDatebase(footballer.getValue());
            }
        /*
            save data to fantasy_teams table in database :
            1-we remove all records from fantasy_teams table
            2-we put all data from teams HashTable in fantasy_teams table
       */

        //
        query = "DELETE FROM fantasyteams";
        con = DatabaseConnection.getConnection();
        preparedStatement = con.prepareStatement(query);
        preparedStatement.executeUpdate();

        //
        for(Map.Entry<String , ArrayList<String>> team : Team.getTeams().entrySet()){
            Team.saveTeamsToDatabase(team.getKey());
        }
    }
}
