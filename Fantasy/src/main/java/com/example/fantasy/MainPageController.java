package com.example.fantasy;

import ClassesAndDatabaseconnection.Season;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
public class MainPageController {


    @FXML
    Pane seasonPane;

    @FXML
    public void goToLoginForm(ActionEvent event) throws IOException
    {
        try{
            // open register form
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
            System.out.println("going to login form from logout failed");
        }
    }

    public void okMessage(){
        seasonPane.setVisible(false);
    }
    public void goToMarket(ActionEvent event) throws IOException
    {
        try{
            if(!Season.isCanModify()){
                seasonPane.setVisible(true);
            }else{
                // open register form
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("market.fxml"));
                Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
                stage.setTitle("Fantasy");
                stage.setScene(scene);
                stage.resizableProperty().setValue(Boolean.FALSE);
                stage.show();
            }

        }
        catch (Exception ex)
        {
            System.out.println("going to market failed");
        }
    }
    @FXML
    // this function to open my team
    public void openMyTeam(ActionEvent event) throws IOException {
        try {
            // open my team form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("myTeam.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        } catch (Exception ex) {
            System.out.println("going to my team form failed");
        }

    }


    public void openRules(ActionEvent event) throws IOException {
        try {
            // open my team form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Rules.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        } catch (Exception ex) {
            System.out.println("going to my team form failed");
        }

    }



    public void openDashBoard(ActionEvent event) throws IOException {
        try {
            // open my team form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DashBoard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        } catch (Exception ex) {
            System.out.println("going to my team form failed");
        }

    }




    public void openMyProfile(ActionEvent event) throws IOException {
        try {
            // open my team form
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("myProfile.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1108, 563);
            stage.setTitle("Fantasy");
            stage.setScene(scene);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.show();
        } catch (Exception ex) {
            System.out.println("going to my team form failed");
        }

    }

}
