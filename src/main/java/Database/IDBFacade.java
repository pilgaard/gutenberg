/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import Entity.Book;
import Entity.City;
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
    public List<BookDTO> GetBooksByCity(String cityName);
    
    //Given a book title, your application plots all cities mentioned in this book onto a map.
    public List<City> GetCitiesByBookTitle(String bookTitle);
    public HashMap<Long, Long> GetGeoLocationByCity(City city);
    
    //Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.
    public List<Book> GetBooksByAuthorName(String authorName);
    public HashMap<Long, Long> GetGeoLocationByBook(Book book);
    
    //Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.
    public List<Book> GetBooksByGeoLocation(Long latitude, Long longitude);
    

}
