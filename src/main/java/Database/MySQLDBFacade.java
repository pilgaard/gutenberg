/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import DTO.CityDTO;
import Entity.Book;
import Entity.City;
import Util.FileScanner;
import java.util.HashMap;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Andreas
 */
public class MySQLDBFacade implements IDBFacade {

    private final MySQLConnector connector;
    private Statement statement;
    private ResultSet rs;
    private FileScanner fileScanner;

    public MySQLDBFacade(MySQLConnector con, FileScanner fileScanner) {
        this.connector = con;
        this.fileScanner = fileScanner;
    }

    public void InsertBooksInDB(List<BookDTO> books) throws ClassNotFoundException, SQLException {
        //Book bookToInsert;
        try {
            statement = connector.GetConnection().createStatement();
            for (int i = 0; i < books.size(); i++) {
                //bookToInsert = new Book(books.get(i).getAuthorName(), books.get(i).getTitle(), books.get(i).getCities());
                String query = "INSERT INTO BOOKS (Title, Author, Cities) VALUES ("
                        + books.get(i).getTitle()+ ", "
                        + books.get(i).getAuthorName() + ", "
                        + books.get(i).getCities() + ");";
                rs = statement.executeQuery(query);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
            rs.close();
        }
    }
    
    private List<CityDTO> GetCities() throws SQLException{
        List<CityDTO> citiesToReturn = new ArrayList();
        String query = "SELECT geonameId, name, lat, long FROM cities";
        try{
            statement = connector.GetConnection().createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                citiesToReturn.add(new CityDTO(rs.getLong("geonameId"), rs.getLong("lat"), rs.getLong("long"), rs.getString("name")));     
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            statement.close();
            rs.close();
        }
        return citiesToReturn;
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
