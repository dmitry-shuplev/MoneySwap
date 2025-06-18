package org.example;

import java.sql.*;

public class App {
    public static void main(String[] args) {

        try {
            String url = "jdbc:sqlite:src/main/resources/money.db";
            Connection connection = DriverManager.getConnection(url);
            System.out.println("Успешнo подключено");
        } catch (Exception e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }
}

