module com.example.fantasy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.fantasy to javafx.fxml;
    exports com.example.fantasy;

}