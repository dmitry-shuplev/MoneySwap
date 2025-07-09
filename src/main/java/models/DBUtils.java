package models;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private final static String SQLITE_JDBC_DRIVER = "org.sqlite.JDBC";
    private final static String DATABASE_URL = "jdbc:sqlite::resource:money.db";
    private final static int MIN_MINUTES_IDLE = 5;
    private final static int MAX_MINUTES_IDLE = 10;
    private final static int MAX_OPENED_CONNECTIONS = 20;

    private static BasicDataSource dataSource = new BasicDataSource();
    //log
    int i = 10;

    static {
        dataSource.setUrl(DATABASE_URL);
        dataSource.setDriverClassName(SQLITE_JDBC_DRIVER);
        dataSource.setMinIdle(MIN_MINUTES_IDLE);
        dataSource.setMaxIdle(MAX_MINUTES_IDLE);
        dataSource.setMaxOpenPreparedStatements(MAX_OPENED_CONNECTIONS);
    }

    public static Connection getConnection() throws SQLException {


        return dataSource.getConnection();
    }

    private DBUtils() {
    }
}