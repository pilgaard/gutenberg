/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Entity.Book;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class CityChekker {

    private final String dir = System.getProperty("user.dir");

    public String scanFiles() {
        /*String basePath = new File("").getAbsolutePath();
        String path = basePath + "/zipFiles/*.txt";
        File f = new File(path);*/
        FileScanner fileScanner = new FileScanner();
        ArrayList<File> files = new ArrayList();
        for (File file : files) {
            String Author = fileScanner.findAuthor(file);
            String Title = fileScanner.findTitle(file);
            ArrayList<String> capWords = fileScanner.findCapWords(file);
            ArrayList<String> city = findCities(capWords);
            Book b = new Book();
            b.setAuthorName(Author);
            b.setTitle(Title);
            b.setCityNames(city);
        }
        return "done";
    }
    
    public static ArrayList<String> findCities(ArrayList<String> CapWords) {  // Array must be sorted.
        // skal erstates af den rigtige liste med alle byer
        String cities[] = {"Amsterdam", "Copenhagen", "Haslev", "London"};
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

    public ArrayList<String> findCitiesWithSpaces(ArrayList<String> CapWords) {
        String cities[] = {"New Amsterdam", "New Orleans", "New York", "Abu Dhabi", "Rio de Janeiro"};
        return null;
    }
}
