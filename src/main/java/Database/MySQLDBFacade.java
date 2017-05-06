/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import Util.FileScanner;
/**
 *
 * @author Andreas
 */
public class MySQLDBFacade {
    
    private MySQLConnector connector;
    
    public MySQLDBFacade(MySQLConnector con){
        this.connector = con;
    }
    
    public void AddCitiesToDB(FileScanner fileScanner){
        // Use FileScanner.FetchAllCities() - Add returned cities to Database.
    }
    
    public void AddBooksToDB(FileScanner fileScanner){
        // Use FileScanner.FetchAllBooks() - Add returned Books to Database.
    }
    
    /*
    public List<String> GetBookTitlesByCityName(String CityName){
        EntityManager em = connector.GetEM();
        List<String> cityNames = new ArrayList<>();
        try{
            em.getTransaction().begin();
            cityNames = em.createQuery("query").getResultList();
        }finally{
            em.close();
        }
        return cityNames;
    }
    */
}
