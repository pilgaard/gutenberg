/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.CityDTO;
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
public class GraphFacadeTest {
    private final GraphConnector gc;
    GraphFacade gf;
    public GraphFacadeTest() {
        gc = new GraphConnector();
        gf = new GraphFacade(gc);
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
        System.out.println("getCities");
        List<CityDTO> result = gf.GetCities();
        assertThat(result.size(), is(47847));
    }    
}
