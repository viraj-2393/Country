package com.movies.country.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.movies.country.Activities.AboutCountry;
import com.movies.country.Models.CountryDetails;
import com.movies.country.Models.Name;
import com.movies.country.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter {
   private final Activity activity;
   private List<CountryDetails> cds;
   private Context ctx;
   // private OnFeedItemClickListener onFeedItemClickListener;

    public CountryAdapter(List<CountryDetails> cds, Context ctx, Activity activity) {
        this.cds = cds;
        this.ctx = ctx;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.cardview_country,parent,false);
        CountryViewHolder CountryViewHolder=new CountryViewHolder(view);
        return CountryViewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CountryViewHolder)holder).bindView(cds.get(position));
        ((CountryViewHolder) holder).see_more.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AboutCountry.class);

                String border;
                if(cds.get(position).getBorders() != null) {
                    List<String> borders_arr = cds.get(position).getBorders();
                    border = String.join(",", borders_arr);
                }
                else {border = "Island Country";}

                intent.putExtra("position",position);
                intent.putExtra("map_url",cds.get(position).getMaps().getGoogleMaps());
                intent.putExtra("region",cds.get(position).getRegion());
                intent.putExtra("sub_region",cds.get(position).getSubregion());
                intent.putExtra("borders",border);
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return cds.size();
    }

    public interface OnFeedItemClickListener {
        void onEditOffer(View v, int position);
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder
    {

        ImageView flagImage;
        AppCompatButton see_more;
        TextView country_name;
        TextView country_capital;
        TextView population;

        CountryViewHolder(View itemView) {
            super(itemView);
            flagImage = itemView.findViewById(R.id.country_flag);
            see_more = itemView.findViewById(R.id.see_more);
            country_name = itemView.findViewById(R.id.country_name);
            country_capital = itemView.findViewById(R.id.country_capital);
            population = itemView.findViewById(R.id.country_population);
        }
        void bindView(CountryDetails cds)
        {
            Picasso.get().load(cds.getFlags().getPng()).into(flagImage);
            country_name.setText("Country: "+cds.getName().getCommon());
            if(cds.getCapital() != null)country_capital.setText("Capital: "+cds.getCapital().get(0));
            population.setText("Population: "+cds.getPopulation());
        }
    }
}
