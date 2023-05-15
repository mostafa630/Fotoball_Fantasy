package com.example.fantasy;

import ClassesAndDatabaseconnection.Player;
import ClassesAndDatabaseconnection.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyProfileController implements Initializable {
    String currentPlayer =LoginController.getCurrenPlayer();
    @FXML
    Label ID_label ;
    @FXML
    Label userName_label ;
    @FXML
    Label password_label ;
    @FXML
    Label points_label ;
    @FXML
    TextField oldPassword_field;
    @FXML
    TextField newPassword_field;
    @FXML
    TextField confirmPassword_field;
    @FXML
    TextField username_delete;
    @FXML
    Label wrong_change_password_label;
    @FXML
    Label wrong_deltet_account_label ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrong_change_password_label.setText("");
        wrong_deltet_account_label.setText("");
         ID_label.setText( Player.getPlayers().get(currentPlayer).getId());
         userName_label.setText( Player.getPlayers().get(currentPlayer).getUserName());
         password_label.setText( Player.getPlayers().get(currentPlayer).getPassword());
         int points =Player.getPlayers().get(currentPlayer).getPoints() ;
         String point  =Integer.toString(points);
         points_label.setText(point);

    }

    public void changePassword() {
        try{
        String oldPassword = oldPassword_field.getText();
        if (!Player.getPlayers().get(currentPlayer).getPassword().equals(oldPassword))
            wrong_change_password_label.setText("check your old password");
        else {
            String newPassword = newPassword_field.getText();
            String confirmPaassword = confirmPassword_field.getText();
            if (Validation.passwordValidation(newPassword)) {
                if (Validation.confirmPassword(newPassword, confirmPaassword)) {
                    wrong_change_password_label.setText("password had changed");
                    Player.getPlayers().get(currentPlayer).setPassword(newPassword);
                    password_label.setText(newPassword);
                } else {
                    wrong_change_password_label.setText("confirm password is worng");
                }

            } else
                wrong_change_password_label.setText("password should be 8 to 20 char");
            }
          }
        catch (Exception ex)
        {
            wrong_change_password_label.setText("please check your old password");
        }
    }
    public void delteAccount  (ActionEvent event )throws IOException
    {
        try {
            String userName = username_delete.getText();
            if (Player.getPlayers().get(currentPlayer).getUserName().equals(userName)) {
                Player.players.remove(userName);
                // go to main page
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
                    System.out.println("going to Main Page board failed");
                }

            } else
                wrong_deltet_account_label.setText("check your user name");
          }
        catch(Exception ex)
        {
            wrong_deltet_account_label.setText("check user name");
        }

    }

    public void goToMainPage(ActionEvent event) throws IOException {
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
            System.out.println("going to Main Page board failed");
        }
    }
}
