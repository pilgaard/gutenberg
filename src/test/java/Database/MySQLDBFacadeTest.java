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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Emil
 */
public class MySQLDBFacadeTest {

    private final MySQLConnector connector;
    private MySQLDBFacade facade;
    private CityDTO city;
    private BookDTO book;
    private List<BookDTO> books;

    public MySQLDBFacadeTest() {
        connector = new MySQLConnector();
        facade = new MySQLDBFacade(connector);
        double d1 = Math.round((48.8168D * 100000000));
        double d2 = Math.round((9.5769D * 100000000));
        BigDecimal latitude = new BigDecimal(d1 / 100000000);
        BigDecimal longitude = new BigDecimal(d2 / 100000000);
        city = new CityDTO(3206285L, latitude, longitude, "Urbach");
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
     * Test of GetCities method, of class MySQLDBFacade.
     */
    @Test
    public void testGetCities() throws SQLException {
        List<CityDTO> expResult = facade.GetCities();
        assertThat(expResult.size(), is(47847));
    }

    /**
     * Test of GetBooksByCity method, of class MySQLDBFacade.
     */
    @Test
    public void testGetBooksByCity() throws SQLException {
        List<BookDTO> result = facade.GetBooksByCity(city.getCityName());
        assertThat(result.size(), is(5));
    }

    /**
     * Test of GetCitiesByBookTitle method, of class MySQLDBFacade.
     */
    @Test
    public void testGetCitiesByBookTitle() throws SQLException {
        List<Coordinate> result = facade.GetCitiesByBookTitle(book.getTitle());
        assertThat(result.size(), is(97));
    }

    /**
     * Test of GetBooksByAuthorName method, of class MySQLDBFacade.
     */
    @Test
    public void testGetBooksByAuthorName() throws SQLException {
        List<BookDTO> result = facade.GetBooksByAuthorName(book.getAuthorName());
        assertThat(result.size(), is(1));
    }

    /**
     * Test of GetGeoLocationByBook method, of class MySQLDBFacade.
     */
    @Test
    public void testGetGeoLocationByBook() throws SQLException {
        List<Coordinate> result = facade.GetGeoLocationByBook(books);
        Coordinate answer = new Coordinate(new BigDecimal("9.57690000"), new BigDecimal("48.81680000"));
        assertThat(result.get(0).toString(), is(answer.toString()));
    }

    /**
     * Test of GetBooksByGeoLocation method, of class MySQLDBFacade.
     */
    @Test
    public void testGetBooksByGeoLocation() throws SQLException {
        List<BookDTO> result = facade.GetBooksByGeoLocation(city.getLatitude(), city.getLongitude());
        assertThat(result.size(), is(1768));
    }
}