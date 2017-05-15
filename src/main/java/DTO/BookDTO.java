/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.List;

/**
 *
 * @author Andreas
 */
public class BookDTO {
    
    private String authorName;
    private String title;
    private List<String> cityNames;

    public BookDTO(String authorName, String title, List<String> cityNames) {
        this.authorName = authorName;
        this.title = title;
        this.cityNames = cityNames;
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

    public List<String> getCityNames() {
        return cityNames;
    }

    public void setCityNames(List<String> cityNames) {
        this.cityNames = cityNames;
    }
    
    
}
