/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

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
public class WebController {

    private static final String hello = "Hello ";
    
    @RequestMapping(value = "/greet", method = RequestMethod.GET, produces = "application/json")
    public String GreetUser(@RequestParam(value="name", defaultValue="World") String name){
        return JsonBuilder.ConvertStringToJson(hello + name);
    }
}
