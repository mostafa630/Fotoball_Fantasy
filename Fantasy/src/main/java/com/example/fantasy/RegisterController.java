package com.example.fantasy;

import ClassesAndDatabaseconnection.Player;
import ClassesAndDatabaseconnection.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    TextField ID_text;
    @FXML
    TextField username_text;
    @FXML
    TextField password_text;
    @FXML
    TextField confirm_password_text;
    @FXML
    Label register_failed_label;

    @FXML
    // this function to open login form if we pressed Sign IN button
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

        }

    }

    /*
      this functioncheck if the data of register is correct or not.
      -if the data is correct stor :
           1-the user data in players  hash table
           2- the user id in playersNationalId has table
      -if the data is incorrect then  I told the user his error to change it
     */
    public void checkData() {
        if (Validation.nationalIdValidation(ID_text.getText())
                && Validation.usernameValidation(username_text.getText())
                && Validation.passwordValidation(password_text.getText())
                && Validation.confirmPassword(password_text.getText(), confirm_password_text.getText()))
        {
            Player currentPlayer =new Player(ID_text.getText(),username_text.getText(),password_text.getText());
            Player.putPlayerToPlayers(currentPlayer.getUserName(),currentPlayer);
            Player.putNationalIDToNationalIDs(currentPlayer.getId());
            currentPlayer.setInitialTeamNull();
            register_failed_label.setText("register sucseeded go to login");
        }
        else
        {
            if(!Validation.nationalIdValidation(ID_text.getText()))
                register_failed_label.setText("please check your: ID");
            else if (!Validation.usernameValidation(username_text.getText()))
                register_failed_label.setText("please check your: Username");
            else if (!Validation.passwordValidation(password_text.getText()))
                register_failed_label.setText("please check your: Password");
            else
                register_failed_label.setText("please check your: ConfirmPassword");
        }

        }

}
