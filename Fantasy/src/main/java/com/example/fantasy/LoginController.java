package com.example.fantasy;

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

public class LoginController {
    @FXML
    // this function to open register form if we pressed SinUp button
    public void openRegisterFrom  (ActionEvent event)throws IOException
    {
        try{
          // open register form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        }
        catch (Exception ex)
        {
            System.out.println("going to register form failed");
        }

    }

    @FXML
     Label login_failed_label ;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    // this function to open main page if the login process sucesseded
    public void openMainPageFrom  (ActionEvent event)throws IOException {
        if (username.getText().equals("Ahmed123")  && password.getText().equals("admin123") ) {
            try {
                // open Admin page form
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminPage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
                stage.setTitle("Fantasy");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            } catch (Exception ex) {
                System.out.println("going to admin page  failed");
            }
        } else {
            if (Validation.loginCheck(username.getText(), password.getText())) // true will be replaced with validation actions
            {

                try {
                    // open main page form
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainPage.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
                    stage.setTitle("Fantasy");
                    stage.setScene(scene);
                    stage.resizableProperty().setValue(Boolean.FALSE);
                    stage.show();
                } catch (Exception ex) {
                    System.out.println("going to main page  failed");
                }
            } else {
                login_failed_label.setText("login failed ,please check your username or password");
            }

        }
    }
}