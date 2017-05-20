/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import org.neo4j.driver.v1.*;
/**
 *
 * @author Andreas
 */
public class GraphConnector {
    private static Driver driver;
    
    public GraphConnector(){
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "class")); //Change Password
    }
    
    public Driver GetDriver(){
        return driver;
    } 
}
