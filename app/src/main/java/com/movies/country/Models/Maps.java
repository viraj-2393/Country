package com.movies.country.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Maps {

    @SerializedName("googleMaps")
    @Expose
    private String googleMaps;
    @SerializedName("openStreetMaps")
    @Expose
    private String openStreetMaps;

    public Maps(){}
    public String getGoogleMaps() {
        return googleMaps;
    }

    public void setGoogleMaps(String googleMaps) {
        this.googleMaps = googleMaps;
    }

    public String getOpenStreetMaps() {
        return openStreetMaps;
    }

    public void setOpenStreetMaps(String openStreetMaps) {
        this.openStreetMaps = openStreetMaps;
    }

}