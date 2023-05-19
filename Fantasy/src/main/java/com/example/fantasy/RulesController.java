package com.example.fantasy;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RulesController {
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
