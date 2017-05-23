/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Database.GraphFacade;
import Util.JsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/msg", method = RequestMethod.GET, produces = "application/json")
    public String DoSomething(@RequestParam(value = "msg", defaultValue = "something") String msg){
        return JsonBuilder.ConvertStringToJson(
                graphFacade.DoSomething(msg));
    }
    
}
