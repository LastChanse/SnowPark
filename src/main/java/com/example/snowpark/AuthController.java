package com.example.snowpark;

import com.example.snowpark.Models.User;
import com.example.snowpark.Utils.MySQLDBConnector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class AuthController {
    public static Stage stage;
    public static String codValue = "";
    public HBox loginArea;
    public HBox passwordArea;
    public HBox codArea;
    public BorderPane update;
    public Button loginBtn;
    public TextField login;
    public PasswordField password;
    public TextField cod;
    public static User user;

    public AuthController() {
        try {
            stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("auth-view.fxml"));
            Scene scene = null;
            fxmlLoader.setController(this);
            scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setTitle("SnowPark");
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("logo.png"))));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        login.requestFocus();
        passwordArea.setDisable(true);
        codArea.setDisable(true);
        update.setDisable(true);
        update.setStyle("-fx-border-color: #1115;");
        loginBtn.setDisable(true);
        login.setText("");
        password.setText("");
        cod.setText("");
    }

    ;

    public void login() {
        if (!codValue.equals(cod.getText()) || codValue.length() != 8) {
            new Alert(Alert.AlertType.INFORMATION, "Введённый код не верный или не актуальный").show();
            return;
        }
        new MainController();
        new Alert(Alert.AlertType.INFORMATION, "Вход успешен!").show();
    }

    public void cancel() {
        initialize();
    }

    public void loginPressKey(KeyEvent keyEvent) {
        if (!keyEvent.getCode().equals(KeyCode.ENTER)) {
           return;
        }
        if(!new MySQLDBConnector().checkLogin(login.getText())) {
            new Alert(Alert.AlertType.ERROR, "Пользователя с таким логином не существует").show();
            return;
        }
        passwordArea.setDisable(false);
        password.requestFocus();
    }

    public void passwordPressKey(KeyEvent keyEvent) {
        if (!keyEvent.getCode().equals(KeyCode.ENTER)) {
            return;
        }
        user = new MySQLDBConnector().logIn(login.getText(), password.getText());
        if (user == null) {
            new Alert(Alert.AlertType.ERROR, "Введённый пароль неверный").show();
            return;
        }
        codArea.setDisable(false);
        update.setDisable(false);
        loginBtn.setDisable(false);
        update.setStyle("");
        cod.requestFocus();

    }

    public void codPressKey(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            login();
        }
    }

    public void updateCod() {
        new CodController();
    }
}