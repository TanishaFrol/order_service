package services;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionToDB {

    public ResultSet connect(String statementStr, String query) {
        Connection connection = null;
        ResultSet result = null;
        try {
            FileInputStream fis;
            Properties properties = new Properties();
            fis = new FileInputStream(getClass().getClassLoader().getResource("config.properties").getFile());
            properties.load(fis);
            String url = properties.getProperty("db.host");
            String name = properties.getProperty("db.login");
            String password = properties.getProperty("db.password");

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            if (query.equalsIgnoreCase("select")) {
                result = statement.executeQuery(statementStr);
            } else {
                statement.execute(statementStr);
            }
            return result;
        } catch (Exception ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }
    }
}
