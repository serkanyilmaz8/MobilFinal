package com.serkanyilmaz.finaldemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Sekmeler;
import com.serkanyilmaz.finaldemo.data.entity.Yerler;
import com.serkanyilmaz.finaldemo.databinding.PlacesCardTasarimBinding;
import com.serkanyilmaz.finaldemo.databinding.SekmelerCardTasarimBinding;
import com.serkanyilmaz.finaldemo.ui.fragment.PlacesAnasayfaFragmentDirections;

import java.util.List;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.YerlerCardTasarimTutucu>{
    private Context mContext;
    private List<Yerler> yerlerList;

    public PlacesAdapter(Context mContext, List<Yerler> yerlerList) {
        this.mContext = mContext;
        this.yerlerList = yerlerList;
    }


    public class YerlerCardTasarimTutucu extends RecyclerView.ViewHolder{
        private PlacesCardTasarimBinding tasarim;
        public YerlerCardTasarimTutucu(@NonNull PlacesCardTasarimBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public YerlerCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlacesCardTasarimBinding binding =
                PlacesCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new PlacesAdapter.YerlerCardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull YerlerCardTasarimTutucu holder, int position) {
        Yerler yer = yerlerList.get(position);
        PlacesCardTasarimBinding t = holder.tasarim;

        t.imageViewPlace.setImageResource(
                mContext.getResources()
                        .getIdentifier(yer.getResim(), "drawable", mContext.getPackageName()));
        t.textViewPlaceAd.setText(yer.getAd());
        //t.textView2.setText(yer.getAciklama());
        t.cardViewPlace.setOnClickListener(view -> {
            PlacesAnasayfaFragmentDirections.ActionPlacesAnasayfaFragmentToPlacesDetayFragment gecis = PlacesAnasayfaFragmentDirections.actionPlacesAnasayfaFragmentToPlacesDetayFragment(yer);
            Navigation.findNavController(view).navigate(gecis);

        });
    }

    @Override
    public int getItemCount() {
        return yerlerList.size();
    }
}