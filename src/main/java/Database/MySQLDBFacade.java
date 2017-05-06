/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Andreas
 */
public class MySQLDBFacade {
    
     private MySQLConnector connector;
    
    public MySQLDBFacade(MySQLConnector con){
        this.connector = con;
    } 
    
}