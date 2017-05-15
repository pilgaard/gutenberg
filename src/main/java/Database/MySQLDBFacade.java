/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import Entity.Book;
import Entity.City;
import Util.FileScanner;
import java.util.HashMap;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Andreas
 */
public class MySQLDBFacade implements IDBFacade {

    private final MySQLConnector connector;

    public MySQLDBFacade(MySQLConnector con, FileScanner fileScanner) {
        this.connector = con;
    }
    
    public void InsertBooksInDB(List<BookDTO> books) throws SQLException, ClassNotFoundException{
        try{
            connector.GetEM().getTransaction().begin();
            for(int i = 0; i < books.size(); i++){
                Book bookToInsert = new Book(books.get(i).getAuthorName(), books.get(i).getTitle(), books.get(i).getCityNames());
                connector.GetEM().persist(bookToInsert);
            }
            connector.GetEM().getTransaction().commit();
        }finally{
            connector.GetEM().close();
        }
    }

    @Override
    public List<Book> GetBooksByCity(String cityName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<City> GetCitiesByBookTitle(String bookTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Long, Long> GetGeoLocationByCity(City city) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> GetBooksByAuthorName(String authorName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Long, Long> GetGeoLocationByBook(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> GetBooksByGeoLocation(Long latitude, Long longitude) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
