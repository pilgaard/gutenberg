/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import static org.hamcrest.CoreMatchers.is;
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
public class FileScannerTest {
    FileScanner fs = new FileScanner();
    
    public FileScannerTest() {
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
     * Test of FetchAllBooks method, of class FileScanner.
     */
    @Test
    public void testCapWords() {
        String basePath = new File("").getAbsolutePath();
        String path = basePath+"/testFiles/5Cap.txt";
        File f = new File(path);
        int words = fs.findCapWords(f);
        
        assertThat(words, is(5));
    }
    
    @Test
    public void testTitle() {
        String basePath = new File("").getAbsolutePath();
        String path = basePath+"/testFiles/Book.txt";
        File f = new File(path);
        String title = fs.findTitle(f);
        String answer = "The King James Bible";
        assertThat(title, is(answer));
    } 
    
    @Test
    public void testAuthorName() {
        String basePath = new File("").getAbsolutePath();
        String path = basePath+"/testFiles/Book.txt";
        File f = new File(path);
        String author = fs.findAuthor(f);
        String answer = "Abraham Lincoln";
        assertThat(author, is(answer));
    }
}