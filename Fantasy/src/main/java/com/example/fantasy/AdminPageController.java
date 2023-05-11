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

public class AdminPageController implements Initializable {
    @FXML
    ComboBox<String>position_combobox;
     @FXML
     // function to fill combobox of positions with data
     public void initialize (URL url , ResourceBundle resourceBundle)
     {
         ObservableList<String>list=FXCollections.observableArrayList("Defender","Midfielder","Goalkeeper","Forward");
         position_combobox.setItems(list);
     }
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
    // this function to open deleteFootballerPage if we pressed Delete Footballer button
    public void openDeleteFootballerPage(ActionEvent event) throws IOException {
        try {
            // open Admin page form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("deleteFootballerPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        }
        catch (Exception ex) {
            System.out.println("going to delete footballer page failed");
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



}
