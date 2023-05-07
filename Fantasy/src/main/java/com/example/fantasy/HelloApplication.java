package com.example.fantasy;
import ClassesAndDatabaseconnection.DatabaseConnection;
import ClassesAndDatabaseconnection.Footballer;
import ClassesAndDatabaseconnection.Validation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
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
    public static void main(String[] args) {
        Connection con = DatabaseConnection.getConnection();
        if(con==null)
        {
           System.out.println("falied");
        }
        else
            System.out.println("done");

        Footballer foot1 =new Footballer("sasa","arsenal","goolkeeper",1000f);
        Footballer.putFotballerToFootballers(foot1.getName(),foot1);
        Footballer foot2 =new Footballer("lokg","arsenal","goolkeeper",1000f);
        Footballer.putFotballerToFootballers(foot2.getName(),foot2);


        for(Map.Entry<String ,Footballer> data :Footballer.footballers.entrySet()) {
            System.out.println(data.getKey()+"--->"+data.getValue().toString());
        }


         launch();
    }
}