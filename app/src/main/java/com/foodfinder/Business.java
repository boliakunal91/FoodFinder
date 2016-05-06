package com.foodfinder;

import java.io.Serializable;

/**
 * Created by Kunal on 12/3/2015.
 */
public class Business implements Serializable {

    final String name;// city;
    final String url;
    final String phone;
    final double longitude;
    final double latitude;
    final String address;
    final String postal;
    public Business(String name, String url, String phone, double latitude, double longitude, String address, String postal) {
        this.name = name;
        this.url = url;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.postal = postal;
        //this.city = city;
    }

    @Override
    public String toString() {
        return name;
    }
}
