/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import dto.BookDTO;
import dto.CityDTO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utillity.CityChekker;
import static org.junit.Assert.*;

/**
 *
 * @author Emil
 */
public class CityChekkerTest {
    private String basePath = new File("").getAbsolutePath();
    private String path = basePath + "/testFiles";
    private List<CityDTO> cities = new ArrayList();
    private CityChekker cc = new CityChekker();
    private static List<String> words;
    
    public CityChekkerTest() {
        cities.add(new CityDTO("Copenhagen"));
        cities.add(new CityDTO("Haslev"));
        cities.add(new CityDTO("London"));
        cities.add(new CityDTO("New York"));   
    }
    
    @BeforeClass
    public static void setUpClass() {
        words = new ArrayList();
        words.add("The");
        words.add("First");
        words.add("Book");
        words.add("Mose");
        words.add("Called");
        words.add("Genesis");
        words.add("In");
        words.add("Haslev");
        words.add("Copenhagen");
        words.add("Their");
        words.add("London");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFindCities() {
        List<CityDTO> found = cc.findCities(words,cities);
        assertTrue(cities.containsAll(found));
    }
    
    
    @Test
    public void testScanFiles() {
        List<CityDTO> cities = new ArrayList();
        cities.add(new CityDTO("Copenhagen"));
        cities.add(new CityDTO("Haslev"));
        cities.add(new CityDTO("London"));
        cities.add(new CityDTO("New York"));
        System.out.println(path);
        List<BookDTO> books = cc.scanFiles(cities, path);
        
        for (BookDTO book : books) {
            System.out.println("book = " + book);    
        }  
    }
}
