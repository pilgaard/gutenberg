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
    private List<Long> cityId;

    public BookDTO(String authorName, String title, List<Long> cityId) {
        this.authorName = authorName;
        this.title = title;
        this.cityId = cityId;
    }
    
    public BookDTO(){
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

    public List<Long> getCities() {
        return cityId;
    }

    public void setCities(List<Long> cities) {
        this.cityId = cities;
    }

    @Override
    public String toString() {
        return "BookDTO{" + "authorName=" + authorName + ", title=" + title + ", cityId=" + cityId + '}';
    }
    
    
    
}
