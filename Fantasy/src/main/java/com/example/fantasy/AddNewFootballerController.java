package com.example.fantasy;

import ClassesAndDatabaseconnection.Footballer;
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
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


// VERY IMPORTANT NOTE --> To initialize the comboBox you must implement Initializable interface.
public class AddNewFootballerController implements Initializable{

    @FXML
    TextField footballerName;

    @FXML
    TextField footballerCost;

    @FXML
    ComboBox<String> positionComboBox;

    @FXML
    ComboBox<String> teamComboBox;

    @FXML
    Label messageLabel;

    @FXML
    // function to fill ComboBox with data
    public void initialize (URL url , ResourceBundle resourceBundle)
    {

        ObservableList<String> positionsList = FXCollections.observableArrayList("Defender","Midfielder","Goalkeeper","Forward");
        positionComboBox.setItems(positionsList);

        ObservableList<String> teamsList = FXCollections.observableArrayList();
        // now we will fill the teams list with teams' name from:
        // 1- teams HashTable. or
        // 2- teamsLeagueHashtable.
        // I will loop in teamsLeagueHashtable as it is faster

        for(Map.Entry<String , String> teamLeague : Team.getTeamsLeagueHashtable().entrySet()){
            teamsList.add(teamLeague.getKey());
        }
        teamComboBox.setItems(teamsList);

    }


    public void addNewFootballer(){
        String newFootballerName = footballerName.getText();
        String newFootballerTeam = teamComboBox.getSelectionModel().getSelectedItem();
        String newFootballerPosition = positionComboBox.getSelectionModel().getSelectedItem();
        String newFootballerCost = footballerCost.getText();

        // I will assume that footballer name is valid firstly
        // if valid I will check footballer cost
        // else I will stop here
        boolean checkFootballerName = true;
        if(Validation.footballerNameValidation(newFootballerName)){
            if(Footballer.getFootballers().containsKey(newFootballerName)){
               messageLabel.setText("This Footballer is already exist!!");
               checkFootballerName = false;
            }
        }else{
            messageLabel.setText("Invalid Name!!");
        }

        // now I will check footballer cost if footballer name is valid
        if(checkFootballerName){

            if(Validation.footballerPriceValidation(newFootballerCost)){
                float price = Float.parseFloat(newFootballerCost);

                /*
                Rules for footballers prices:
                Goalkeeper: £4.0k to £6.0k
                Defender: £4.5k to £7.5k
                Midfielder: £5.0k to £12.0k
                Forward: £6.0k to £14.0k
                 */
                Pair<String , Boolean> pair = Validation.footballerPricePositionValidation(newFootballerPosition , price);
                if(pair.getValue()){
                    // add footballer in main hashtable
                    Footballer.addNewFootballer(newFootballerName , newFootballerTeam , newFootballerPosition , price);
                    // add footballer to its position's list
                    Footballer.addNewFootballersToPositionList(newFootballerPosition , newFootballerName);
                    messageLabel.setText("Done");
                }else{
                    messageLabel.setText(pair.getKey());
                }

            }else{
                messageLabel.setText("Invalid Price!!");
            }

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
    // this function to open Admin Page if we pressed Admin Photo
    public void openAdminPage(ActionEvent event) throws IOException {
        try {
            // open login form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();

        } catch (Exception ex) {
            System.out.println("going to Admin Page failed");
        }

    }



    }
