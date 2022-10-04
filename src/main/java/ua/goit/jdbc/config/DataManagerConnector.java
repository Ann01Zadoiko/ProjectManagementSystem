package ua.goit.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataManagerConnector {
    private String url;
    private Properties properties;

    public DataManagerConnector(Properties properties, String user, String password) {
        init(properties, user, password);
    }

    private void init(Properties properties, String user, String password){
        url = String.format("jdbc:postgresql://%s:%s/%s", properties.get("host"),
                properties.get("port"), properties.get("databaseName"));
        this.properties = new Properties();
        this.properties.setProperty("user", user);
        this.properties.setProperty("password", password);
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, properties);
    }

}
