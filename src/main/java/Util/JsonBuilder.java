/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DTO.BookDTO;
import DTO.CityDTO;
import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class JsonBuilder {
    
    private static final Gson gson = new Gson();
    
    public static String GetJsonFromCityDTO(CityDTO city){
        return gson.toJson(city);
    }
    
    public static String GetJsonFromBookDTO(BookDTO book){
        return gson.toJson(book);
    }
    
    public static String GetJsonFromCities(List<CityDTO> cities){
        return gson.toJson(cities);
    }
    
    public static String GetJsonFromBooks(List<BookDTO> books){
        return gson.toJson(books);
    }
}
