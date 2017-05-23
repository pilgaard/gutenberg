/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Database.MySQLDBFacade;
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
@RequestMapping("/mysql")
public class MySQLWebController implements IWebController {
    
    private static MySQLDBFacade mysqlFacade; 
    
    public static void SetFacade(MySQLDBFacade mysqlFacade){
        MySQLWebController.mysqlFacade = mysqlFacade;
    }
    
    @RequestMapping(value = "/msg", method = RequestMethod.GET, produces = "application/json")
    public String DoSeomthing(@RequestParam(value = "msg", defaultValue = "something") String msg){
        return JsonBuilder.ConvertStringToJson(
                mysqlFacade.DoSomething(msg));
    }

}
