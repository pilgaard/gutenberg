/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import DTO.CityDTO;
import DTO.Coordinate;
import java.awt.Point;
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

    public void InsertBooksInDB(List<BookDTO> books) throws SQLException {
        //Book bookToInsert;
        try {
            statement = connector.GetConnection().createStatement();
            connector.GetConnection().setAutoCommit(false);
            for (int i = 0; i < books.size(); i++) {
                //bookToInsert = new Book(books.get(i).getAuthorName(), books.get(i).getTitle(), books.get(i).getCities());
                String query = "INSERT INTO books (Title, Author) VALUES ("
                        + "'" + books.get(i).getTitle() + "'" + ", "
                        + "'" + books.get(i).getAuthorName() + "')";
                statement.execute(query);
                int remaining = books.size()-i;
                System.out.println("remaining = " + remaining);
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
        String query = "call GetCities()";
        ResultSet rs;
        try (Connection conn = connector.GetConnection();
                CallableStatement stmt = conn.prepareCall(query)){
            rs = stmt.executeQuery();
            while (rs.next()) {
                citiesToReturn.add(new CityDTO(rs.getLong("geonameId"), rs.getLong("lat"), rs.getLong("long"), rs.getString("asciiname")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return citiesToReturn;
    }

    @Override
    public List<BookDTO> GetBooksByCity(String cityName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Coordinate> GetCitiesByBookTitle(String bookTitle) {
        ArrayList<CityDTO> citiesToReturn = new ArrayList();
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        String query = "call GetCitiesByBookTitle(?)";
        ResultSet rs;
        try (Connection conn = connector.GetConnection();
                CallableStatement stmt = conn.prepareCall(query)){
            
            stmt.setString(1, bookTitle);
            rs = stmt.executeQuery();
            while (rs.next()) {
                citiesToReturn.add(new CityDTO(rs.getLong("geonameId"), rs.getLong("lat"), rs.getLong("long"), rs.getString("asciiname")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        for (CityDTO city : citiesToReturn) {
            coordinates.add(new Coordinate(city.getLongitude(), city.getLatitude()));
        }
        return coordinates;
    }

    @Override
    public List<BookDTO> GetBooksByAuthorName(String authorName) {
        ArrayList<BookDTO> booksToReturn = new ArrayList();
        String query = "call GetBooksByAuthorName(?)";
        ResultSet rs;
 
        try (Connection conn = connector.GetConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
 
            stmt.setString(1, authorName);
 
            rs = stmt.executeQuery();
            while (rs.next()) {
                String str = rs.getString("cityID");
                List<String> cityIdStr = Arrays.asList(str.split(","));
                List<Long> cityId = new ArrayList();
                for (String string : cityIdStr) {
                    cityId.add(Long.parseLong(string));
                }
                BookDTO book = new BookDTO(rs.getString("author"),rs.getString("title"),cityId);
                booksToReturn.add(book);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return booksToReturn;
    }

    @Override
    public HashMap<Long, Long> GetGeoLocationByBook(BookDTO book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BookDTO> GetBooksByGeoLocation(Long latitude, Long longitude) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
