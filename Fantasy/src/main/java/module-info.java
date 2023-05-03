module com.example.fantasy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fantasy to javafx.fxml;
    exports com.example.fantasy;
}