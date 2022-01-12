package com.movies.country.DB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.movies.country.API.Dao;
import com.movies.country.Controllers.DataConverter;
import com.movies.country.Modal.CountryModal;
import com.movies.country.Models.CountryDetails;

// adding annotation for our database entities and db version.
@Database(entities = {CountryModal.class}, version = 1)
@TypeConverters(DataConverter.class)
public abstract class CountryDatabase extends RoomDatabase {

    private static CountryDatabase instance;

    public abstract Dao Dao();

    public static synchronized CountryDatabase getInstance(Context context) {

        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            CountryDatabase.class, "country_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback).allowMainThreadQueries()
                            .build();
        }
        return instance;
    }

    // below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(CountryDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
