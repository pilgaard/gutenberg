/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import dto.BookDTO;
import dto.CityDTO;
import dto.Coordinate;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Andreas
 */
public interface IDBFacade {
    
    /**
    Given a city name your application returns all book titles with corresponding authors that mention this city.
    @param cityName The name of the city to look for.
    @return Returns a List of BookDTO.
    */    
    public List<BookDTO> GetBooksByCity(String cityName) throws Exception;
    
    public List<CityDTO> GetCities() throws Exception;
    //Given a book title, your application plots all cities mentioned in this book onto a map.
    public List<Coordinate> GetCitiesByBookTitle(String bookTitle) throws Exception;
    
    //Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.
    public List<BookDTO> GetBooksByAuthorName(String authorName) throws Exception;
    public ArrayList<Coordinate> GetGeoLocationByBook(List<BookDTO> books) throws Exception;
    
    
    /**
     * Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.
     * @param latitude
     * @param longitude
     * @return 
     */
    public List<BookDTO> GetBooksByGeoLocation(BigDecimal latitude, BigDecimal longitude) throws Exception;
    

}
