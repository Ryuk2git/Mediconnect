package com.edutech.progressive.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    // Properties: Thread safe way to hold db credentials
    // static makes it global, final makes it constant
    private static final Properties properties = new Properties();

    // static block runs once when the class is first loaded in memory
    static{
        loadProperties();
    }

    // util.Properties used to store configuration data(key-value) loaded from application.properties
    // InputStream is ised for reading configuration file from project classpath
    private static void loadProperties(){
        // Try-with-resource block is used for automatic closing of inputStream

        // DatabaseConnectionManager: to get db cred from application.properties file
        // Stream is unidirectional: Moves data from file to program
        try(InputStream input = DatabaseConnectionManager.class.getClassLoader().getResourceAsStream("application.properties")){
            if(input != null){
                properties.load(input);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
            properties.getProperty("spring.datasource.url"),
            properties.getProperty("spring.datasource.username"),
            properties.getProperty("spring.datasource.password")
        );
    }
}
