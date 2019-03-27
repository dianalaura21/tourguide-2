package com.example.julioparedes.mapa;

import com.google.android.gms.maps.model.LatLng;
public class SimpleGeofence {
    private double latitude;
    private double longitude;
    private float radius;
    private int id;
    private String nombre;

    public SimpleGeofence(double latitude, double longitude, float radius, int id,String nombre) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.id = id;
        this.nombre = nombre;
    }
    public double getLatitude() {
        return latitude;
    }
    public String getNombre() {
        return nombre;
    }
    public double getLongitude() {
        return longitude;
    }

    public float getRadius() {
        return radius;
    }

    public float getId() {
        return id;
    }

}

