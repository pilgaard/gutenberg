/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Andreas
 */
public class GraphFacade {
    
    private GraphConnector connector;
    
    public GraphFacade(GraphConnector con){
        this.connector = con;
    }
}
