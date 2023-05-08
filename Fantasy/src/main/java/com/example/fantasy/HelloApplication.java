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
        Connection con = DatabaseConnection.getConnection();
        if(con==null)
        {
           System.out.println("falied");
        }
        else
            System.out.println("done");

         launch();
         /*
           save data to player tale in ata base :
            1-we remove all records from database
            2-we put all data in players has table
          */
         String query ="DELETE FROM player";
         con = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = con.prepareStatement(query);
         preparedStatement.executeUpdate();
         for(Map.Entry<String ,Player>player:Player.getPlayers().entrySet()) {
             player.getValue().saveToDatebase(player.getValue());
         }

          /*
           save data to Footballer tale in ata base :
            1-we remove all records from database
            2-we put all data in footballers has table
          */
         query ="DELETE FROM footballer";
         con = DatabaseConnection.getConnection();
         preparedStatement = con.prepareStatement(query);
         preparedStatement.executeUpdate();
         for(Map.Entry<String ,Footballer>footballer:Footballer.getFootballers().entrySet()) {
            footballer.getValue().saveToDatebase(footballer.getValue());
         }
    }
}