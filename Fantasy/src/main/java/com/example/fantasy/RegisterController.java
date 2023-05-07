package com.example.fantasy;

import ClassesAndDatabaseconnection.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
    // this function to open login form if we pressed Sign IN button
    public void openLoginFrom  (ActionEvent event)throws IOException
    {
        try{
            // open login form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();

        }
        catch (Exception ex)
        {

        }

    }
    public void checkData()
    {
        if(Validation.nationalIdValidation(ID_text.getText())
        && Validation.usernameValidation(username_text.getText())
        && Validation.passwordValidation(password_text.getText())
        && Validation.confirmPassword(password_text.getText(),confirm_password_text.getText())) {
            System.out.println("done");
        }
        else {
            System.out.println("failed");
        }

    }
}
