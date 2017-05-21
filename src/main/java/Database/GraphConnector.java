/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.Map;
import org.neo4j.driver.v1.*;
/**
 *
 * @author Andreas
 */
public class GraphConnector {
    private static Driver driver;
    private Map<String, String> env = System.getenv();
    
    public GraphConnector(){
        if (env.get("TRAVIS") == "true") {
            for (int i = 0; i < 10; i++) {
                System.out.println(env.get("TRAVIS") == "true");
            }
            driver = GraphDatabase.driver("bolt://localhost:7474", AuthTokens.basic("neo4j", "class"));
        } else {
            for (int i = 0; i < 10; i++) {
                System.out.println(env.get("TRAVIS") == "true");
            }
            driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "class"));
        }
    }
    
    public Driver GetDriver(){
        return driver;
    } 
}
