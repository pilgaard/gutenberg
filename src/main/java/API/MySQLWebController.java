/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Database.MySQLDBFacade;
import Util.JsonBuilder;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Andreas
 */
@RestController
@RequestMapping("/mysql")
public class MySQLWebController implements IWebController {
    
    private static MySQLDBFacade mysqlFacade; 
    
    public static void SetFacade(MySQLDBFacade mysqlFacade){
        MySQLWebController.mysqlFacade = mysqlFacade;
    }
    
    @Override
    @RequestMapping(value = "/msg", method = RequestMethod.GET, produces = "application/json")
    public String DoSomething(@RequestParam(value = "msg", defaultValue = "something") String msg){
        return JsonBuilder.ConvertStringToJson(
                mysqlFacade.DoSomething(msg));
    }
    
    @Override
    @RequestMapping(
            value = "/GetBooksByCity/{cityName}", 
            method = RequestMethod.GET, 
            produces = "application/json")
    public String GetBooksByCity(@PathVariable String cityName) {
        String books = JsonBuilder.GetJsonFromBooks(
                mysqlFacade.GetBooksByCity(cityName)
        );
        return books;
    }

    @Override
    @RequestMapping(
            value = "/GetCitiesByBookTitle/{bookTitle}", 
            method = RequestMethod.GET, 
            produces = "application/json")
    public String GetCitiesByBookTitle(@PathVariable String bookTitle) {
        String coordinates = JsonBuilder.GetJsonFromCoordinates(
                mysqlFacade.GetCitiesByBookTitle(bookTitle)
        );
        return coordinates;
    }

    @Override
    @RequestMapping(
            value = "/GetBooksByGeoLocation/{latitude}/{longitude}", 
            method = RequestMethod.GET, 
            produces = "application/json")
    public String GetBooksByGeoLocation(@PathVariable String latitude, @PathVariable String longitude) {
        BigDecimal tempLat = new BigDecimal(latitude);
        BigDecimal tempLong = new BigDecimal(longitude);
        String books = JsonBuilder.GetJsonFromBooks(
                mysqlFacade.GetBooksByGeoLocation(tempLat, tempLong)
        );
        return books;
    }
    
    

}
