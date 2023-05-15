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
        // fill position comboBox
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
        /*
         to handle the choice of the ComboBox selection, we have two cases:
         1- if the admin does not touch the ComboBox at all:
            This will cause runtime exception as we create new String without any value
         2- if the admin click on the ComboBox but does not choose anything

         */
        String newFootballerTeam;

        try{
            newFootballerTeam = new String(teamComboBox.getSelectionModel().getSelectedItem());
        }catch (Exception exception){
            newFootballerTeam = "";
        }

        String newFootballerPosition;
        try{
            newFootballerPosition = new String(positionComboBox.getSelectionModel().getSelectedItem());
        }catch (Exception exception){
            newFootballerPosition = "";
        }

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
            checkFootballerName = false;
            messageLabel.setText("Invalid Name!!");
        }

        // now I will check footballer cost if footballer name is valid
        if(checkFootballerName){

            if(newFootballerTeam.equals("")){
                messageLabel.setText("Choose Team First!!");
            }else if(newFootballerPosition.equals("")){
                messageLabel.setText("Choose Position First!!");
            }else {
                if (Validation.footballerPriceValidation(newFootballerCost)) {
                    float price = Float.parseFloat(newFootballerCost);

                    /*
                    Rules for footballers prices:
                    Goalkeeper: £4.0k to £6.0k
                    Defender: £4.5k to £7.5k
                    Midfielder: £5.0k to £12.0k
                    Forward: £6.0k to £14.0k
                     */
                    Pair<String, Boolean> pair = Validation.footballerPricePositionValidation(newFootballerPosition, price);
                    if (pair.getValue()) {

                        // add footballer in main Footballer hashtable
                        Footballer.addNewFootballer(newFootballerName, newFootballerTeam, newFootballerPosition, price);

                        // add footballer to its team list
                        Team.putFootballerInTeams(newFootballerTeam , newFootballerName);

                        // add footballer to its position's list
                        Footballer.addNewFootballerToPositionList(newFootballerPosition, newFootballerName);
                        messageLabel.setText("Done");
                    } else {
                        messageLabel.setText(pair.getKey());
                    }

                } else {
                    messageLabel.setText("Invalid Price!!");
                }
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
