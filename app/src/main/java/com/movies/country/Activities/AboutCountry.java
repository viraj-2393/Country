package com.movies.country.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.movies.country.R;

public class AboutCountry extends AppCompatActivity {
    WebView webView;
    TextView region,sub_region,borders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_country);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        webView = findViewById(R.id.map_holder);
        region = findViewById(R.id.region);
        sub_region = findViewById(R.id.sub_region);
        borders = findViewById(R.id.borders);

        //get all the data from intent extras
        String country_on_map = getIntent().getStringExtra("map_url");
        String country_region = getIntent().getStringExtra("region");
        String country_sub_region = getIntent().getStringExtra("sub_region");
        String country_borders = getIntent().getStringExtra("borders");
        //--------

        //set all the data
        region.setText("Region: "+country_region);
        sub_region.setText("Sub-Region: "+country_sub_region);
        borders.setText("Borders: "+country_borders);
        webView.loadUrl(country_on_map);
        //----------------

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                return false;
            }
        });
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}