/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Emil
 */
public class CityDTO {
    
    private Long id;
    private Long Latitude;
    private Long Longitude;
    private String cityName;

    public CityDTO(Long id, Long Latitude, Long Longitude, String cityName) {
        this.id = id;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.cityName = cityName;
    }

    public CityDTO(String cityName) {
        this.cityName = cityName;
    }

    public CityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLatitude() {
        return Latitude;
    }

    public void setLatitude(Long Latitude) {
        this.Latitude = Latitude;
    }

    public Long getLongitude() {
        return Longitude;
    }

    public void setLongitude(Long Longitude) {
        this.Longitude = Longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
