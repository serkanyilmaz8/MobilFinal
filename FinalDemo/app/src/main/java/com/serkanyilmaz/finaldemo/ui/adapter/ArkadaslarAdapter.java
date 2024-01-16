package com.serkanyilmaz.finaldemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.serkanyilmaz.finaldemo.data.entity.EvArkadaslari;
import com.serkanyilmaz.finaldemo.databinding.RoommateCardTasarimBinding;
import com.serkanyilmaz.finaldemo.ui.fragment.RommateAnasayfaFragmentDirections;

import java.util.List;

public class ArkadaslarAdapter extends RecyclerView.Adapter<ArkadaslarAdapter.RoommateCardTasarimTutucu>{
    private Context mContext;
    private List<EvArkadaslari> arkadaslarList;

    public ArkadaslarAdapter(Context mContext, List<EvArkadaslari> arkadaslarList) {
        this.mContext = mContext;
        this.arkadaslarList = arkadaslarList;
    }


    public class RoommateCardTasarimTutucu extends RecyclerView.ViewHolder{
        private RoommateCardTasarimBinding tasarim;
        public RoommateCardTasarimTutucu(RoommateCardTasarimBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }


    @NonNull
    @Override
    public RoommateCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RoommateCardTasarimBinding binding = RoommateCardTasarimBinding
                .inflate(LayoutInflater.from(mContext), parent, false);
        return new RoommateCardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoommateCardTasarimTutucu holder, int position) {
        EvArkadaslari arkadas = arkadaslarList.get(position);
        RoommateCardTasarimBinding t = holder.tasarim;

        t.imageViewArkadas.setImageResource(mContext.getResources().getIdentifier("person_img", "drawable", mContext.getPackageName()));
        t.textViewArkadasBaslik.setText(arkadas.getName());
        t.textViewArkadasAciklama.setText(arkadas.getDescription());

        t.cardViewArkadas.setOnClickListener(view -> {
            RommateAnasayfaFragmentDirections.RommateDetayGecis gecis =
                    RommateAnasayfaFragmentDirections.rommateDetayGecis(arkadas);
            Navigation.findNavController(view).navigate(gecis);
        });

    }

    @Override
    public int getItemCount() {
        return arkadaslarList.size();
    }

}
