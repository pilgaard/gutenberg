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
    
    public List<CityDTO> getCities(){
        List<CityDTO> cities = new ArrayList();
        String query = "match (n:City) return n.id, n.asciiname, n.lat, n.long;";
        Driver driver = connector.GetDriver();
        Session session = driver.session();

        // Run a query matching all nodes
        StatementResult result = session.run(query);

        while ( result.hasNext() ) {
            Record record = result.next();
            Long id = Long.parseLong(record.get("n.id").asString()); 
            String name = record.get("n.asciiname").asString();
            BigDecimal latitude = new BigDecimal(record.get("n.lat").asString());
            BigDecimal longitude = new BigDecimal(record.get("n.long").asString());
            
            CityDTO cityDTO = new CityDTO(id, latitude, longitude, name);
            cities.add(cityDTO);
        }
        session.close();
        driver.close();
        return cities;
    }

    @Override
    public List<BookDTO> GetBooksByCity(String cityName) {
        String query = "MATCH (c:City { name: '"+cityName+"' })-[r:MENTIONED_IN]->(b:Book)\n" +
                        "RETURN  b.id, b.author, b.title;";
        return null;
    }

    @Override
    public List<Coordinate> GetCitiesByBookTitle(String bookTitle) throws SQLException {
        String query = "MATCH (c:City)-[r:MENTIONED_IN]->(b:Book { title: '"+bookTitle+"' })\n" +
                        "RETURN  c.id, c.asciiname, c.lat, long;";
        return null;
    }

    @Override
    public List<BookDTO> GetBooksByAuthorName(String authorName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Coordinate> GetGeoLocationByBook(List<BookDTO> books) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BookDTO> GetBooksByGeoLocation(BigDecimal latitude, BigDecimal longitude) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
