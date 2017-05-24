/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.math.BigDecimal;
/**
 *
 * @author Andreas
 */
public interface IWebController{
    
    public String GetCities();
    
    public String GetBooksByCity(String City);
    
    public String GetCitiesByBookTitle(String Title);
    
    public String GetBooksByAuthorName(String authorName);
    
    public String GetBooksByGeoLocation(BigDecimal latitude, BigDecimal longitude); 
    

}
