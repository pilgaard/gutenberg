/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Util.CityChekker;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Andreas
 */
public class DBInitializer {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLConnector msc = new MySQLConnector("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/gutenberg", "root", "root");
        MySQLDBFacade sql = new MySQLDBFacade(msc);
        CityChekker chek = new CityChekker();
        
        sql.InsertBooksInDB(chek.scanFiles(sql.GetCities()));
        
        
    }
}
