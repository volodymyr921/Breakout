module com.coursework.breakout {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.coursework.breakout to javafx.fxml;
    exports com.coursework.breakout;
}