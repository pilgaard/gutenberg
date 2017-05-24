/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dto.BookDTO;
import dto.CityDTO;
import dto.Coordinate;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class JsonBuilder {
    
    public static final Gson gson = new GsonBuilder()
                                        .setPrettyPrinting()
                                        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                                        .create();
    
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
    
    public static String GetJsonFromCoordinates(List<Coordinate> coordinates){
        return gson.toJson(coordinates);
    }
    
    public static String ConvertStringToJson(String string){
        return gson.toJson(string);
    }
}
