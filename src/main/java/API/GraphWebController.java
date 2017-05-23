/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Database.GraphFacade;
import Util.JsonBuilder;
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
@RequestMapping("/graph")
public class GraphWebController implements IWebController {
    
    private static GraphFacade graphFacade; 
    
    public static void SetFacade(GraphFacade graphFacade){
        GraphWebController.graphFacade = graphFacade;
    }

    @Override
    @RequestMapping(value="/cities", method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody String GetCities( ) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetCities());
    return json;
}

    @Override
    @RequestMapping(value="/booksbycity", method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetBooksByCity(@RequestParam("city") String City) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetBooksByCity(City));
    return json;
    }

    @Override
    @RequestMapping(value="/citiesbytitle", method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetCitiesByBookTitle(@RequestParam("title")String title) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetCitiesByBookTitle(title));
    return json;
    }

    @Override
    @RequestMapping(value="/booksbyauthor", method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetBooksByAuthorName(@RequestParam("authorName")String authorName) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetBooksByAuthorName(authorName));
    return json;
    }

    @Override
    @RequestMapping(value="/booksbygeolacation", method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody String GetBooksByGeoLocation(@RequestParam("latitude")BigDecimal latitude, @RequestParam("longitude")BigDecimal longitude) {
        String json = JsonBuilder.gson.toJson(graphFacade.GetBooksByGeoLocation(latitude, longitude));
    return json;
    }

}
