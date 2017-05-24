/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.FileScanner;
import static org.junit.Assert.*;

/**
 *
 * @author Emil
 */
public class FileScannerTest {

    FileScanner fs = new FileScanner();
    private static File file;

    public FileScannerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        String basePath = new File("").getAbsolutePath();
        String path = basePath + "/testFiles/Book.txt";
        file = new File(path);
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
        assertThat(words.size(), is(42));
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
    
    @Test
    public void nonBookTest() {
        String basePath = new File("").getAbsolutePath();
        String path = basePath + "/testFiles/5Cap.txt";
        File f = new File(path);
        String author = fs.findAuthor(f);
        String answer = null;
        assertThat(author, is(answer));
        String title = fs.findTitle(f);
        answer = null;
        assertThat(title, is(answer));
    } 
}
