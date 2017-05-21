/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import DTO.CityDTO;
import DTO.Coordinate;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.driver.v1.*;

/**
 *
 * @author Andreas
 */
public class GraphFacade implements IDBFacade{
    
    private GraphConnector connector;
    
    public GraphFacade(GraphConnector con){
        this.connector = con;
    }
    
    @Override
    public List<CityDTO> GetCities(){
        List<CityDTO> cities = new ArrayList();
        String query = "match (n:City) return n.id, n.asciiname, n.lat, n.long;";
        Driver driver = connector.GetDriver();
        Session session = driver.session();

        // Run a query matching all nodes
        StatementResult result = session.run(query);

        while ( result.hasNext() ) {
            Record record = result.next();
            long id = record.get("n.id").asLong(); 
            String name = record.get("n.asciiname").asString();
            double d1 = Math.round((record.get("n.lat").asDouble()*100000000));
            double d2 = Math.round((record.get("n.long").asDouble()*100000000));
            BigDecimal latitude = new BigDecimal(d1/100000000);
            BigDecimal longitude = new BigDecimal(d2/100000000);
            CityDTO cityDTO = new CityDTO(id, latitude, longitude, name);
            cities.add(cityDTO);
        }
        session.close();
        driver.close();
        return cities;
    }

    @Override
    public List<BookDTO> GetBooksByCity(String cityName) {
        List<BookDTO> books = new ArrayList();
        BookDTO book; 
        String query = "MATCH (c:City { name: '"+cityName+"' })-[r:MENTIONED_IN]->(b:Book)\n" +
                        "RETURN  b.id, b.author, b.title;";
        Driver driver = connector.GetDriver();
        Session session = driver.session();

        // Run a query matching all nodes
        StatementResult result = session.run(query);
        while ( result.hasNext() ) {
            book = new BookDTO();
            Record record = result.next();
            String author = record.get("b.author").asString();
            String title = record.get("b.title").asString();
            book.setAuthorName(author);
            book.setTitle(title);
            books.add(book);
        }
        session.close();
        driver.close();
        return books;
    }

    @Override
    public List<Coordinate> GetCitiesByBookTitle(String bookTitle) {
        List<CityDTO> citiesToReturn = new ArrayList();
        CityDTO city;
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        String query = "MATCH (c:City)-[r:MENTIONED_IN]->(b:Book { title: '"+bookTitle+"' })\n" +
                        "RETURN  c.id, c.asciiname, c.lat, c.long;";
        Driver driver = connector.GetDriver();
        Session session = driver.session();

        // Run a query matching all nodes
        StatementResult result = session.run(query);
        while ( result.hasNext() ) {
            Record record = result.next();
            city = new CityDTO();
            long id = record.get("c.id").asLong();
            String name = record.get("c.asciiname").asString();
            BigDecimal latitude = new BigDecimal(record.get("c.lat").asDouble());
            BigDecimal longitude = new BigDecimal(record.get("c.long").asDouble());
            
            CityDTO cityDTO = new CityDTO(id, latitude, longitude, name);
            citiesToReturn.add(cityDTO);
        }
        session.close();
        driver.close();
        for (CityDTO ci : citiesToReturn) {
            coordinates.add(new Coordinate(ci.getLongitude(), ci.getLatitude()));
        }
        return coordinates;
    }

    @Override
    public List<BookDTO> GetBooksByAuthorName(String authorName) {
        List<BookDTO> books = new ArrayList();
        BookDTO book;
        String query = "MATCH (b:Book{author: '"+authorName+"'}) RETURN b;";
        Driver driver = connector.GetDriver();
        Session session = driver.session();

        // Run a query matching all nodes
        StatementResult result = session.run(query);
        while ( result.hasNext() ) {
            book = new BookDTO();
            Record record = result.next();
            String author = record.get("b.author").asString();
            String title = record.get("b.title").asString();
            book.setAuthorName(author);
            book.setTitle(title);
            books.add(book);
        }
        session.close();
        driver.close();
        return books;
    }

    @Override
    public ArrayList<Coordinate> GetGeoLocationByBook(List<BookDTO> books) {
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
    public List<BookDTO> GetBooksByGeoLocation(BigDecimal latitude, BigDecimal longitude) {
        List<BookDTO> books = new ArrayList();
        BookDTO book;
        String query = "WITH "+latitude+" AS latitude, "+longitude+" AS longitude MATCH (c:City)-[r:MENTIONED_IN]->(b:Book) "
                + "WHERE 2 * 6371 * asin(sqrt(haversin(radians(latitude - c.lat))+ cos(radians(latitude))* cos(radians(c.lat))* "
                + "haversin(radians(longitude - c.long)))) < 50.0 RETURN c;";
        Driver driver = connector.GetDriver();
        Session session = driver.session();

        // Run a query matching all nodes
        StatementResult result = session.run(query);
        while ( result.hasNext() ) {
            book = new BookDTO();
            Record record = result.next();
            String author = record.get("b.author").asString();
            String title = record.get("b.title").asString();
            book.setAuthorName(author);
            book.setTitle(title);
            books.add(book);
        }
        session.close();
        driver.close();
        return books;
    }
}
