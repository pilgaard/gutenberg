/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import DTO.CityDTO;
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
        //sql.insertList();
        ArrayList<CityDTO> cities = sql.GetCities();
        System.out.println("step 1");
        ArrayList<BookDTO> books = chek.scanFiles(cities);
        int authorLength = 0;
        for (BookDTO book : books) {
            if (authorLength < book.getTitle().length()) {
                authorLength = book.getTitle().length();
            }
        }
        System.out.println("authorLength = " + authorLength);
        System.out.println("step 2");
        sql.InsertBooksInDB(books);
        System.out.println("done");
        sql.GetBooksByGeoLocation((long)48.8168000, (long)9.57690000);
        sql.GetGeoLocationByBook(sql.GetBooksByAuthorName("William Shakespear"));
        
    }
}
