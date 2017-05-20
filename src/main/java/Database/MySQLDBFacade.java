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
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                rs = ps.executeQuery();
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
    
    @Override
    public List<CityDTO> GetCities() {
        List<CityDTO> citiesToReturn = new ArrayList();
        String query = "call GetCities()";
        try (Connection conn = connector.GetConnection();
                CallableStatement stmt = conn.prepareCall(query)){
            rs = stmt.executeQuery();
            while (rs.next()) {
                citiesToReturn.add(new CityDTO(rs.getLong("geonameId"), rs.getBigDecimal("lat"), rs.getBigDecimal("long"), rs.getString("asciiname")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLDBFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return citiesToReturn;
    }

    @Override
    public List<BookDTO> GetBooksByCity(String cityName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Coordinate> GetCitiesByBookTitle(String bookTitle) throws SQLException {
        ArrayList<CityDTO> citiesToReturn = new ArrayList();
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        String query = "call GetCitiesByBookTitle(?)";
        try (Connection conn = connector.GetConnection();
                CallableStatement stmt = conn.prepareCall(query)){
            
            stmt.setString(1, bookTitle);
            rs = stmt.executeQuery();
            while (rs.next()) {
                citiesToReturn.add(new CityDTO(rs.getLong("geonameId"), rs.getBigDecimal("lat"), rs.getBigDecimal("long"), rs.getString("asciiname")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            rs.close();
        } 
        for (CityDTO city : citiesToReturn) {
            coordinates.add(new Coordinate(city.getLongitude(), city.getLatitude()));
        }
        return coordinates;
    }

    @Override
    public List<BookDTO> GetBooksByAuthorName(String authorName) throws SQLException{
        ArrayList<BookDTO> booksToReturn = new ArrayList();
        String query = "call GetBooksByAuthorName(?)";
 
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
        }finally{
            rs.close();
        }
        return booksToReturn;
    }

    @Override
    public ArrayList<Coordinate> GetGeoLocationByBook(List<BookDTO> books) throws SQLException{
        List<CityDTO> cities = GetCities();
        ArrayList<Coordinate> coordinates = new ArrayList();
        for (BookDTO book : books) {
            for(CityDTO city : cities){
                if(book.getCities().contains(city.getId())){
                    coordinates.add(new Coordinate(city.getLongitude(), city.getLatitude()));
                }
            }
        }
        /*for (Coordinate coordinate : coordinates) {
            System.out.println(coordinate);
        }*/
        return coordinates;
    }

    @Override
    public List<BookDTO> GetBooksByGeoLocation(BigDecimal latitude, BigDecimal longitude) throws SQLException{
        String query = "call GetBooksByGeoLocation(?, ?)";
        BookDTO dto;
        List<BookDTO> booksBeingMentioned = new ArrayList<>();
        try(Connection conn = connector.GetConnection();
                CallableStatement stmt = conn.prepareCall(query)){
            stmt.setBigDecimal(1, latitude);
            stmt.setBigDecimal(2, longitude);
            rs = stmt.executeQuery();
            while(rs.next()){
                dto = new BookDTO();
                dto.setTitle(rs.getString("title"));
                dto.setAuthorName(rs.getString("author"));
                booksBeingMentioned.add(dto);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            rs.close();
        }
        System.out.println("booksbeingMentioned = " + booksBeingMentioned.size());
        return booksBeingMentioned;
    }
}
