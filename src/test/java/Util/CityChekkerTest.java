/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DTO.CityDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emil
 */
public class CityChekkerTest {
    
    CityChekker cc = new CityChekker();
    private static ArrayList<String> words;
    
    public CityChekkerTest() {
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
        ArrayList<CityDTO> found = cc.findCities(words);
        List<String> actually = Arrays.asList("Copenhagen", "Haslev", "London", "New York");
        assertTrue(actually.containsAll(found));
    }
}
