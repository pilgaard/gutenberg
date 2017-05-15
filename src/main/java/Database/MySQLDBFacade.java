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
import java.util.*;
import java.sql.*;

/**
 *
 * @author Andreas
 */
public class MySQLDBFacade implements IDBFacade {

    private final MySQLConnector connector;
    private Statement statement;
    private ResultSet rs;

    public MySQLDBFacade(MySQLConnector con) {
        this.connector = con;
    }

    public void insertList() throws ClassNotFoundException, SQLException {
        statement = connector.GetConnection().createStatement();
        connector.GetConnection().setAutoCommit(false);
        PreparedStatement ps = connector.GetConnection().prepareStatement("INSERT INTO test(data) VALUES (?)");
        for (int i = 0; i < 100; i++) {
            ps.setObject(1, i);
            ps.addBatch();
        }
        ps.executeBatch();
        connector.GetConnection().commit();
    }

    public void InsertBooksInDB(List<BookDTO> books) throws ClassNotFoundException, SQLException {
        //Book bookToInsert;
        try {
            statement = connector.GetConnection().createStatement();
            connector.GetConnection().setAutoCommit(false);
            for (int i = 0; i < books.size(); i++) {
                PreparedStatement ps = connector.GetConnection().prepareStatement("INSERT INTO books (Title, Author) VALUES (?,?)");
                ps.setObject(1, books.get(i).getTitle());
                ps.setObject(2, books.get(i).getAuthorName());
                ps.execute();
                connector.GetConnection().commit();
                statement = connector.GetConnection().createStatement();
                ps = connector.GetConnection().prepareStatement("SELECT LAST_INSERT_ID() as bookId;");
                ResultSet rs = ps.executeQuery();
                int id = 0;
                while (rs.next()) {
                    id = rs.getInt("bookId");
                    System.out.println("id = " + id);
                }
                ps = connector.GetConnection().prepareStatement("INSERT INTO CitiesInBooks(bookId, cityId) VALUES (?,?)");
                List<Long> ids = books.get(i).getCities();
                for (Long id1 : ids) {
                    ps.setObject(1, id);
                    ps.setObject(2, id1);
                    ps.addBatch();
                }
                ps.executeBatch();
                connector.GetConnection().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
            rs.close();
        }
    }

    public ArrayList<CityDTO> GetCities() throws SQLException {
        ArrayList<CityDTO> citiesToReturn = new ArrayList();
        String query = "SELECT geonameId, asciiname, lat, `long` FROM cities order by asciiname asc;";
        try {
            statement = connector.GetConnection().createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                citiesToReturn.add(new CityDTO(rs.getLong("geonameId"), rs.getLong("lat"), rs.getLong("long"), rs.getString("asciiname")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
            rs.close();
        }
        return citiesToReturn;
    }

    @Override
    public List<BookDTO> GetBooksByCity(String cityName) {
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
