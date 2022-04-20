module com.manthanrajoria.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.desktop;
    requires a;


    opens com.manthanrajoria.musicplayer to javafx.fxml;
    exports com.manthanrajoria.musicplayer;
}