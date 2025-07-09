package models;

import config.DataSourceConfig;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DBUtils {
    public static Connection getConnection() throws SQLException {
        DataSource ds = DataSourceConfig.getDataSource();
        return ds.getConnection();

    }
}
