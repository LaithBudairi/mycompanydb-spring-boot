package com.exalt.mycompany.dto;

import com.exalt.mycompany.model.Device;

import java.util.Calendar;

public class LocationDTO {

    private DeviceDTO device;
    private String name;
    private double longitude;
    private double latitude;
    private Calendar date;

    public DeviceDTO getDevice() {
        return device;
    }

    public void setDevice(DeviceDTO device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "device=" + device +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", date=" + date +
                '}';
    }
}
