package com.movies.country;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.movies.country.API.CountryAPI;
import com.movies.country.Adapter.CountryAdapter;
import com.movies.country.Controllers.DataConverter;
import com.movies.country.Modal.CountryModal;
import com.movies.country.Modal.ViewModal;
import com.movies.country.Models.CountryDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    LinearLayout trash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trash = findViewById(R.id.trash);
        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearTrash();
            }
        });

        if(isNetworkAvailable()) {
            getCountryInfo();
        }
        else{
            dataWithoutNetwork();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getCountryInfo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/v3.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryAPI service = retrofit.create(CountryAPI.class);

        Call<List<CountryDetails>> call = service.getCountryDetails();
        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "",
                getResources().getString(R.string.loading), true);
        dialog.setCancelable(false);
        call.enqueue(new Callback<List<CountryDetails>>() {

            @Override
            public void onResponse(Call<List<CountryDetails>> call, Response<List<CountryDetails>> response) {
                List<CountryDetails> serverResponse = response.body();
                ViewModal viewModal = new ViewModal(getApplication());
                List<CountryModal> countryModals = viewModal.getAllCountries();
                if(countryModals.size() == 0) {
                    CountryModal countryModal = new CountryModal(new DataConverter().fromOptionValuesList(serverResponse));
                    viewModal.insert(countryModal);
                }
                dialog.dismiss();
                if (response.isSuccessful() && response.body() != null){
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.country_list_view);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
                    linearLayoutManager.setReverseLayout(false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    CountryAdapter customAdapter = new CountryAdapter(serverResponse, getApplicationContext(),MainActivity.this);
                    recyclerView.setAdapter(customAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CountryDetails>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }

        });
    }

    private void dataWithoutNetwork(){
        ViewModal viewModal = new ViewModal(getApplication());
        List<CountryModal> countryModals = viewModal.getAllCountries();
        if(countryModals != null && countryModals.size() > 0) {
            List<CountryDetails> countryDetails = new DataConverter().toOptionValuesList(countryModals.get(0).getCountryDetails());

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.country_list_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
            linearLayoutManager.setReverseLayout(false);
            recyclerView.setLayoutManager(linearLayoutManager);
            CountryAdapter customAdapter = new CountryAdapter(countryDetails, getApplicationContext(), MainActivity.this);
            recyclerView.setAdapter(customAdapter);
        }
        else{
            Toast.makeText(getApplicationContext(),"Either the internet is down or something else went wrong!",Toast.LENGTH_LONG).show();
        }
    }

    private void clearTrash(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm Deletion..!!!");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.trash);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Are you sure,You want to delete?");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                ViewModal viewModal = new ViewModal(getApplication());
                List<CountryModal> countryModals = viewModal.getAllCountries();
                if(countryModals != null && countryModals.size() > 0) {
                    viewModal.deleteAllCountries();
                    Toast.makeText(getApplicationContext(), "All details deleted successfully!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "There's Nothing to delete!", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}