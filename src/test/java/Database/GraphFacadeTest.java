/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import DTO.CityDTO;
import DTO.Coordinate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class GraphFacadeTest {

    private GraphConnector gc;
    private GraphFacade facade;
    private CityDTO city;
    private BookDTO book;
    private List<BookDTO> books;
    private Map<String, String> env = System.getenv();

    public GraphFacadeTest() {
        if (env.get("TRAVIS") != "true") {
        gc = new GraphConnector();
        facade = new GraphFacade(gc);
        }
        city = new CityDTO(3206285L, new BigDecimal(48.8168D), new BigDecimal(9.5769D), "Urbach");
        List<Long> cities = new ArrayList();
        cities.add(city.getId());
        book = new BookDTO("Gustav Just", "Life of Luther", cities);
        books = new ArrayList();
        books.add(book);
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
     * Test of getCities method, of class GraphFacade.
     */
    @Test
    public void testGetCities() {
        if (env.get("TRAVIS") ==null) {
            List<CityDTO> result = facade.GetCities();
            assertThat(result.size(), is(47847));
        }
    }

    /**
     * Test of GetBooksByCity method, of class GraphFacade.
     */
    @Test
    public void testGetBooksByCity() {
        if (env.get("TRAVIS") ==null) {
            List<BookDTO> result = facade.GetBooksByCity(city.getCityName());
            assertThat(result.size(), is(5));
        }
    }

    /**
     * Test of GetCitiesByBookTitle method, of class GraphFacade.
     */
    @Test
    public void testGetCitiesByBookTitle() throws Exception {
        if (env.get("TRAVIS") ==null) {
            List<Coordinate> result = facade.GetCitiesByBookTitle(book.getTitle());
            assertThat(result.size(), is(97));
        }
    }

    /**
     * Test of GetBooksByAuthorName method, of class GraphFacade.
     */
    @Test
    public void testGetBooksByAuthorName() throws Exception {
        if (env.get("TRAVIS") ==null) {
            List<BookDTO> result = facade.GetBooksByAuthorName(book.getAuthorName());
            assertThat(result.size(), is(1));
        }
    }

    /**
     * Test of GetGeoLocationByBook method, of class GraphFacade.
     */
    @Test
    public void testGetGeoLocationByBook() throws Exception {
        if (env.get("TRAVIS") ==null) {
            List<Coordinate> result = facade.GetGeoLocationByBook(books);
            Coordinate answer = new Coordinate(city.getLongitude(), city.getLatitude());
            assertThat(result.get(0).toString(), is(answer.toString()));
        }
    }

    /**
     * Test of GetBooksByGeoLocation method, of class GraphFacade.
     */
    @Test
    public void testGetBooksByGeoLocation() throws Exception {
        if (env.get("TRAVIS") == null) {
            List<BookDTO> result = facade.GetBooksByGeoLocation(city.getLatitude(), city.getLongitude());
            assertThat(result.size(), is(852));
        }
    }
}
