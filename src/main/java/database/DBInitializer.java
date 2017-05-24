/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import dto.BookDTO;
import dto.CityDTO;
import utillity.CityChekker;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Andreas
 */
public class DBInitializer {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLConnector msc = new MySQLConnector();
        MySQLDBFacade sql = new MySQLDBFacade(msc);
        
        BigDecimal lat = new BigDecimal(48.8168000);
        BigDecimal longitude = new BigDecimal(9.57690000);
        sql.GetBooksByGeoLocation(lat, longitude );
        sql.GetGeoLocationByBook(sql.GetBooksByAuthorName("William Shakespeare"));
    }
    
    public void insert(){
        MySQLConnector msc = new MySQLConnector();
        MySQLDBFacade sql = new MySQLDBFacade(msc);
        CityChekker chek = new CityChekker();
        List<CityDTO> cities = sql.GetCities();
        List<BookDTO> books = chek.scanFiles(cities, "");
        sql.InsertBooksInDB(books);
        System.out.println("step 1");
        
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
    }
}
