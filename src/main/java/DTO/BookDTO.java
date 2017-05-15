/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Entity.City;
import java.util.List;

/**
 *
 * @author Andreas
 */
public class BookDTO {
    
    private String authorName;
    private String title;
    private List<City> cities;

    public BookDTO(String authorName, String title, List<City> cities) {
        this.authorName = authorName;
        this.title = title;
        this.cities = cities;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    
}
