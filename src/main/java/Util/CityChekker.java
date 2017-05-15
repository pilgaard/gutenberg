/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DTO.BookDTO;
import DTO.CityDTO;
import Entity.Book;
import Entity.City;
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
            String author = fileScanner.findAuthor(file);
            String title = fileScanner.findTitle(file);
            ArrayList<String> capWords = fileScanner.findCapWords(file);
            ArrayList<CityDTO> city = findCities(capWords);
            ArrayList<Long> cityId = new ArrayList();
            for (CityDTO cityDTO : city) {
                cityId.add(cityDTO.getId());
            }
            BookDTO b = new BookDTO(author, title, cityId);
        }
        return "done";
    }
    
    public static ArrayList<CityDTO> findCities(ArrayList<String> CapWords) {  // Array must be sorted.
        // skal erstates af den rigtige liste med alle byer
        String cities[] = {"Amsterdam", "Copenhagen", "Haslev", "London"};
        ArrayList<CityDTO> result = new ArrayList();
        CityDTO cd = new CityDTO();
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
                    cd = new CityDTO(word);
                    result.add(cd);
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
