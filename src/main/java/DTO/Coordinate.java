/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Andreas
 */
public class Coordinate {
    
    private Long longitude;
    private Long latitude;

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Coordinate(Long longitude, Long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    
}
