package com.movies.country.Controllers;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.movies.country.API.Dao;
import com.movies.country.DB.CountryDatabase;
import com.movies.country.Modal.CountryModal;
import com.movies.country.Models.CountryDetails;

import java.util.List;

public class CountryRepo {

    private Dao dao;
    private List<CountryModal> allCountries;

    public CountryRepo(Application application) {
        CountryDatabase database = CountryDatabase.getInstance(application);
        dao = database.Dao();
        allCountries = dao.getAllCountries();
    }

    //method to insert the data to our database.
    public void insert(CountryModal model) {
        new InsertCountryAsyncTask(dao).execute(model);
    }

    //method to delete all the countries list.
    public void deleteAllCountries() {
        new DeleteAllCountriesAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public List<CountryModal> getAllCountries() {
        return allCountries;
    }

    // creating an async task method to insert new country list
    private static class InsertCountryAsyncTask extends AsyncTask<CountryModal, Void, Void> {
        private Dao dao;

        private InsertCountryAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CountryModal... countryDetails) {
            // below line is use to insert our modal in dao.
            dao.insert(countryDetails[0]);
            return null;
        }

    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllCountriesAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllCountriesAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllCountries();
            return null;
        }
    }

}

