package com.example.snowpark;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class CodController {
    public TextField cod;
    CodController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cod-view.fxml"));
        Scene scene;
        try {
            fxmlLoader.setController(this);
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = new Stage();
        stage.setTitle("SnowPark");
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("logo.png"))));
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            final int[] time = {10};
            System.out.println(time[0]);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> Platform.runLater(() -> {
                time[0]--;
                System.out.println(time[0]);
                if (time[0] <= 0) {
                    AuthController.codValue = "";
                }
            })));
            timeline.setCycleCount(10);
            timeline.play();
        });
        stage.show();
        StringBuilder generatedCod = new StringBuilder();
        for (int i = 0;i < 8; i++) {
            generatedCod.append((char) (new Random().nextInt(74) + 48));
        }
        AuthController.codValue = generatedCod.toString();
        cod.setText(generatedCod.toString());
    }
}
