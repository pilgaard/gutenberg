/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;
import Database.GraphConnector;
import Database.GraphFacade;
import Database.MySQLConnector;
import Database.MySQLDBFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * @author Andreas
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        MySQLConnector mysqlCon = new MySQLConnector();
        GraphConnector graphCon = new GraphConnector();
        MySQLDBFacade mysqlFacade = new MySQLDBFacade(mysqlCon);
        GraphFacade graphFacade = new GraphFacade(graphCon);
        //WebController webController = new MySQLWebController();
        MySQLWebController.SetFacade(mysqlFacade);
        SpringApplication.run(Application.class, args);
    }
}
