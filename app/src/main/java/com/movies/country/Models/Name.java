package com.movies.country.Models;

import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Name {

    @SerializedName("common")
    @Expose
    private String common;
    @SerializedName("official")
    @Expose
    private String official;

    public Name(){}


    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }


}