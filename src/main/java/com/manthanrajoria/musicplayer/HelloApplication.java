package com.manthanrajoria.musicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1274, 618);
        stage.setTitle("DS Music Player");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main() {
        launch();
    }
}