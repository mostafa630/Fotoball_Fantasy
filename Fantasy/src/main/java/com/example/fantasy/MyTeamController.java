package com.example.fantasy;

import ClassesAndDatabaseconnection.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyTeamController implements Initializable  {
    @FXML
    CheckBox checkBox_goalKeeper1 ;
    @FXML
    CheckBox checkBox_goalKeeper2 ;
    @FXML
    CheckBox checkBox_defender1 ;
    @FXML
    CheckBox checkBox_defender2 ;
    @FXML
    CheckBox checkBox_defender3 ;
    @FXML
    CheckBox checkBox_defender4 ;
    @FXML
    CheckBox checkBox_defender5 ;
    @FXML
    CheckBox checkBox_midfilder1 ;
    @FXML
    CheckBox checkBox_midfilder2 ;
    @FXML
    CheckBox checkBox_midfilder3 ;
    @FXML
    CheckBox checkBox_midfilder4 ;
    @FXML
    CheckBox checkBox_midfilder5 ;
    @FXML
    CheckBox checkBox_forward1 ;
    @FXML
    CheckBox checkBox_forward2 ;
    @FXML
    CheckBox checkBox_forward3 ;
    @FXML
    Label planDone_label;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        int i=0;
        String currentPlayer =LoginController.getCurrenPlayer();
        for(Pair<String ,Boolean>footballer : Player.getPlayers().get(currentPlayer).myTeam)
        {
            // check goal keeper 1
            if(i==0)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_goalKeeper1.setText("no player");
                    checkBox_goalKeeper1.setSelected(false);
                }
                else
                {
                    checkBox_goalKeeper1.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_goalKeeper1.setSelected(true);
                    else
                        checkBox_goalKeeper1.setSelected(false);
                }

            }
            // check goal keeper 2
            if(i==1)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_goalKeeper2.setText("no player");
                    checkBox_goalKeeper2.setSelected(false);
                }
                else
                {
                    checkBox_goalKeeper2.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_goalKeeper2.setSelected(true);
                    else
                        checkBox_goalKeeper2.setSelected(false);
                }

            }


            if(i==2)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_defender1.setText("no player");
                    checkBox_defender1.setSelected(false);
                }
                else
                {
                    checkBox_defender1.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_defender1.setSelected(true);
                    else
                        checkBox_defender1.setSelected(false);
                }

            }
            if(i==3)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_defender2.setText("no player");
                    checkBox_defender2.setSelected(false);
                }
                else
                {
                    checkBox_defender2.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_defender2.setSelected(true);
                    else
                        checkBox_defender2.setSelected(false);
                }

            }

            if(i==4)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_defender3.setText("no player");
                    checkBox_defender3.setSelected(false);
                }
                else
                {
                    checkBox_defender3.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_defender3.setSelected(true);
                    else
                        checkBox_defender3.setSelected(false);
                }

            }

            if(i==5)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_defender4.setText("no player");
                    checkBox_defender4.setSelected(false);
                }
                else
                {
                    checkBox_defender4.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_defender4.setSelected(true);
                    else
                        checkBox_defender4.setSelected(false);
                }

            }

            if(i==6)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_defender5.setText("no player");
                    checkBox_defender5.setSelected(false);
                }
                else
                {
                    checkBox_defender5.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_defender5.setSelected(true);
                    else
                        checkBox_defender5.setSelected(false);
                }

            }


            ///
            if(i==7)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_midfilder1.setText("no player");
                    checkBox_midfilder1.setSelected(false);
                }
                else
                {
                    checkBox_midfilder1.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_midfilder1.setSelected(true);
                    else
                        checkBox_midfilder1.setSelected(false);
                }

            }
            if(i==8)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_midfilder2.setText("no player");
                    checkBox_midfilder2.setSelected(false);
                }
                else
                {
                    checkBox_midfilder2.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_midfilder2.setSelected(true);
                    else
                        checkBox_midfilder2.setSelected(false);
                }

            }

            if(i==9)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_midfilder3.setText("no player");
                    checkBox_midfilder3.setSelected(false);
                }
                else
                {
                    checkBox_midfilder3.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_midfilder3.setSelected(true);
                    else
                        checkBox_midfilder3.setSelected(false);
                }

            }

            if(i==10)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_midfilder4.setText("no player");
                    checkBox_midfilder4.setSelected(false);
                }
                else
                {
                    checkBox_midfilder4.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_midfilder4.setSelected(true);
                    else
                        checkBox_midfilder4.setSelected(false);
                }

            }

            if(i==11)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_midfilder5.setText("no player");
                    checkBox_midfilder5.setSelected(false);
                }
                else
                {
                    checkBox_midfilder5.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_midfilder5.setSelected(true);
                    else
                        checkBox_midfilder5.setSelected(false);
                }

            }

            ///

            if(i==12)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_forward1.setText("no player");
                    checkBox_forward1.setSelected(false);
                }
                else
                {
                    checkBox_forward1.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_forward1.setSelected(true);
                    else
                        checkBox_forward1.setSelected(false);
                }

            }

            if(i==13)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_forward2.setText("no player");
                    checkBox_forward2.setSelected(false);
                }
                else
                {
                    checkBox_forward2.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_forward2.setSelected(true);
                    else
                        checkBox_forward2.setSelected(false);
                }

            }

            if(i==14)
            {
                if(footballer.getKey().equals("null")) {
                    checkBox_forward3.setText("no player");
                    checkBox_forward3.setSelected(false);
                }
                else
                {
                    checkBox_forward3.setText(footballer.getKey());
                    if(footballer.getValue()==true)
                        checkBox_forward3.setSelected(true);
                    else
                        checkBox_forward3.setSelected(false);
                }

            }
            i++;
        }
    }
    public boolean checkPlan()
    {
        int noPlayerCounter=0 ,numOfGoalkeepers=0,numOfDefenders=0,numOfMidfilders=0 ,numOfForwards=0 ,playerCounter=0;
        if(checkBox_goalKeeper1.isSelected()==true)
        {
            if(checkBox_goalKeeper1.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfGoalkeepers++;
            }
        }

        if(checkBox_goalKeeper2.isSelected()==true)
        {
            if(checkBox_goalKeeper2.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfGoalkeepers++;
            }
        }
        if(checkBox_defender1.isSelected()==true)
        {
            if(checkBox_defender1.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfDefenders++;
            }
        }
        if(checkBox_defender2.isSelected()==true)
        {
            if(checkBox_defender2.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfDefenders++;
            }
        }

        if(checkBox_defender3.isSelected()==true)
        {
            if(checkBox_defender3.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfDefenders++;
            }
        }

        if(checkBox_defender4.isSelected()==true)
        {
            if(checkBox_defender4.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfDefenders++;
            }
        }

        if(checkBox_defender5.isSelected()==true)
        {
            if(checkBox_defender5.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfDefenders++;
            }
        }

        if(checkBox_midfilder1.isSelected()==true)
        {
            if(checkBox_midfilder1.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfMidfilders++;
            }
        }

        if(checkBox_midfilder2.isSelected()==true)
        {
            if(checkBox_midfilder2.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfMidfilders++;
            }
        }

        if(checkBox_midfilder3.isSelected()==true)
        {
            if(checkBox_midfilder3.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfMidfilders++;
            }
        }

        if(checkBox_midfilder4.isSelected()==true)
        {
            if(checkBox_midfilder4.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfMidfilders++;
            }
        }

        if(checkBox_midfilder5.isSelected()==true)
        {
            if(checkBox_midfilder5.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfMidfilders++;
            }
        }

        if(checkBox_forward1.isSelected()==true)
        {
            if(checkBox_forward1.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfForwards++;
            }
        }
        if(checkBox_forward2.isSelected()==true)
        {
            if(checkBox_forward2.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfForwards++;
            }
        }

        if(checkBox_forward3.isSelected()==true)
        {
            if(checkBox_forward3.getText().equals("no player"))
                noPlayerCounter++;
            else
            {
                playerCounter++;
                numOfForwards++;
            }
        }

       if(noPlayerCounter>0 || playerCounter!=11 || numOfGoalkeepers>1 ||numOfDefenders<3||numOfDefenders>4||numOfMidfilders<3||numOfMidfilders>4||numOfForwards<2)
           return false ;
       else
           return  true;
    }

    public  void setPlan() {
        if (checkPlan()) {
            String currentPlayer = LoginController.getCurrenPlayer();
            if (!checkBox_goalKeeper1.getText().equals("no player")) {
                String footballerName = checkBox_goalKeeper1.getText();
                if (checkBox_goalKeeper1.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(0, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(0, footballerName, false);
                }

            }

            //
            if (!checkBox_goalKeeper2.getText().equals("no player")) {
                String footballerName = checkBox_goalKeeper2.getText();
                if (checkBox_goalKeeper2.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(1, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(1, footballerName, false);
                }

            }

            if (!checkBox_defender1.getText().equals("no player")) {
                String footballerName = checkBox_defender1.getText();
                if (checkBox_defender1.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(2, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(2, footballerName, false);
                }

            }

            if (!checkBox_defender2.getText().equals("no player")) {
                String footballerName = checkBox_defender2.getText();
                if (checkBox_defender2.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(3, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(3, footballerName, false);
                }

            }

            if (!checkBox_defender3.getText().equals("no player")) {
                String footballerName = checkBox_defender3.getText();
                if (checkBox_defender3.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(4, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(4, footballerName, false);
                }

            }

            if (!checkBox_defender4.getText().equals("no player")) {
                String footballerName = checkBox_defender4.getText();
                if (checkBox_defender4.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(5, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(5, footballerName, false);
                }

            }

            if (!checkBox_defender5.getText().equals("no player")) {
                String footballerName = checkBox_defender5.getText();
                if (checkBox_defender5.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(6, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(6, footballerName, false);
                }

            }

            //////
            if (!checkBox_midfilder1.getText().equals("no player")) {
                String footballerName = checkBox_midfilder1.getText();
                if (checkBox_midfilder1.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(7, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(7, footballerName, false);
                }

            }

            if (!checkBox_midfilder2.getText().equals("no player")) {
                String footballerName = checkBox_midfilder2.getText();
                if (checkBox_midfilder2.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(8, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(8, footballerName, false);
                }

            }

            if (!checkBox_midfilder3.getText().equals("no player")) {
                String footballerName = checkBox_midfilder3.getText();
                if (checkBox_midfilder3.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(9, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(9, footballerName, false);
                }

            }

            if (!checkBox_midfilder4.getText().equals("no player")) {
                String footballerName = checkBox_midfilder4.getText();
                if (checkBox_midfilder4.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(10, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(10, footballerName, false);
                }

            }

            if (!checkBox_midfilder5.getText().equals("no player")) {
                String footballerName = checkBox_midfilder5.getText();
                if (checkBox_midfilder5.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(11, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(11, footballerName, false);
                }

            }

            if (!checkBox_forward1.getText().equals("no player")) {
                String footballerName = checkBox_forward1.getText();
                if (checkBox_forward1.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(12, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(12, footballerName, false);
                }

            }

            if (!checkBox_forward2.getText().equals("no player")) {
                String footballerName = checkBox_forward2.getText();
                if (checkBox_forward2.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(13, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(13, footballerName, false);
                }

            }

            if (!checkBox_forward3.getText().equals("no player")) {
                String footballerName = checkBox_forward3.getText();
                if (checkBox_forward3.isSelected() == true) {

                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(14, footballerName, true);
                } else {
                    Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(14, footballerName, false);
                }

            }
               planDone_label.setText("Plan updated sucssefuly");
        }
        else
        {

            try {

                // open register form
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ruleOfPlan.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 427, 282);
                stage.setTitle("Fantasy");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            } catch (Exception ex) {
                System.out.println("open rule of plan failed");
            }
            planDone_label.setText("");

        }
        }
        public void goToMainPage (ActionEvent event)throws IOException
        {
            try {
                // open register form
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
                stage.setTitle("Fantasy");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            } catch (Exception ex) {
                System.out.println("going back to main page failed");
            }

        }

}
