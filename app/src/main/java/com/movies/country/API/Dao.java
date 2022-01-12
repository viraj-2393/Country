package com.movies.country.API;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.movies.country.Controllers.DataConverter;
import com.movies.country.Modal.CountryModal;
import com.movies.country.Models.CountryDetails;

import java.util.List;
@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(CountryModal model);

    @Query("DELETE FROM country_table")
    void deleteAllCountries();

    @Query("SELECT * FROM country_table")
    List<CountryModal>  getAllCountries();
}
