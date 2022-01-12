package com.movies.country.Modal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.movies.country.Controllers.CountryRepo;
import com.movies.country.Models.CountryDetails;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    // creating a new variable for course repository.
    private CountryRepo repository;

    private List<CountryModal> allCountries;


    // constructor for our view modal.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new CountryRepo(application);
        allCountries = repository.getAllCountries();
    }

    // below method is use to insert the data to our repository.
    public void insert(CountryModal model) {
        repository.insert(model);
    }


    // below method is to delete all the courses in our list.
    public void deleteAllCountries() {
        repository.deleteAllCountries();
    }

    // below method is to get all the courses in our list.
    public List<CountryModal> getAllCountries() {
        return allCountries;
    }
}
