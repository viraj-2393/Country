package com.movies.country.Models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class CountryDetails {

    @SerializedName("name")
    @Expose
    @Embedded
    private Name name;
    @SerializedName("independent")
    @Expose
    private Boolean independent;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("unMember")
    @Expose
    private Boolean unMember;

    @SerializedName("capital")
    @Expose
    @Embedded
    private List<String> capital = null;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;
    @SerializedName("latlng")
    @Expose
    @Embedded
    private List<Double> latlng = null;
    @SerializedName("landlocked")
    @Expose
    private Boolean landlocked;
    @SerializedName("borders")
    @Expose
    @Embedded
    private List<String> borders = null;
    @SerializedName("area")
    @Expose
    private Double area;
    @SerializedName("maps")
    @Expose
    @Embedded
    private Maps maps;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("flags")
    @Expose
    @Embedded
    private Flags flags;

    public CountryDetails(){}


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Boolean getIndependent() {
        return independent;
    }

    public void setIndependent(Boolean independent) {
        this.independent = independent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getUnMember() {
        return unMember;
    }

    public void setUnMember(Boolean unMember) {
        this.unMember = unMember;
    }


    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public Boolean getLandlocked() {
        return landlocked;
    }

    public void setLandlocked(Boolean landlocked) {
        this.landlocked = landlocked;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Maps getMaps() {
        return maps;
    }

    public void setMaps(Maps maps) {
        this.maps = maps;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

}



