module com.example.montyhallgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.montyhallgame to javafx.fxml;
    exports com.example.montyhallgame;
}