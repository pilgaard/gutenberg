/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Emil
 */
public class CityDTO implements Serializable{
    
    private Long id;
    private BigDecimal Latitude;
    private BigDecimal Longitude;
    private String cityName;

    public CityDTO(Long id, BigDecimal Latitude, BigDecimal Longitude, String cityName) {
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

    public BigDecimal getLatitude() {
        return Latitude;
    }

    public void setLatitude(BigDecimal Latitude) {
        this.Latitude = Latitude;
    }

    public BigDecimal getLongitude() {
        return Longitude;
    }

    public void setLongitude(BigDecimal Longitude) {
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
