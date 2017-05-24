/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import database.GraphFacade;
import util.JsonBuilder;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/graph")
public class GraphWebController implements IWebController {
    
    private static GraphFacade graphFacade; 
    
    public static void SetFacade(GraphFacade graphFacade){
        GraphWebController.graphFacade = graphFacade;
    }

    @Override
    @CrossOrigin(methods = RequestMethod.GET)
    @RequestMapping(value="/cities", produces = "application/json")
    public @ResponseBody String GetCities( ) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetCities());
    return json;
}

    @Override
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value="/booksbycity", produces = "application/json")
    public @ResponseBody String GetBooksByCity(@RequestParam("city") String City) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetBooksByCity(City));
    return json;
    }

    @Override
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value="/citiesbytitle", produces = "application/json")
    public @ResponseBody String GetCitiesByBookTitle(@RequestParam("title")String title) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetCitiesByBookTitle(title));
    return json;
    }

    @Override
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value="/booksbyauthor", produces = "application/json")
    public @ResponseBody String GetBooksByAuthorName(@RequestParam("authorName")String authorName) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetBooksByAuthorName(authorName));
    return json;
    }

    @Override
    @CrossOrigin(methods = RequestMethod.POST)
    @RequestMapping(value="/booksbygeolacation", produces = "application/json")
    public @ResponseBody String GetBooksByGeoLocation(@RequestParam("latitude")BigDecimal latitude, @RequestParam("longitude")BigDecimal longitude) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetBooksByGeoLocation(latitude, longitude));
    return json;
    }

}
