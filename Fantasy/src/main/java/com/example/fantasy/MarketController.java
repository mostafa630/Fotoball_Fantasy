package com.example.fantasy;

import ClassesAndDatabaseconnection.Footballer;
import ClassesAndDatabaseconnection.Player;
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
import javafx.scene.control.skin.CellSkinBase;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    @FXML
    ComboBox<String> goolKeepersCombobox;
    @FXML
    ComboBox<String> defendersCombobox;
    @FXML
    ComboBox<String> midfildersCombobox;
    @FXML
    ComboBox<String> forwardsCombobox;
   @FXML
    ComboBox<String> sellCombobox;
/*
   label for gool keepers
 */
    @FXML
    Label goolkeeper1;
    @FXML
    Label goolkeeper2;
    /*
     labels for defenders
     */
    @FXML
    Label defender1;
    @FXML
    Label defender2;
    @FXML
    Label defender3;
    @FXML
    Label defender4;
    @FXML
    Label defender5;
    /*
     labels for midfilders;
     */
    @FXML
    Label midfilder1;
    @FXML
    Label midfilder2;
    @FXML
    Label midfilder3;
    @FXML
    Label midfilder4;
    @FXML
    Label midfilder5;
    /*
       label for forwards
     */
    @FXML
    Label forward1;
    @FXML
    Label forward2;
    @FXML
    Label forward3;
  @FXML
  Label budget_label;
    @FXML

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // fill goolkeepers combobox
        ObservableList<String> list = FXCollections.observableArrayList();
        for (String goolKeeper : Footballer.goolKeepers)
            list.add(goolKeeper);
        goolKeepersCombobox.setItems(list);
        // fill defender combobox
        ObservableList<String> list2 = FXCollections.observableArrayList();
        for (String defender : Footballer.defenders)
            list2.add(defender);
        defendersCombobox.setItems(list2);
        // fill midfilders combobox
        ObservableList<String> list3 = FXCollections.observableArrayList();
        for (String defender : Footballer.midfielders)
            list3.add(defender);
        midfildersCombobox.setItems(list3);
        // fill forwards combobox
        ObservableList<String> list4 = FXCollections.observableArrayList();
        for (String forward : Footballer.forwardes)
            list4.add(forward);
        forwardsCombobox.setItems(list4);

        // fill sell combobox
        String currentPlayer =LoginController.getCurrenPlayer();
        ObservableList<String> list5 = FXCollections.observableArrayList();
        for (Pair<String, Boolean> footballer :Player.getPlayers().get(currentPlayer).myTeam )
            if(!footballer.getKey().equals("null"))
                list5.add(footballer.getKey());
        sellCombobox.setItems(list5);

        budget_label.setText("Budget = "+Player.getPlayers().get(currentPlayer).getBudget()+"k"); // update buget every time
        // put my whole team in list in the market
        int i=0;
        for(Pair<String ,Boolean> footballer :Player.getPlayers().get(currentPlayer).myTeam)
        {
            if(!footballer.getKey().equals("null")) {
                if (i == 0)
                    goolkeeper1.setText(footballer.getKey());
                else if (i==1)
                    goolkeeper2.setText(footballer.getKey());
                else if(i==2)
                    defender1.setText(footballer.getKey());
                else if(i==3)
                    defender2.setText(footballer.getKey());
                else if(i==4)
                    defender3.setText(footballer.getKey());
                else if(i==5)
                    defender4.setText(footballer.getKey());
                else if(i==6)
                    defender5.setText(footballer.getKey());
                else if (i==7)
                    midfilder1.setText(footballer.getKey());
                else if (i==8)
                    midfilder2.setText(footballer.getKey());
                else if (i==9)
                    midfilder3.setText(footballer.getKey());
                else if (i==10)
                    midfilder4.setText(footballer.getKey());
                else if (i==11)
                    midfilder5.setText(footballer.getKey());
                else if(i==12)
                    forward1.setText(footballer.getKey());
                else if(i==13)
                    forward2.setText(footballer.getKey());
                else if(i==14)
                    forward3.setText(footballer.getKey());
            }
            i++;
        }

    }


    @FXML
    Label label_of_goolkeeper_club;
    @FXML
    Label label_of_goolkeeper_cost;
    @FXML
    Label label_of_goolkeeper_total_points;
    @FXML
    Label wrong_choice_of_goolkeeper;


    @FXML
    // this function to returnto main page from market form
    public void goToMainPage (ActionEvent event)throws IOException
    {
        try{
            // open register form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        }
        catch (Exception ex)
        {
            System.out.println("going back to main page failed");
        }

    }
    // search for goolkeeper and return his data
    public void searchOfGoolkeeper() {
        try {
            String goolkeeper = goolKeepersCombobox.getSelectionModel().getSelectedItem();
            if (!Footballer.footballers.containsKey(goolkeeper)) {
                wrong_choice_of_goolkeeper.setText("please choose from combobox");
                label_of_goolkeeper_club.setText("");
                label_of_goolkeeper_cost.setText("");
                label_of_goolkeeper_total_points.setText("");

            } else {
                Footballer footballer = Footballer.footballers.get(goolkeeper);
                String club = footballer.getClub();
                float cost = footballer.getCost();
                float totalPoints = footballer.getTotalPoints();
                label_of_goolkeeper_club.setText("Club : " + club);
                label_of_goolkeeper_cost.setText("Cost : " + cost + "k");
                label_of_goolkeeper_total_points.setText("Total Points : " + totalPoints);
                wrong_choice_of_goolkeeper.setText("");
            }
        } catch (Exception ex) {
            wrong_choice_of_goolkeeper.setText("please choose from combobox");
        }
    }


    @FXML
    Label label_of_defender_club;
    @FXML
    Label label_of_defender_cost;
    @FXML
    Label label_of_defender_total_points;
    @FXML
    Label wrong_choice_of_defender;
    // search for defender and return his data
    public void searchOfDefender() {
        try {
            String defender = defendersCombobox.getSelectionModel().getSelectedItem();
            if (!Footballer.footballers.containsKey(defender)) {
                wrong_choice_of_defender.setText("please choose from combobox");
                label_of_defender_club.setText("");
                label_of_defender_cost.setText("");
                label_of_defender_total_points.setText("");

            } else {
                Footballer footballer = Footballer.footballers.get(defender);
                String club = footballer.getClub();
                float cost = footballer.getCost();
                float totalPoints = footballer.getTotalPoints();
                label_of_defender_club.setText("Club : " + club);
                label_of_defender_cost.setText("Cost : " + cost + "k");
                label_of_defender_total_points.setText("Total Points : " + totalPoints);
                wrong_choice_of_defender.setText("");
            }
        } catch (Exception ex) {
            wrong_choice_of_defender.setText("please choose from combobox");
        }
    }


    @FXML
    Label label_of_midfilder_club;
    @FXML
    Label label_of_midfilder_cost;
    @FXML
    Label label_of_midfilder_total_points;
    @FXML
    Label wrong_choice_of_midfilder;

    // search for midfilder and return his data
    public void searchOfMidfilder() {
        try {
            String midfilder = midfildersCombobox.getSelectionModel().getSelectedItem();
            if (!Footballer.footballers.containsKey(midfilder)) {
                wrong_choice_of_midfilder.setText("please choose from combobox");
                label_of_midfilder_club.setText("");
                label_of_midfilder_cost.setText("");
                label_of_midfilder_total_points.setText("");

            } else {
                Footballer footballer = Footballer.footballers.get(midfilder);
                String club = footballer.getClub();
                float cost = footballer.getCost();
                float totalPoints = footballer.getTotalPoints();
                label_of_midfilder_club.setText("Club : " + club);
                label_of_midfilder_cost.setText("Cost : " + cost + "k");
                label_of_midfilder_total_points.setText("Total Points : " + totalPoints);
                wrong_choice_of_midfilder.setText("");
            }
        } catch (Exception ex) {
            wrong_choice_of_midfilder.setText("please choose from combobox");
        }
    }


    @FXML
    Label label_of_forward_club;
    @FXML
    Label label_of_forward_cost;
    @FXML
    Label label_of_forward_total_points;
    @FXML
    Label wrong_choice_of_forward;

    // search for forward and return his data
    public void searchOfForward() {
        try {
            String forward = forwardsCombobox.getSelectionModel().getSelectedItem();
            if (!Footballer.footballers.containsKey(forward)) {
                wrong_choice_of_forward.setText("please choose from combobox");
                label_of_forward_club.setText("");
                label_of_forward_cost.setText("");
                label_of_forward_total_points.setText("");

            } else {
                Footballer footballer = Footballer.footballers.get(forward);
                String club = footballer.getClub();
                float cost = footballer.getCost();
                float totalPoints = footballer.getTotalPoints();
                label_of_forward_club.setText("Club : " + club);
                label_of_forward_cost.setText("Cost : " + cost + "k");
                label_of_forward_total_points.setText("Total Points : " + totalPoints);
                wrong_choice_of_forward.setText("");
            }
        } catch (Exception ex) {
            wrong_choice_of_forward.setText("please choose from combobox");
        }
    }

    @FXML
    Label label_of_sell_club;
    @FXML
    Label label_of_sell_cost;
    @FXML
    Label label_of_sell_total_points;
    @FXML
    Label wrong_choice_of_sell;

    public void sell() {
        try {
            String soldPlayer = sellCombobox.getSelectionModel().getSelectedItem();
            if (!Footballer.footballers.containsKey(soldPlayer)) {
                wrong_choice_of_sell.setText("please choose from combobox");
                label_of_sell_club.setText("");
                label_of_sell_cost.setText("");
                label_of_sell_total_points.setText("");

            } else {
                Footballer footballer = Footballer.footballers.get(soldPlayer);
                String club = footballer.getClub();
                float cost = footballer.getCost();
                float totalPoints = footballer.getTotalPoints();
                label_of_sell_club.setText("Club : " + club);
                label_of_sell_cost.setText("Cost : " + cost + "k");
                label_of_sell_total_points.setText("Total Points : " + totalPoints);
                wrong_choice_of_sell.setText("");
            }
        } catch (Exception ex) {
            wrong_choice_of_sell.setText("please choose from combobox");
        }
    }
    public void buyGoolkeeper()
    {
        String goolkeeper = goolKeepersCombobox.getSelectionModel().getSelectedItem();
        if (!Footballer.footballers.containsKey(goolkeeper)) {
            wrong_choice_of_goolkeeper.setText("please choose from combobox");
        }
        else {
            String currentPlayer = LoginController.getCurrenPlayer();
            Footballer footballer = Footballer.getFootballers().get(goolkeeper);
            Player player = Player.getPlayers().get(currentPlayer);
            float cost = footballer.getCost();
            if (player.getBudget() < cost)
                wrong_choice_of_goolkeeper.setText("check your budget");
            else
            {
                if(goolkeeper1.getText().equals("")) {
                    if(goolkeeper2.getText().equals(goolkeeper))
                        wrong_choice_of_goolkeeper.setText("this player is in your team");
                    else {
                        wrong_choice_of_goolkeeper.setText("");
                        sellCombobox.getItems().add(goolkeeper);
                        goolkeeper1.setText(goolkeeper);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(0, goolkeeper, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else if (goolkeeper2.getText().equals("")) {
                    if(goolkeeper1.getText().equals(goolkeeper))
                        wrong_choice_of_goolkeeper.setText("this player is in your team");
                    else {
                        wrong_choice_of_goolkeeper.setText("");
                        sellCombobox.getItems().add(goolkeeper);
                        goolkeeper2.setText(goolkeeper);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(1, goolkeeper, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else
                    wrong_choice_of_goolkeeper.setText("sell a goolkeeper first");
            }
        }
    }

    public void buyDefender()
    {
        String defender = defendersCombobox.getSelectionModel().getSelectedItem();
        if (!Footballer.footballers.containsKey(defender)) {
            wrong_choice_of_defender.setText("please choose from combobox");
        }
        else {
            String currentPlayer = LoginController.getCurrenPlayer();
            Footballer footballer = Footballer.getFootballers().get(defender);
            Player player = Player.getPlayers().get(currentPlayer);
            float cost = footballer.getCost();
            if (player.getBudget() < cost)
                wrong_choice_of_defender.setText("check your budget");
            else
            {
                if(defender1.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,false)))
                        wrong_choice_of_defender.setText("this player is in your team");
                    else {
                        wrong_choice_of_defender.setText("");
                        sellCombobox.getItems().add(defender); ///
                        defender1.setText(defender);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(2, defender, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else if (defender2.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,false)))
                        wrong_choice_of_defender.setText("this player is in your team");
                    else {
                        wrong_choice_of_defender.setText("");
                        sellCombobox.getItems().add(defender); ///
                        defender2.setText(defender);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(3, defender, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }

                else if (defender3.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,false)))
                        wrong_choice_of_defender.setText("this player is in your team");
                    else {
                        wrong_choice_of_defender.setText("");
                        sellCombobox.getItems().add(defender); ///
                        defender3.setText(defender);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(4, defender, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }

                else if (defender4.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,false)))
                        wrong_choice_of_defender.setText("this player is in your team");
                    else {
                        wrong_choice_of_defender.setText("");
                        sellCombobox.getItems().add(defender); ///
                        defender4.setText(defender);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(5, defender, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else if (defender5.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(defender,false)))
                        wrong_choice_of_defender.setText("this player is in your team");
                    else {
                        wrong_choice_of_defender.setText("");
                        sellCombobox.getItems().add(defender); ///
                        defender5.setText(defender);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(6, defender, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else
                    wrong_choice_of_defender.setText("sell a defender first");
            }
        }
    }



    public void buyMidfilder()
    {
        String midfilder = midfildersCombobox.getSelectionModel().getSelectedItem();
        if (!Footballer.footballers.containsKey(midfilder)) {
            wrong_choice_of_midfilder.setText("please choose from combobox");
        }
        else {
            String currentPlayer = LoginController.getCurrenPlayer();
            Footballer footballer = Footballer.getFootballers().get(midfilder);
            Player player = Player.getPlayers().get(currentPlayer);
            float cost = footballer.getCost();
            if (player.getBudget() < cost)
                wrong_choice_of_midfilder.setText("check your budget");
            else
            {
                if(midfilder1.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,false)))
                        wrong_choice_of_midfilder.setText("this player is in your team");
                    else {
                        wrong_choice_of_midfilder.setText("");
                        sellCombobox.getItems().add(midfilder);
                        midfilder1.setText(midfilder);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(7, midfilder, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else if (midfilder2.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,false)))
                        wrong_choice_of_midfilder.setText("this player is in your team");
                    else {
                        wrong_choice_of_midfilder.setText("");
                        sellCombobox.getItems().add(midfilder);
                        midfilder2.setText(midfilder);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(8, midfilder, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }

                else if (midfilder3.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,false)))
                        wrong_choice_of_midfilder.setText("this player is in your team");
                    else {
                        wrong_choice_of_midfilder.setText("");
                        sellCombobox.getItems().add(midfilder);
                        midfilder3.setText(midfilder);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(9, midfilder, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }

                else if (midfilder4.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,false)))
                        wrong_choice_of_midfilder.setText("this player is in your team");
                    else {
                        wrong_choice_of_midfilder.setText("");
                        sellCombobox.getItems().add(midfilder);
                        midfilder4.setText(midfilder);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(10, midfilder, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else if (midfilder5.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(midfilder,false)))
                        wrong_choice_of_midfilder.setText("this player is in your team");
                    else {
                        wrong_choice_of_midfilder.setText("");
                        sellCombobox.getItems().add(midfilder);
                        midfilder5.setText(midfilder);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(11, midfilder, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else
                    wrong_choice_of_midfilder.setText("sell a defender first");
            }
        }
    }




    public void buyForward()
    {
        String forward = forwardsCombobox.getSelectionModel().getSelectedItem();
        if (!Footballer.footballers.containsKey(forward)) {
            wrong_choice_of_forward.setText("please choose from combobox");
        }
        else {
            String currentPlayer = LoginController.getCurrenPlayer();
            Footballer footballer = Footballer.getFootballers().get(forward);
            Player player = Player.getPlayers().get(currentPlayer);
            float cost = footballer.getCost();
            if (player.getBudget() < cost)
                wrong_choice_of_forward.setText("check your budget");
            else
            {
                if(forward1.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(forward,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(forward,false)))
                        wrong_choice_of_forward.setText("this player is in your team");
                    else {
                        wrong_choice_of_forward.setText("");
                        sellCombobox.getItems().add(forward);
                        forward1.setText(forward);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(12, forward, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else if (forward2.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(forward,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(forward,false)))
                        wrong_choice_of_forward.setText("this player is in your team");
                    else {
                        wrong_choice_of_forward.setText("");
                        sellCombobox.getItems().add(forward);
                        forward2.setText(forward);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(13, forward, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }

                else if (forward3.getText().equals("")) {
                    if(Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(forward,true))||Player.getPlayers().get(currentPlayer).myTeam.contains(new Pair<>(forward,false)))
                        wrong_choice_of_forward.setText("this player is in your team");
                    else {
                        wrong_choice_of_forward.setText("");
                        sellCombobox.getItems().add(forward);
                        forward3.setText(forward);
                        Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(14, forward, false);
                        Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget() - cost);
                        budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
                    }
                }
                else
                    wrong_choice_of_forward.setText("sell a forward first");
            }
        }
    }

    public void sellPlayer()
    {
        String currentPlayer =LoginController.getCurrenPlayer();
        String soldFootballer=sellCombobox.getSelectionModel().getSelectedItem();
        if (!Footballer.footballers.containsKey(soldFootballer)) {
            wrong_choice_of_sell.setText("please choose from combobox");
        }
        else
        {
            int index=0 ;
            for(Pair<String ,Boolean> player : Player.getPlayers().get(currentPlayer).myTeam)
            {
                if(player.getKey().equals(soldFootballer))
                    break;
                else
                    index++;
            }
            /*int index1 = Player.getPlayers().get(currentPlayer).myTeam.indexOf(new Pair<>(soldPlayer,true));
            int index2 = Player.getPlayers().get(currentPlayer).myTeam.indexOf(new Pair<>(soldPlayer,false));
            int index= Math.max(index1,index2);*/
            Player.getPlayers().get(currentPlayer).putFootballerInMyTeam(index ,"null",false);
            float cost =Footballer.getFootballers().get(soldFootballer).getCost();
            Player.getPlayers().get(currentPlayer).setBudget(Player.getPlayers().get(currentPlayer).getBudget()+cost);
            budget_label.setText("Budget = " + Player.getPlayers().get(currentPlayer).getBudget() + "k");
            sellCombobox.getItems().remove(soldFootballer);
            if (index == 0)
                goolkeeper1.setText("");
            else if (index==1)
                goolkeeper2.setText("");
            else if(index==2)
                defender1.setText("");
            else if(index==3)
                defender2.setText("");
            else if(index==4)
                defender3.setText("");
            else if(index==5)
                defender4.setText("");
            else if(index==6)
                defender5.setText("");
            else if (index==7)
                midfilder1.setText("");
            else if (index==8)
                midfilder2.setText("");
            else if (index==9)
                midfilder3.setText("");
            else if (index==10)
                midfilder4.setText("");
            else if (index==11)
                midfilder5.setText("");
            else if(index==12)
                forward1.setText("");
            else if(index==13)
                forward2.setText("");
            else if(index==14)
                forward3.setText("");
        }
    }
}



