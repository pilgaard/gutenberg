/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas
 */
public class MySQLConnector {

    private String driver;
    private String uri;
    private String username;
    private String password;
    private Connection connection = null;
    
    private void getInvironment() {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
        }
    }

    public MySQLConnector(String driver, String uri, String username, String password) {
        this.driver = driver;
        this.uri = uri;
        this.username = username;
        this.password = password;
        //getInvironment();
    }

    public Connection GetConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(uri, username, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void SetConnection(Connection con) {
        this.connection = con;
    }

    public int executeQuery(String query) throws ClassNotFoundException, SQLException {
        return connection.createStatement().executeUpdate(query);
    }

}
