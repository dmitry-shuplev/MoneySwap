package models;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtils {
    private final static String dbUrl = "jdbc:sqlite:src/main/resources/money.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки базы данных: " + e.getMessage());
        }
        return null;
    }

}
