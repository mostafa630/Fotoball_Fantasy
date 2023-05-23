package com.example.fantasy;

import ClassesAndDatabaseconnection.Footballer;
import ClassesAndDatabaseconnection.Season;
import ClassesAndDatabaseconnection.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AddPointsOfTheWeekController implements Initializable {

    @FXML
    Pane seasonPane;
    @FXML
    Label seasonLabel;

    // For Choose Footballer Pane
    @FXML
    Pane chooseFootballerPane;
    @FXML
    ComboBox<String> chooseTeamComboBox;
    @FXML
    ComboBox<String> chooseFootballerComboBox;
    @FXML
    Label chooseMessageLabel;

    // For Add Points Plane
    @FXML
    Pane addPointsPane;
    @FXML
    Label nameLabel;
    @FXML
    Label teamLabel;
    @FXML
    Label leagueLabel;
    @FXML
    Label positionLabel;
    @FXML
    ComboBox<Integer> goalsComboBox;
    @FXML
    ComboBox<Integer> assistsComboBox;
    @FXML
    CheckBox yellowCardCheckBox;
    @FXML
    CheckBox redCardCheckBox;
    @FXML
    ComboBox<Integer> penaltyMissesComboBox;
    @FXML
    ComboBox<Integer> ownGoalsComboBox;
    @FXML
    CheckBox moreThan60MinCheckBox;

    @FXML
    CheckBox cleanSheetCheckBox;

    @FXML
    ComboBox<Integer> penaltySavesComboBox;


    //
    @FXML
    Label checkUpdatedLabel;
    //

    //

    // message
    @FXML
    Pane messagePane;
    @FXML
    Label checkMessageLabel;
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

        yellowCardCheckBox.setOnAction(actionEvent -> {
            if(redCardCheckBox.isSelected()){
                redCardCheckBox.setSelected(false);
            }
        });

        redCardCheckBox.setOnAction(actionEvent -> {
            if(yellowCardCheckBox.isSelected()){
                yellowCardCheckBox.setSelected(false);
            }
        });

        //

        // comboBox for add points pane
        ObservableList<Integer> list =FXCollections.observableArrayList(0 , 1 , 2 , 3 , 4 , 5);

        goalsComboBox.setItems(list);
        assistsComboBox.setItems(list);
        penaltyMissesComboBox.setItems(list);
        ownGoalsComboBox.setItems(list);
        penaltySavesComboBox.setItems(list);


    }




    public void chooseFootballer() {

        selectedFootballer = chooseFootballerComboBox.getSelectionModel().getSelectedItem();
        if(selectedFootballer != null){

            // set Player Information
            nameLabel.setText(selectedFootballer);
            teamLabel.setText(selectedTeam);
            leagueLabel.setText(Team.getTeamsLeagueHashtable().get(selectedTeam));
            positionLabel.setText(Footballer.getFootballers().get(selectedFootballer).getPosition());

            // Set initial value of comboBoxes
            goalsComboBox.getSelectionModel().select(0);
            assistsComboBox.getSelectionModel().select(0);
            penaltyMissesComboBox.getSelectionModel().select(0);
            ownGoalsComboBox.getSelectionModel().select(0);
            penaltySavesComboBox.getSelectionModel().select(0);


            //
            redCardCheckBox.setSelected(false);
            yellowCardCheckBox.setSelected(false);
            moreThan60MinCheckBox.setSelected(false);
            cleanSheetCheckBox.setSelected(false);

            //

            //  Set Visible of The chooseFootballerPane to false
            chooseFootballerPane.setVisible(false);
            //  Set Visible of The addPointsPane to false
            addPointsPane.setVisible(true);
            //  Set Visible of The messagePane to false
            messagePane.setVisible(false);

            //
            if(Footballer.getFootballers().get(selectedFootballer).isUpdated()){
                checkUpdatedLabel.setText(selectedFootballer + " is updated before with " + (int)Footballer.getFootballers().get(selectedFootballer).getPointsThisWeek() +" points.");
            }else {
                checkUpdatedLabel.setText("");
            }
            //

        }else{
            chooseMessageLabel.setText("Choose Footballer First!!");
        }
    }
    //
    int points;
    public void calculatePoints(){

        int goals = goalsComboBox.getSelectionModel().getSelectedItem();
        int assists = assistsComboBox.getSelectionModel().getSelectedItem();
        boolean yellowCard = yellowCardCheckBox.isSelected();
        boolean redCard = redCardCheckBox.isSelected();
        int penaltyMisses = penaltyMissesComboBox.getSelectionModel().getSelectedItem();
        int ownGoals = ownGoalsComboBox.getSelectionModel().getSelectedItem();
        boolean playedMoreThan60Min = moreThan60MinCheckBox.isSelected();
        boolean cleanSheet = cleanSheetCheckBox.isSelected();
        int penaltySaves = penaltySavesComboBox.getSelectionModel().getSelectedItem();
        String position = positionLabel.getText();
        points = Footballer.addPointsToFootballers(selectedFootballer ,position,
                                                    goals, assists,
                                                    yellowCard,redCard,
                                                    playedMoreThan60Min,
                                                    penaltyMisses,ownGoals,
                                                    cleanSheet,
                                                    penaltySaves);

        String message = "The total points for " + selectedFootballer +  " for This week is " + points;
        checkMessageLabel.setText(message);
        messagePane.setVisible(true);

    }

    public void addPoints(){

        Footballer.getFootballers().get(selectedFootballer).setPointsThisWeek(points);

        if(!Footballer.getFootballers().get(selectedFootballer).isUpdated()){
            Footballer.getFootballers().get(selectedFootballer).setUpdated(true);
            Season.setNumOfUpdatedFootballer(Season.getNumOfUpdatedFootballer() + 1);
        }
        //  Set Visible of The addPointsPane to false
        addPointsPane.setVisible(false);

        //  Set Visible of The messagePane to false
        messagePane.setVisible(false);
        //  Set Visible of The chooseFootballerPane to false
        chooseFootballerPane.setVisible(true);
    }

    public void cancelAdd(){

        //  Set Visible of The addPointsPane to false
        addPointsPane.setVisible(false);

        //  Set Visible of The messagePane to false
        messagePane.setVisible(false);
        //  Set Visible of The chooseFootballerPane to false
        chooseFootballerPane.setVisible(true);
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
    // this function to open addNewTeam page if we pressed Add New Team button
    public void openAddNewTeamPage(ActionEvent event) throws IOException{

        try{
            if(!Season.isCanModify()) {
                seasonPane.setVisible(true);
                addPointsPane.setVisible(false);
                messagePane.setVisible(false);
                chooseFootballerPane.setVisible(false);
                seasonLabel.setText("Please refrain from adding new teams");
            }else{
                // open Add New Team page
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddNewTeam.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(),1108,563);
                stage.setTitle("Fantasy");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            }

        }catch (Exception ex){
            System.out.println("Going to Add New Team page failed");
        }
    }

    @FXML
    // this function to open Add New Footballer Page if we pressed Add New Footballer button
    public void openAddNewFootballerPage(ActionEvent event) throws IOException {
        try{
            if(!Season.isCanModify()){
                seasonPane.setVisible(true);
                addPointsPane.setVisible(false);
                messagePane.setVisible(false);
                chooseFootballerPane.setVisible(false);

                seasonLabel.setText("Please refrain from adding new footballers");
            }else{
                // open Add New Team page
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddNewFootballer.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(),1108,563);
                stage.setTitle("Fantasy");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            }
        }catch (Exception ex){
            System.out.println("Going to Add New Footballer page failed");
        }
    }

    @FXML
    // this function to open Update Footballer Data Page if we pressed Update Footballer Data button
    public void openUpdateFootballerDataPage(ActionEvent event) throws IOException {
        try{
            if(!Season.isCanModify()){
                seasonPane.setVisible(true);
                addPointsPane.setVisible(false);
                messagePane.setVisible(false);
                chooseFootballerPane.setVisible(false);

                seasonLabel.setText("Please refrain from updating footballer's data");
            }else{
                // open Add New Team page
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("updateFootballerData.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(),1108,563);
                stage.setTitle("Fantasy");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            }

        }catch (Exception ex){
            System.out.println("Going to Update Footballer page failed");
        }
    }

    @FXML
    // this function to open Delete Footballer Page if we pressed Delete Footballer button
    public void openDeleteFootballerPage(ActionEvent event) throws IOException {
        try{
            if(!Season.isCanModify()){
                seasonPane.setVisible(true);
                addPointsPane.setVisible(false);
                messagePane.setVisible(false);
                chooseFootballerPane.setVisible(false);

                seasonLabel.setText("Please refrain from deleting footballers");
            }else{
                // open Add New Team page
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DeleteFootballer.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(),1108,563);
                stage.setTitle("Fantasy");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            }

        }catch (Exception ex){
            System.out.println("Going to Delete Footballer page failed");
        }
    }

    public void okMessage(){
        seasonPane.setVisible(false);
        addPointsPane.setVisible(false);
        messagePane.setVisible(false);
        chooseFootballerPane.setVisible(true);
    }
}
