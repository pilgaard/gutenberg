/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Util.CityChekker;
import java.util.*;

/**
 *
 * @author Andreas
 */
public class DBInitializer {
    public static void main(String[] args) {
        MySQLConnector msc = new MySQLConnector("", "", "", "");
        ArrayList<String> temp = new ArrayList<String>();
        MySQLDBFacade sql = new MySQLDBFacade(msc);
        CityChekker chek = new CityChekker();
        
    }
}
