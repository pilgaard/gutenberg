/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import DTO.CityDTO;
import DTO.Coordinate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
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
public class MySQLDBFacadeTest {
    private final MySQLConnector connector;
    MySQLDBFacade sqlFacade;
    public MySQLDBFacadeTest() {
        connector = new MySQLConnector("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/gutenberg", "root", "root");
        sqlFacade= new MySQLDBFacade(connector);
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    /**
     * Test of InsertBooksInDB method, of class MySQLDBFacade.
     */
    @Test
    public void testInsertBooksInDB() throws Exception {
        System.out.println("InsertBooksInDB");
        List<BookDTO> books = null;
        MySQLDBFacade instance = null;
        instance.InsertBooksInDB(books);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetCities method, of class MySQLDBFacade.
     */
    @Test
    public void testGetCities() throws Exception {
        ArrayList<CityDTO> expResult = sqlFacade.GetCities();
        assertThat(expResult.size(), is(47847));
    }

    /**
     * Test of GetBooksByCity method, of class MySQLDBFacade.
     */
    @Test
    public void testGetBooksByCity() {
        System.out.println("GetBooksByCity");
        String cityName = "Byron";
        MySQLDBFacade instance = null;
        List<BookDTO> expResult = null;
        List<BookDTO> result = instance.GetBooksByCity(cityName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetCitiesByBookTitle method, of class MySQLDBFacade.
     */
    @Test
    public void testGetCitiesByBookTitle() throws SQLException {
        String bookTitle = "Byron";
        List<Coordinate> expResult = sqlFacade.GetCitiesByBookTitle(bookTitle);
        assertThat(expResult.size(), is(478));
    }

    /**
     * Test of GetGeoLocationByCity method, of class MySQLDBFacade.
     */
    @Test
    public void testGetGeoLocationByCity() {
        System.out.println("GetGeoLocationByCity");
        CityDTO city = null;
        MySQLDBFacade instance = null;
        HashMap<Long, Long> expResult = null;
        //HashMap<Long, Long> result = instance.GetGeoLocationByCity(city);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetBooksByAuthorName method, of class MySQLDBFacade.
     */
    @Test
    public void testGetBooksByAuthorName() throws SQLException {
        System.out.println("GetBooksByAuthorName");
        String authorName = "William Shakespeare";
        
        List<BookDTO> result = sqlFacade.GetBooksByAuthorName(authorName);
        assertThat(result.size(), is(22));
    }

    /**
     * Test of GetGeoLocationByBook method, of class MySQLDBFacade.
     */
    @Test
    public void testGetGeoLocationByBook() {
        System.out.println("GetGeoLocationByBook");
        BookDTO book = null;
        MySQLDBFacade instance = null;
        HashMap<Long, Long> expResult = null;
        //HashMap<Long, Long> result = instance.GetGeoLocationByBook(book);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetBooksByGeoLocation method, of class MySQLDBFacade.
     */
    @Test
    public void testGetBooksByGeoLocation() throws SQLException {
        System.out.println("GetBooksByGeoLocation");
        Long latitude = null;
        Long longitude = null;
        MySQLDBFacade instance = null;
        List<BookDTO> expResult = null;
        List<BookDTO> result = instance.GetBooksByGeoLocation(latitude, longitude);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
