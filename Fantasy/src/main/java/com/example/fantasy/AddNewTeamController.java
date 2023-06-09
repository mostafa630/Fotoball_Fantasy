package com.example.fantasy;

import ClassesAndDatabaseconnection.Team;
import ClassesAndDatabaseconnection.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// VERY IMPORTANT NOTE --> To initialize the comboBox you must implement Initializable interface.
public class AddNewTeamController implements Initializable {




    // Get the Team Name
    @FXML
    TextField teamName;

    // Get the league comboBox
    @FXML
    ComboBox<String> leagueComboBox;

  // initialize it with a list of Leagues
    @FXML
    public void initialize (URL url , ResourceBundle resourceBundle){
        ObservableList<String>list=FXCollections.observableArrayList("La Liga" , "Premier League" , "Serie A" , "Bundesliga" , "Ligue1");
        leagueComboBox.setItems(list);
    }

    // Add team in our teams hashtable if it is valid

    @FXML
    Label messageLabel;

    public void addNewTeam(){
        String newTeamName = new String(teamName.getText());

        /*
         to handle the choice of the league's selection, we have two cases:
         1- if the admin does not touch the ComboBox at all:
            This will cause runtime exception as we create new String without any value
         2- if the admin click on the ComboBox but does not choose any league

         */

        String newTeamLeague;
        try {
            newTeamLeague= new String(leagueComboBox.getSelectionModel().getSelectedItem());
        }catch (Exception exception){
            newTeamLeague = "";
        }

        if(Validation.teamNameValidation(newTeamName)){
            // check if this team is existed before or not
            if(!Team.getTeams().containsKey(newTeamName)){
                if(newTeamLeague.equals("")){
                    messageLabel.setText("Choose Team League First");
                }else {
                    Team.putTeamInTeams(newTeamName);
                    Team.putTeamsInTeamsLeagueHashtable(newTeamName, newTeamLeague);
                    messageLabel.setText("Done!!");
                }
            }else{
                messageLabel.setText("This Team is already exist");
            }

        }else{
            messageLabel.setText("Invalid Team Name");
        }
    }

    @FXML
    // this function to open Admin Page if we pressed Add New Footballer button
    // This page which Add New Footballer
    public void openAdminPage(ActionEvent event) throws IOException {
        try {
            // open Admin page form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        }
        catch (Exception ex) {
            System.out.println("going to admin page page failed");
        }

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

    @FXML
    // this function to open Add Points Of The Week Page if we pressed Add Points Of The Week button
    public void openAddPointsOfTheWeekPage(ActionEvent event) throws IOException {
        try{
            // open Add New Team page
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPointsOfTheWeek.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(),1108,563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        }catch (Exception ex){
            System.out.println("Going to Add Points Of The Week page failed");
        }
    }

}
