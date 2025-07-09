package config;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {

    private static DataSource dataSource;

    static {
        int i = 10;
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        String dbPath = DataSourceConfig.class.getClassLoader().getResource("money.db").getPath();
        ds.setUrl("jdbc:sqlite:" + dbPath);
        dataSource = ds;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

}

