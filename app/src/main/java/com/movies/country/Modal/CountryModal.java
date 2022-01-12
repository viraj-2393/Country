package com.movies.country.Modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.movies.country.Controllers.DataConverter;
import com.movies.country.Models.CountryDetails;

import java.util.List;

@Entity(tableName = "country_table")
public class CountryModal {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "country_list")
    //@TypeConverters(DataConverter.class)
    String countryDetails;

    public CountryModal(String countryDetails){
        this.countryDetails = countryDetails;
    }
    public void setId(int id){
        this.id =id;
    }
    public int getId(){
       return this.id;
    }

//    public CountryModal(List<CountryDetails> countryDetails,int id){
//        this.countryDetails = countryDetails;
//        this.id = id;
//    }

    public void setCountryDetails(String countryDetails){
        this.countryDetails = countryDetails;
    }

    public String getCountryDetails(){
        return this.countryDetails;
    }


}
