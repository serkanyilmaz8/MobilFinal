package com.serkanyilmaz.finaldemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Sekmeler;
import com.serkanyilmaz.finaldemo.data.entity.Urunler;
import com.serkanyilmaz.finaldemo.databinding.FragmentPlacesAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentRommateAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentShoppingAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.SekmelerCardTasarimBinding;
import com.serkanyilmaz.finaldemo.databinding.ShoppingCardTasarimBinding;
import com.serkanyilmaz.finaldemo.ui.fragment.AnasayfaFragmentDirections;
import com.serkanyilmaz.finaldemo.ui.fragment.ShoppingAnasayfaFragmentDirections;

import java.util.List;

public class SekmelerAdapter extends RecyclerView.Adapter<SekmelerAdapter.sekmelerCardTasarimTutucu>{
    private Context mContext;
    private List<Sekmeler> sekmelerList;

    public SekmelerAdapter(Context mContext, List<Sekmeler> sekmelerList) {
        this.mContext = mContext;
        this.sekmelerList = sekmelerList;
    }


    public class sekmelerCardTasarimTutucu extends RecyclerView.ViewHolder{
        private SekmelerCardTasarimBinding tasarim;
        public sekmelerCardTasarimTutucu(SekmelerCardTasarimBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public sekmelerCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SekmelerCardTasarimBinding binding =
                SekmelerCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new sekmelerCardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull sekmelerCardTasarimTutucu holder, int position) {
        Sekmeler sekme = sekmelerList.get(position);
        SekmelerCardTasarimBinding t = holder.tasarim;

       t.imageViewSekme.setImageResource(
                mContext.getResources()
                        .getIdentifier(sekme.getSekmeResim(), "drawable", mContext.getPackageName()));
        t.textViewSekmeAd.setText(sekme.getSekmeAd());
        t.cardViewSekme.setOnClickListener(view -> {
            if (position == 0) {
                // Anasayfa'dan Shopping Fragment'a geçiş
                Navigation.findNavController(view).navigate(R.id.anasayfaShoppingAnasayfaGecis);
            } else if (position == 1) {
                // Anasayfa'dan Rommate Fragment'a geçiş
                Navigation.findNavController(view).navigate(R.id.anasayfaRommateAnasayfaGecis);
            } else {
                // Anasayfa'dan Places Fragment'a geçiş
                Navigation.findNavController(view).navigate(R.id.anasayfaPlacesAnasayfaGecis);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sekmelerList.size();
    }



}
