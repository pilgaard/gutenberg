/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Andreas
 */
public class FileScanner {

    public List<String> findCapWords(File f) {
        ArrayList<String> words = new ArrayList();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern p = Pattern.compile("\\b([A-Z]\\w*)\\b");
                Matcher matcher = p.matcher(line);
                while (matcher.find()) {
                    if (!words.contains(matcher.group())) {
                        words.add(matcher.group());
                    } 
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
