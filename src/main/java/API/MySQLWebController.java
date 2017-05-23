/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Database.MySQLDBFacade;
import Util.JsonBuilder;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andreas
 */
@RestController
@RequestMapping("/mysql")
public class MySQLWebController implements IWebController {

    private static MySQLDBFacade mysqlFacade;

    public static void SetFacade(MySQLDBFacade mysqlFacade) {
        MySQLWebController.mysqlFacade = mysqlFacade;
    }

    @Override
    @RequestMapping(value="/cities", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody String GetCities( ) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetCities());
    return json;
}

    @Override
    @RequestMapping(value="/booksbycity", method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetBooksByCity(@RequestParam("city") String City) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetBooksByCity(City));
    return json;
    }

    @Override
    @RequestMapping(value="/citiesbytitle", method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetCitiesByBookTitle(@RequestParam("title")String title) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetCitiesByBookTitle(title));
    return json;
    }

    @Override
    @RequestMapping(value="/booksbyauthor", method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetBooksByAuthorName(@RequestParam("authorName")String authorName) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetBooksByAuthorName(authorName));
    return json;
    }

    @Override
    @RequestMapping(value="/booksbygeolacation", method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetBooksByGeoLocation(@RequestParam("latitude")BigDecimal latitude, @RequestParam("longitude")BigDecimal longitude) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetBooksByGeoLocation(latitude, longitude));
    return json;
    }
}
