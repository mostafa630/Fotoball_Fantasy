package com.example.fantasy;

import ClassesAndDatabaseconnection.Footballer;
import ClassesAndDatabaseconnection.Player;
import ClassesAndDatabaseconnection.Season;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable{

    @FXML
    Pane seasonPane;
    @FXML
    Label seasonLabel;

    @FXML
    Pane adminPane;
    @FXML
    Label roundLabel;
    @FXML
    Label messageLabel;

    @FXML
    Button startButton;

    @FXML
    Button endButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roundLabel.setText("We are in round number " + Season.getCurrentRound());

        if(Season.getCurrentRound() == 0){
            endButton.setVisible(false);
            startButton.setVisible(true);

        }

        if(Season.isCanModify()){
            endButton.setVisible(false);
            startButton.setVisible(true);

        }

        if(!Season.isCanModify()){
            startButton.setVisible(false);
            endButton.setVisible(true);
        }
    }

    public void startRound(){
        if(Season.getNumOfUpdatedFootballer()!=Footballer.getFootballers().size() && Season.getCurrentRound()>0){
            messageLabel.setText("You should update all footballers' points for this round!!");
        }
        else{

            startButton.setVisible(false);
            endButton.setVisible(true);
            Season.setCurrentRound(Season.getCurrentRound() + 1);
            roundLabel.setText("We are in round number " + Season.getCurrentRound());

            messageLabel.setText("The round number " + Season.getCurrentRound() + " started successfully!!");
            for (Map.Entry<String, Footballer> footballer : Footballer.getFootballers().entrySet()) {
                footballer.getValue().setUpdated(false);
            }

            Season.setNumOfUpdatedFootballer(0);
            Season.setCanModify(false);
        }
    }

    public void endRound()
    {

        if(Season.getNumOfUpdatedFootballer() != Footballer.getFootballers().size()) {
            messageLabel.setText("You should update all footballers' points for this round!!");
        }
        else {
            messageLabel.setText("The round number " + Season.getCurrentRound() + " ended successfully!!");

            endButton.setVisible(false);
            startButton.setVisible(true);

            // update total points and cost of footballers
            for (Map.Entry<String, Footballer> footballer : Footballer.getFootballers().entrySet()) {
                footballer.getValue().updateTotalPoints();
                // update cost of the footballers
                int pointsOfWeek = (int) footballer.getValue().getPointsThisWeek();
                float plusCost =pointsOfWeek/100;
                DecimalFormat df = new DecimalFormat("#.0");
                String formatted = df.format(plusCost);
                plusCost=Float.parseFloat(formatted);
                footballer.getValue().updateCost(plusCost);
            }
            // update users points
            for (Map.Entry<String, Player> player : Player.getPlayers().entrySet()) {
                for(Pair<String ,Boolean> footballer: player.getValue().myTeam)
                {
                    int footballerPointsThisWeek = (int) Footballer.getFootballers().get(footballer.getKey()).getPointsThisWeek();
                    if(footballer.getValue()){
                        player.getValue().updatePoints(footballerPointsThisWeek);
                    }

                }
            }
            // make user and admin can modify
            Season.setCanModify(true);
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
             if(!Season.isCanModify()) {
                 adminPane.setVisible(false);
                 seasonPane.setVisible(true);
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
                adminPane.setVisible(false);
                seasonPane.setVisible(true);
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
                adminPane.setVisible(false);
                seasonPane.setVisible(true);
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
                adminPane.setVisible(false);
                seasonPane.setVisible(true);
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


    public void okMessage(){
        seasonPane.setVisible(false);
        roundLabel.setText("We are in round number " + Season.getCurrentRound());
        adminPane.setVisible(true);

    }
}
