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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class UpdateFootballerDataController implements Initializable {

    // For Choose Footballer Pane
    @FXML
    Pane chooseFootballerPane;
    @FXML
    ComboBox<String> chooseTeamComboBox;
    @FXML
    ComboBox<String> chooseFootballerComboBox;
    @FXML
    Label chooseMessageLabel;

    //

    // For Update Footballer Pane
    @FXML
    Pane updateFootballerPane;
    @FXML
    Label chosenFootballerLabel;
    @FXML
    ComboBox<String> updateFootballerTeamComboBox;
    @FXML
    ComboBox<String> updateFootballerPositionComboBox;
    @FXML
    TextField updateFootballerPrice;
    @FXML
    TextField updateFootballerPoints;
    @FXML
    Label updateMessageLabel;
    //

    //
    String selectedTeam;
    String selectedFootballer;
    //

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // ComboPox For Choose Pane.

        // Add teams to the chooseTeamComboBox
        ObservableList<String> teamsList = FXCollections.observableArrayList();
        // now we will fill the teams list with teams' name from:
        // 1- teams HashTable. or
        // 2- teamsLeagueHashtable.
        // I will loop in teamsLeagueHashtable as it is faster

        for(Map.Entry<String , String> teamLeague : Team.getTeamsLeagueHashtable().entrySet()){
            teamsList.add(teamLeague.getKey());
        }
        chooseTeamComboBox.setItems(teamsList);

        // Listen for changes to the selected item of the chooseTeamComboBox
        chooseTeamComboBox.setOnAction(event -> {
            // Get the selected team
            selectedTeam = chooseTeamComboBox.getSelectionModel().getSelectedItem();

            // Update the chooseFootballerComboBox based on the selected team
            if (selectedTeam != null) {
                // Clear the items in the chooseFootballerComboBox
                chooseFootballerComboBox.getItems().clear();

                // Add the footballers in the selected team to the chooseFootballerComboBox
                ObservableList<String> footballersList = FXCollections.observableArrayList();
                // now we will fill the footballers list with teams' name from:
                // teams HashTable:
                // I now have the selected team, and it is the key of teams HashTable,
                // so I will copy the footballers list of the current key in the chooseFootballerComboBox.
                for(String footballer : Team.getTeams().get(selectedTeam)){
                    footballersList.add(footballer);
                }
                chooseFootballerComboBox.setItems(footballersList);
            }
        });
        //

        // ComboBox For Update Pane.

        // Add teams to the updateFootballerTeamComboBox
        ObservableList<String> updateTeamsList = FXCollections.observableArrayList();
        // now we will fill the teams list with teams' name from:
        // 1- teams HashTable. or
        // 2- teamsLeagueHashtable.
        // I will loop in teamsLeagueHashtable as it is faster

        for(Map.Entry<String , String> teamLeague : Team.getTeamsLeagueHashtable().entrySet()){
            updateTeamsList.add(teamLeague.getKey());
        }
        updateFootballerTeamComboBox.setItems(updateTeamsList);


        // fill updateFootballerPositionComboBox
        ObservableList<String> positionsList = FXCollections.observableArrayList("Defender","Midfielder","Goalkeeper","Forward");
        updateFootballerPositionComboBox.setItems(positionsList);


        //

    }


    public void chooseFootballer() {

        selectedFootballer = chooseFootballerComboBox.getSelectionModel().getSelectedItem();
        if(selectedFootballer != null){
            //  Set Visible of The chooseFootballerPane to false
            chooseFootballerPane.setVisible(false);
            //  Set Visible of The updateFootballerPane to true
            updateFootballerPane.setVisible(true);

            // set the chosenFootballerLabel to the footballer name
            chosenFootballerLabel.setText(selectedFootballer);

            //  set the current choice of this comboBox the footballer Team.
            updateFootballerTeamComboBox.getSelectionModel().select(selectedTeam);

            //  set the current choice of this comboBox the footballer position.
            updateFootballerPositionComboBox.getSelectionModel().select(Footballer.getFootballers().get(selectedFootballer).getPosition());

            // set the updateFootballerPrice to the footballer's price
            updateFootballerPrice.setText(Float.toString(Footballer.getFootballers().get(selectedFootballer).getCost()));


            // set the updateFootballerPoints to the footballer's points
            int footballerPoints = Math.round(Footballer.getFootballers().get(selectedFootballer).getPointsThisWeek());
            updateFootballerPoints.setText(Integer.toString(footballerPoints));

        }else{
            chooseMessageLabel.setText("Choose Footballer First!!");
        }
    }

    public void cancelUpdateProcess() {
        //  Set Visible of The updateFootballerPane to false
        updateFootballerPane.setVisible(false);
        //  Set Visible of The chooseFootballerPane to true
        chooseFootballerPane.setVisible(true);

    }

    public void updateFootballerData(){

        // the team does not make problems
        String updatedFootballerTeam = updateFootballerTeamComboBox.getSelectionModel().getSelectedItem();

        // the position does not make problems
        String updatedFootballerPosition = updateFootballerPositionComboBox.getSelectionModel().getSelectedItem();

        // the price and points should be validated first


        String updatedFootballerPrice = updateFootballerPrice.getText();

        String updatedFootballerPoints = updateFootballerPoints.getText();

        //

        if (Validation.footballerPriceValidation(updatedFootballerPrice)) {
            float price = Float.parseFloat(updatedFootballerPrice);
            /*
            Rules for footballers prices:
            Goalkeeper: £4.0k to £6.0k
            Defender: £4.5k to £7.5k
            Midfielder: £5.0k to £12.0k
            Forward: £6.0k to £14.0k
             */
            Pair<String, Boolean> pair = Validation.footballerPricePositionValidation(updatedFootballerPosition, price);
            // if footballer price is valid
            if (pair.getValue()) {

                // check the footballer points
                if(Validation.footballerPointsValidation(updatedFootballerPoints)){
                    float footballerPoints = Float.parseFloat(updatedFootballerPoints);
                    if(footballerPoints <= 50.0){
                        // here we will put update function
                        updateMessageLabel.setText("Done!!");

                    }else{
                        updateMessageLabel.setText("Check Points Again!!");
                    }
                }else{
                    updateMessageLabel.setText("Invalid Input For points!!");
                }
            } else {
                updateMessageLabel.setText(pair.getKey());
            }

        } else {
            updateMessageLabel.setText("Invalid Price!!");
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
