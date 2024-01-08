package com.serkanyilmaz.finaldemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.serkanyilmaz.finaldemo.data.entity.Urunler;
import com.serkanyilmaz.finaldemo.databinding.ShoppingCardTasarimBinding;
import com.serkanyilmaz.finaldemo.ui.fragment.AnasayfaFragment;
import com.serkanyilmaz.finaldemo.ui.fragment.ShoppingAnasayfaFragmentDirections;

import java.util.List;

public class UrunlerAdapter extends RecyclerView.Adapter<UrunlerAdapter.shoppingCardTasarimTutucu>{
    private Context mContext;
    private List<Urunler> urunlerList;

    public UrunlerAdapter(Context mContext, List<Urunler> urunlerList) {
        this.mContext = mContext;
        this.urunlerList = urunlerList;
    }

    public class shoppingCardTasarimTutucu extends RecyclerView.ViewHolder{
        private ShoppingCardTasarimBinding tasarim;
        public shoppingCardTasarimTutucu(ShoppingCardTasarimBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }


    @NonNull
    @Override
    public shoppingCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShoppingCardTasarimBinding binding = ShoppingCardTasarimBinding.
                inflate(LayoutInflater.from(mContext), parent, false);
        return new shoppingCardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull shoppingCardTasarimTutucu holder, int position) {
        Urunler urun = urunlerList.get(position);
        ShoppingCardTasarimBinding t = holder.tasarim;

        t.imageViewUrun.setImageResource(mContext.getResources().
                getIdentifier(urun.getResim(), "drawable", mContext.getPackageName()));

        t.textViewUrunBaslik.setText(urun.getAd());
        t.textViewUrunAciklama.setText(urun.getAciklama());
        t.textViewUrunFiyat.setText(urun.getFiyat() + "TL");

        t.cardViewUrun.setOnClickListener(view -> {
            ShoppingAnasayfaFragmentDirections.ShoppingDetayGecis gecis =
                    ShoppingAnasayfaFragmentDirections.shoppingDetayGecis(urun);
            Navigation.findNavController(view).navigate(gecis);
        });

    }

    @Override
    public int getItemCount() {
        return urunlerList.size();
    }
}
