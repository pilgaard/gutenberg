/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    
    // Insert Actual values.
    public MySQLConnector(){
        this.driver = "";
        this.uri = "";
        this.username = "";
        this.password = "";
    }
    
    public MySQLConnector(String driver, String uri, String username, String password){
        this.driver = driver;
        this.uri = uri;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        if(connection == null || connection.isClosed()){
            Class.forName(driver);
            connection = DriverManager.getConnection(uri, username, password);
        }
        return connection;
    }
}
