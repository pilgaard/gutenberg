/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import dto.BookDTO;
import dto.CityDTO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public class CityChekker {
    
    public void listF(String directoryName, List<File> files) {
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

    public List<BookDTO> scanFiles(List<CityDTO> cities, String path) {
        ArrayList<File> files = new ArrayList();
        listF(path, files);
        FileScanner fileScanner = new FileScanner();
        ArrayList<BookDTO> books = new ArrayList();
        BookDTO b;
        for (File file : files) {
            System.out.println(file);
            String title = fileScanner.findTitle(file);
            if (title != null) {
                String author = fileScanner.findAuthor(file);
                List<String> capWords = fileScanner.findCapWords(file);
                List<CityDTO> city = findCities(capWords, cities);
                List<Long> cityId = new ArrayList();
                for (CityDTO cityDTO : city) {
                    cityId.add(cityDTO.getId());
                }
                b = new BookDTO(author, title, cityId);
                books.add(b);
            }
        }
        return books;
    }

    public List<CityDTO> findCities(List<String> CapWords, List<CityDTO> CityDTOs) {  // Array must be sorted.
        List<CityDTO> result = new ArrayList();
        while (!CapWords.isEmpty()) {
            String word = CapWords.get(0);
            int low = 0;
            int high = CityDTOs.size() - 1;
            int mid;
            boolean found = false;

            while (low <= high && !found) {
                mid = (low + high) / 2;
                if (CityDTOs.get(mid).getCityName().compareTo(word) < 0) {
                    low = mid + 1;
                } else if (CityDTOs.get(mid).getCityName().compareTo(word) > 0) {
                    high = mid - 1;
                } else {
                    result.add(CityDTOs.get(mid));
                    found = true;
                }
            }
            CapWords.remove(0);
        }
        return result;
    }
}
