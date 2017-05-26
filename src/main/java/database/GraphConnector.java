/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.Map;
import org.neo4j.driver.v1.*;
/**
 *
 * @author Andreas
 */
public class GraphConnector {
    private static Driver driver;
    private Map<String, String> env = System.getenv();
    private static final String ip = "bolt://localhost";
    private static final String port = "7687";
    private static final String travis_port = "7474";
    private static final String username = "neo4j";
    private static final String password = "class";
    private static final String travis_password = "neo4j";
    
    
    public GraphConnector(){
        if (env.get("TRAVIS") == null) {
            driver = GraphDatabase.driver(ip+":"+port, AuthTokens.basic(username, password));
        } else {
            driver = GraphDatabase.driver(ip+":"+travis_port, AuthTokens.basic(username, travis_password));
        }
    }
    
    public Driver GetDriver(){
        return driver;
    } 
}
