module com.example.minesweeper {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires firmata4j;


    opens com.example.minesweeper to javafx.fxml;
    exports com.example.minesweeper;
}