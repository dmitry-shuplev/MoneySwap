package config;

import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;

public class DataSourceConfig {

    private static DataSource dataSource;
    static {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        //String dbPath = DataSourceConfig.class.getClassLoader().getResource("money.db").getPath();
        //ds.setUrl("jdbc:sqlite:" + dbPath);
        ds.setUrl("jdbc:sqlite:C:/Users/j_dim/Java_programm/MoneySwap/src/main/webapp/WEB-INF/money.db");
        dataSource = ds;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

}

