package com.example.snowpark.Utils;

import com.example.snowpark.Models.User;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLDBConnector {
    String url = "jdbc:mysql://localhost:3306/user28";
    String user = "root";
    String password = "";

    Connection connection;

    public MySQLDBConnector() {
        try {
            connection = DriverManager.getConnection(
                    url,
                    user,
                    password);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Ошибка подключения к базе данных:\n" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean checkLogin(String login) {
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM `sotrudniki` WHERE `login`='"+login+"';"
            );

            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Ошибка получения данных пользователя:\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        return false;
    }

    public User logIn(String login, String password) {
        User user = null;
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(
                    "SELECT * FROM `sotrudniki` s, `roles` r WHERE `login`='"+login+"' and `password`='"+password+"' and s.roles=r.id;"
            );

            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("secondName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Ошибка получения данных пользователя:\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        return user;
    }
}
