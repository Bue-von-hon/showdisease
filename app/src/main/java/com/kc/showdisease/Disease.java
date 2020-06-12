package com.kc.showdisease;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Disease {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "latitude")
    private double latitude;
    @ColumnInfo(name = "longitude")
    private double longitude;
    @ColumnInfo(name = "info")
    private String info;
    @ColumnInfo(name = "location")
    private String location;

    public Disease(String name, double latitude, double longitude, String info, String location) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.info = info;
        this.location = location;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
