package ua.goit.jdbc.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
    public Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(filePath);) {
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
