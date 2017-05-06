/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Entity.Book;
import Entity.City;
import java.util.List;
/**
 *
 * @author Andreas
 */
public class FileScanner {
    private final String dir = System.getProperty("user.dir");
    private final String citiesFileLocation = dir + "\\cities15000.text";
    private final String booksFileLocation = "";
    
    // Read cities .CSV file. Create City objects and return all.
    public List<City> FetchAllCities(){
        return null;
    }
    
    // Read Books files. When City name is found, add to a list (if it is not already found). 
    // Create Book objects, and fill with found city names. 
    // Maybe split into 2 methods instead of one.
    public List<Book> FetchAllBooks(){
        return null;
    }
}
