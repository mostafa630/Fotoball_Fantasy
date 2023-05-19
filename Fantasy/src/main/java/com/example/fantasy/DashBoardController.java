package com.example.fantasy;

import ClassesAndDatabaseconnection.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DashBoardController implements Initializable {
    @FXML
    TextArea txtArea ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       List<Pair<Integer ,String>> orderList =new ArrayList<>();
        for(Map.Entry<String ,Player> player : Player.getPlayers().entrySet())
        {
            Pair<Integer ,String>currentPair =new Pair<>(player.getValue().getPoints(),player.getKey() );
            orderList.add(currentPair);
        }
        Collections.sort(orderList, Comparator.comparing(p -> -p.getKey()));
        txtArea.setFont(Font.font("Monospaced",20));
        txtArea.appendText("\n");
         int i=1 ;
        for(Pair<Integer ,String> player : orderList)
        {
            String tempString=player.getValue();
            String outPut =String.format("%-60s%-10d",tempString,player.getKey());
            txtArea.appendText(i+"- "+ outPut+"\n\n");
            txtArea.appendText("--------------------------------------------------------------------\n");
            i++;
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
