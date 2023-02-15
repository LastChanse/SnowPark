package com.example.snowpark;

import com.example.snowpark.Models.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    User user;
    Stage stage;
    @FXML
    public Label FIO, role;
    MainController() {
        AuthController.stage.close();
        user = AuthController.user;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene;
        try {
            fxmlLoader.setController(this);
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = new Stage();
        stage.setTitle("SnowPark");
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("logo.png"))));
        stage.setScene(scene);
        stage.show();
        FIO.setText(user.getSecondName()+" "+user.getFirstName()+" "+user.getLastName());
        role.setText(user.getRole());
    }

    @FXML
    public void exit() {
        stage.close();
        new AuthController();
    }
}