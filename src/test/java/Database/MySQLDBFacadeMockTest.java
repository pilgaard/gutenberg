/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import DTO.BookDTO;
import DTO.Coordinate;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Andreas
 */
public class MySQLDBFacadeMockTest {
    
    private IDBFacade facade;

    @Before
    public void SetUp(){
        facade = mock(MySQLDBFacade.class);
    }
    
    @Test
    public void TestGetBooksByCity(){
        String title = "Book Title";
        
        List<BookDTO> books = new ArrayList<BookDTO>();
        books.add(new BookDTO());
        
        when(facade.GetBooksByCity(title)).thenReturn(books);
        assertThat(facade.GetBooksByCity(title), is(books)); 
    }
    
    @Test
    public void TestGetCitiesByBookTitle() throws SQLException{
        String title = "Book Title";
        
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate());
        
        when(facade.GetCitiesByBookTitle(title)).thenReturn(coordinates);
        assertThat(facade.GetCitiesByBookTitle(title), is(coordinates));
    }
    
    @Test
    public void TestGetBooksByAuthorName() throws SQLException{
        String author = "Author Name";
        
        List<BookDTO> books = new ArrayList<BookDTO>();
        books.add(new BookDTO());
        
        when(facade.GetBooksByAuthorName(author)).thenReturn(books);
        assertThat(facade.GetBooksByAuthorName(author), is(books));
    }
    
    @Test
    public void TestGetGeoLocationByBook() throws SQLException{
        ArrayList<BookDTO> books = new ArrayList<BookDTO>();
        books.add(new BookDTO());
        
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate());
        
        when(facade.GetGeoLocationByBook(books)).thenReturn(coordinates);
        assertThat(facade.GetGeoLocationByBook(books), is(coordinates));
    }
    
    @Test
    public void GetBooksByGeoLocation() throws SQLException{
        List<BookDTO> books = new ArrayList<BookDTO>();
        books.add(new BookDTO());
        
        BigDecimal longitude = new BigDecimal("1");
        BigDecimal latitude = new BigDecimal("1");
        
        when(facade.GetBooksByGeoLocation(latitude, longitude)).thenReturn(books);
        assertThat(facade.GetBooksByGeoLocation(latitude, longitude), is(books));
    }

}
