/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Entity.Book;
import java.io.BufferedReader;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Andreas
 */
public class FileScanner {

    private final String dir = System.getProperty("user.dir");
    private final String booksFileLocation = "";

    // Read Books files. When City name is found, add to a list (if it is not already found). 
    // Create Book objects, and fill with found city names. 
    // Maybe split into 2 methods instead of one.
    public List<Book> FetchAllBooks() {
        return null;
    }

    public List<String> findCapWords(File f) {
        List<String> words = new ArrayList();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern p = Pattern.compile("\\b([A-Z]\\w*)\\b");
                Matcher matcher = p.matcher(line);
                while (matcher.find()) {
                    words.add(matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return words;
    }

    public ArrayList<String> findCities(ArrayList<String> CapWords) {  // Array must be sorted.
        // skal erstates af den rigtige liste med alle byer
        String cities[] = {"Amsterdam", "Copenhagen", "Haslev", "London", "New York"};
        ArrayList<String> result = new ArrayList();

        while (!CapWords.isEmpty()) {
            String word = CapWords.get(0);
            int low = 0;
            int high = cities.length - 1;
            int mid;
            boolean found = false;
            
            while (low <= high && !found) {
                mid = (low + high) / 2;

                if (cities[mid].compareTo(word) < 0) {
                    low = mid + 1;
                } else if (cities[mid].compareTo(word) > 0) {
                    high = mid - 1;
                } else {
                    result.add(word);
                    found = true;
                }
            }
            CapWords.remove(0);
        }
        return result;
    }

    public String findTitle(File f) {
        try {
            String title = "";
            BufferedReader reader = null;

            reader = new BufferedReader(new FileReader(f));
            Pattern pattern = Pattern.compile("(?<=Title: ).*");
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null && !found) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    title = matcher.group().toString();
                    return title;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String findAuthor(File f) {
        try {
            String author = "";
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(f));
            Pattern pattern = Pattern.compile("(?<=Author: ).*");
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null && found == false) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    author = matcher.group().toString();
                    return author;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FileScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
