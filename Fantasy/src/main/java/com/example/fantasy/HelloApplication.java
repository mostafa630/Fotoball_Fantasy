package com.example.fantasy;
import ClassesAndDatabaseconnection.DatabaseConnection;
import ClassesAndDatabaseconnection.Footballer;
import ClassesAndDatabaseconnection.Player;
import ClassesAndDatabaseconnection.Validation;
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
        //
        /*String filePath = "C:/Users/Admin/Desktop/Fotoball_Fantasy/Fantasy/Database.files/FootballersNames.txt";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            Footballer footballer;
            String line;
            while ((line = br.readLine()) != null) {
                String[] params = line.split("#");
                // Process the individual parameters here
                // 0 -> name // 1 -> club // 2 -> position // 3 -> cost
                float cost = Float.parseFloat(params[3]);
                footballer = new Footballer(params[0],params[1], params[2], cost );
                Footballer.putFotballerToFootballers(footballer.getName() , footballer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/          //
            System.out.println(Footballer.footballers.size());
        System.out.println(Player.getPlayersNationalIDs().size());
            Connection con = DatabaseConnection.getConnection();
            if (con == null) {
                System.out.println("falied");
            } else
                System.out.println("done");

            for (Map.Entry<String, Player> playerEntry : Player.getPlayers().entrySet())
                System.out.println(playerEntry.getKey() + "--->" + playerEntry.getValue().toString());
            launch();  // launch the stage


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
           save data to Footballer tale in ata base :
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
        }
    }
