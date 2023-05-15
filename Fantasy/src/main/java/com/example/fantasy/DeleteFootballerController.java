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

public class DeleteFootballerController implements Initializable {
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
    Pane deleteFootballerPane;
    @FXML
    Label chosenFootballerLabel;

    @FXML
    Label teamLabel;
    @FXML
    Label leagueLabel;
    @FXML
    Label positionLabel;
    @FXML
    Label priceLabel;
    @FXML
    Label pointsLabel;

    @FXML
    Label deleteMessageLabel;
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
    }


    public void chooseFootballer() {

        selectedFootballer = chooseFootballerComboBox.getSelectionModel().getSelectedItem();
        try{
            if(selectedFootballer != null){

                // set the chosenFootballerLabel to the footballer name
                chosenFootballerLabel.setText(selectedFootballer);

                //  set the team label to  the footballer Team.
                teamLabel.setText(selectedTeam);

                //  set the league label to  the Team league.
                leagueLabel.setText(Team.getTeamsLeagueHashtable().get(selectedTeam));

                //  set the position label to  the footballer position.
                positionLabel.setText(Footballer.getFootballers().get(selectedFootballer).getPosition());


                //  set the price label to  the footballer price.
                float price = Footballer.getFootballers().get(selectedFootballer).getCost();
                priceLabel.setText(Float.toString(price));

                //  set the points label to  the footballer total points.
                float points = Footballer.getFootballers().get(selectedFootballer).getTotalPoints();
                pointsLabel.setText(Float.toString(points));
                deleteMessageLabel.setText("");

                //  Set Visible of The chooseFootballerPane to false
                chooseFootballerPane.setVisible(false);
                //  Set Visible of The updateFootballerPane to true
                deleteFootballerPane.setVisible(true);


            }else{
                chooseMessageLabel.setText("Choose Footballer First!!");
            }
        }catch (Exception exception){
            chooseMessageLabel.setText("This Player is already Deleted");
        }
    }

    public void cancelDeleteProcess() {
        //  Set Visible of The updateFootballerPane to false
        deleteFootballerPane.setVisible(false);
        //  Set Visible of The chooseFootballerPane to true
        chooseFootballerPane.setVisible(true);

        chooseMessageLabel.setText("");

    }

    public void deleteFootballer(){

        try{
            Footballer.deleteFootballer(selectedFootballer , selectedTeam);
            deleteMessageLabel.setText("Done");
        }catch (Exception exception){
            deleteMessageLabel.setText("This Player is already Deleted");
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
}
