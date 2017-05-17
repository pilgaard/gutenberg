/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import DTO.CityDTO;
import DTO.Coordinate;
import Entity.Book;
import Entity.City;
import java.util.HashMap;
import java.util.List;

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
    public List<BookDTO> GetBooksByCity(String cityName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BookDTO> GetBooksByAuthorName(String authorName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Long, Long> GetGeoLocationByBook(BookDTO book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BookDTO> GetBooksByGeoLocation(Long latitude, Long longitude) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Coordinate> GetCitiesByBookTitle(String bookTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
