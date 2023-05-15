package com.example.fantasy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController{


    @FXML
    // this function to open login form if we pressed Log Out button
    public void openLoginForm(ActionEvent event) throws IOException {
        try {
            // open login form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();

        } catch (Exception ex) {
            System.out.println("going to login form failed");
        }

    }


    @FXML
    // this function to open addNewTeam page if we pressed Add New Team button
    public void openAddNewTeamPage(ActionEvent event) throws IOException{

         try{
             // open Add New Team page
             FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddNewTeam.fxml"));
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             Scene scene = new Scene(fxmlLoader.load(),1108,563);
             stage.setTitle("Fantasy");
             stage.setScene(scene);
             stage.resizableProperty().setValue(Boolean.FALSE);
             stage.show();
         }catch (Exception ex){
             System.out.println("Going to Add New Team page failed");
         }
    }

    @FXML
    // this function to open Add New Footballer Page if we pressed Add New Footballer button
    public void openAddNewFootballerPage(ActionEvent event) throws IOException {
        try{
            // open Add New Team page
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddNewFootballer.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(),1108,563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        }catch (Exception ex){
            System.out.println("Going to Add New Footballer page failed");
        }

    }

    @FXML
    // this function to open Update Footballer Data Page if we pressed Update Footballer Data button
    public void openUpdateFootballerDataPage(ActionEvent event) throws IOException {
        try{
            // open Add New Team page
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updateFootballerData.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(),1108,563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        }catch (Exception ex){
            System.out.println("Going to Update Footballer page failed");
        }
    }

    @FXML
    // this function to open Delete Footballer Page if we pressed Delete Footballer button
    public void openDeleteFootballerPage(ActionEvent event) throws IOException {
        try{
            // open Add New Team page
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeleteFootballer.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(),1108,563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        }catch (Exception ex){
            System.out.println("Going to Delete Footballer page failed");
        }
    }
}
