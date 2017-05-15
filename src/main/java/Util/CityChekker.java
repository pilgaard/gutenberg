/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DTO.BookDTO;
import DTO.CityDTO;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class CityChekker {

    private final String dir = System.getProperty("user.dir");
    private String path = "/Users/Emil/examproject/gutenberg/testFiles";
    
    public void listF(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listF(file.getAbsolutePath(), files);
            }
        }
    }

    public ArrayList<BookDTO> scanFiles() {
        ArrayList<File> files = new ArrayList();
        listF(path, files);
        FileScanner fileScanner = new FileScanner();
        ArrayList<BookDTO> books = new ArrayList();
        BookDTO b;
        for (File file : files) {
            String title = fileScanner.findTitle(file);
            if (title != null) {
                String author = fileScanner.findAuthor(file);
                ArrayList<String> capWords = fileScanner.findCapWords(file);
                ArrayList<CityDTO> city = findCities(capWords);
                ArrayList<Long> cityId = new ArrayList();
                for (CityDTO cityDTO : city) {
                    cityId.add(cityDTO.getId());
                }
                b = new BookDTO(author, title, cityId);
                books.add(b);
            }
        }
        return books;
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
