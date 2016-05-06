package com.foodfinder;

import java.io.Serializable;

public class College implements Serializable {

    public String name, city, country, state;
    public int id, image, zipCode;
    public double longitude, lattitude;

    public College(String name, int id, int image, int zipCode, String city, String country, String state, double longitude, double lattitude) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.state = state;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

}
