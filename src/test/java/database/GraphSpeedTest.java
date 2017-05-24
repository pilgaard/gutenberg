/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import dto.BookDTO;
import dto.CityDTO;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author Emil
 */
public class GraphSpeedTest {

    private List<CityDTO> cities = Arrays.asList(new CityDTO(3206285L, new BigDecimal(48.8168D), new BigDecimal(9.5769D), "Urbach"),
            new CityDTO(2618425L, new BigDecimal(55.67594000D), new BigDecimal(12.56553000D), "Copenhagen"),
            new CityDTO(2759794L, new BigDecimal(52.37403000D), new BigDecimal(4.88969000D), "Amsterdam"),
            new CityDTO(292223L, new BigDecimal(25.06570000D), new BigDecimal(55.17128000D), "Dubai"),
            new CityDTO(2643743L, new BigDecimal(51.50853000D), new BigDecimal(-0.12574000D), "London"));
    private List<BookDTO> books = Arrays.asList(new BookDTO("William Shakespeare", "Romeo and Juliet", Arrays.asList(289433L, 298728L, 304081L, 570508L, 741240L)),
            new BookDTO("Hans Christian Andersen", "Pictures of Sweden", Arrays.asList(289433L, 455915L, 581671L, 587378L, 668732L)),
            new BookDTO("Charles Dickens", "Oliver Twist", Arrays.asList(668732L, 741240L, 749795L, 1002145L, 1152322L, 1153427L, 1167718L)),
            new BookDTO("George Washington", "State of the Union Addresses of George Washington", Arrays.asList(252601L, 361058L, 741240L, 1002145L, 1725919L)),
            new BookDTO("Unknown", "King Arthur and the Knights of the Round Table", Arrays.asList(281184L, 361058L, 668732L, 685707L, 741240L, 967476L)));
    private GraphFacade facade;
    private GraphConnector gc;
    private Map<String, String> env = System.getenv();

    public GraphSpeedTest() {
        if (env.get("TRAVIS") == null) {
            gc = new GraphConnector();
            facade = new GraphFacade(gc);
        }
    }

    @Test
    public void testGetCities() {
        if (env.get("TRAVIS") == null) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < cities.size(); i++) {
                facade.GetCities();
            }
            long time = System.currentTimeMillis() - start;
            System.out.println("Graph testGetCities: time = " + time + "milliseconds");
        }
    }

    @Test
    public void testGetBooksByCity() {
        if (env.get("TRAVIS") == null) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < cities.size(); i++) {
                CityDTO city = cities.get(i);
                facade.GetBooksByCity(city.getCityName());
            }
            long time = System.currentTimeMillis() - start;
            System.out.println("Graph testGetBooksByCity: time = " + time + "milliseconds");
        }
    }

    @Test
    public void testGetCitiesByBookTitle() {
        if (env.get("TRAVIS") == null) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < books.size(); i++) {
                BookDTO book = books.get(i);
                facade.GetCitiesByBookTitle(book.getTitle());
            }
            long time = System.currentTimeMillis() - start;
            System.out.println("Graph testGetCitiesByBookTitle: time = " + time + "milliseconds");
        }
    }

    @Test
    public void testGetBooksByGeoLocation() {
        if (env.get("TRAVIS") == null) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < cities.size(); i++) {
                CityDTO city = cities.get(i);
                facade.GetBooksByGeoLocation(city.getLatitude(), city.getLongitude());
            }
            long time = System.currentTimeMillis() - start;
            System.out.println("Graph testGetBooksByGeoLocation: time = " + time + "milliseconds");
        }
    }

    @Test
    public void testGetBooksByAuthorName() {
        if (env.get("TRAVIS") == null) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < books.size(); i++) {
                BookDTO book = books.get(i);
                facade.GetBooksByAuthorName(book.getAuthorName());
            }
            long time = System.currentTimeMillis() - start;
            System.out.println("Graph testGetBooksByAuthorName: time = " + time + "milliseconds");
        }
    }
}
