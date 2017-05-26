/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import database.GraphConnector;
import database.GraphFacade;
import database.MySQLConnector;
import database.MySQLDBFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 *
 * @author Andreas
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        start(args);
    }
    
    public static void start(String[] args){
        MySQLConnector mysqlCon = new MySQLConnector();
        GraphConnector graphCon = new GraphConnector();
        MySQLDBFacade mysqlFacade = new MySQLDBFacade(mysqlCon);
        GraphFacade graphFacade = new GraphFacade(graphCon);
        GraphWebController.SetFacade(graphFacade);
        MySQLWebController.SetFacade(mysqlFacade);
        SpringApplication.run(Application.class, args);
    }
}
