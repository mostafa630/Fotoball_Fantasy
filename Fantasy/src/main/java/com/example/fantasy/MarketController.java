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
import javafx.stage.Stage;

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

       /* // fill sell combobox
        ObservableList<String> list5 = FXCollections.observableArrayList();
        for (String myPlayers : )
            list.add(goolKeeper);
        goolKeepersCombobox.setItems(list);*/
    }

    @FXML
    Label label_of_goolkeeper_club;
    @FXML
    Label label_of_goolkeeper_cost;
    @FXML
    Label label_of_goolkeeper_total_points;
    @FXML
    Label wrong_choice_of_goolkeeper;

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
}



