package models;

import config.DataSourceConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtils {
    public static Connection getConnection() throws SQLException{
        DataSource ds = DataSourceConfig.getDataSource();
        return ds.getConnection();
    }
}
