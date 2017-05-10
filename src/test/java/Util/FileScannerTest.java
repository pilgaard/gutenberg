/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.core.IsEqual;
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
    private static File file;
    private static ArrayList<String> words;

    public FileScannerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        String basePath = new File("").getAbsolutePath();
        String path = basePath + "/testFiles/Book.txt";
        file = new File(path);
        
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

    /**
     * Test of FetchAllBooks method, of class FileScanner.
     */
    @Test
    public void testCapWords() {
        List<String> words = fs.findCapWords(file);
        assertThat(words.size(), is(58));
    }

    @Test
    public void testFindCities() {
        ArrayList<String> found = fs.findCities(words);
        List<String> actually = Arrays.asList("Copenhagen", "Haslev", "London", "New York");
        assertTrue(actually.containsAll(found));
    }

    @Test
    public void testTitle() {
        String title = fs.findTitle(file);
        String answer = "The King James Bible";
        assertThat(title, is(answer));
    }

    @Test
    public void testAuthorName() {
        String author = fs.findAuthor(file);
        String answer = "Abraham Lincoln";
        assertThat(author, is(answer));
    }
}
