/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import database.MySQLDBFacade;
import utillity.JsonBuilder;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin(methods = RequestMethod.GET)
    @RequestMapping(value="/cities", produces = "application/json")
    public @ResponseBody String GetCities( ) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetCities());
    return json;
}

    @Override
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value="/booksbycity", produces = "application/json")
    public @ResponseBody String GetBooksByCity(@RequestParam("city") String City) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetBooksByCity(City));
    return json;
    }

    @Override
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value="/citiesbytitle", produces = "application/json")
    public @ResponseBody String GetCitiesByBookTitle(@RequestParam("title")String title) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetCitiesByBookTitle(title));
    return json;
    }

    @Override
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value="/booksbyauthor", produces = "application/json")
    public @ResponseBody String GetBooksByAuthorName(@RequestParam("authorName")String authorName) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetBooksByAuthorName(authorName));
    return json;
    }

    @Override
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value="/booksbygeolacation", produces = "application/json")
    public @ResponseBody String GetBooksByGeoLocation(@RequestParam("latitude")BigDecimal latitude, @RequestParam("longitude")BigDecimal longitude) {
        String json = JsonBuilder.gson.toJson(mysqlFacade.GetBooksByGeoLocation(latitude, longitude));
    return json;
    }
}
