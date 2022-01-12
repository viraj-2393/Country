package com.movies.country.API;

import com.movies.country.Models.CountryDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryAPI {
    @GET("region/asia")
    Call<List<CountryDetails>> getCountryDetails();
}
