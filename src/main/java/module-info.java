module com.coursework.breakout {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.coursework.breakout to javafx.fxml;
    exports com.coursework.breakout;
    exports com.coursework.breakout.info;
    opens com.coursework.breakout.info to javafx.fxml;
}