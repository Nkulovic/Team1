package com.team1.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelID;

    private String name;
    private String description;
    private double longitude;
    private double latitude;
    private String location;



    public Long getHotelID() {
        return hotelID;
    }

    public Hotel() {

    }

    public Hotel(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.longitude = 0;
        this.latitude = 0;
    }

    public void setHotelID(Long hotelID) {
        this.hotelID = hotelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
