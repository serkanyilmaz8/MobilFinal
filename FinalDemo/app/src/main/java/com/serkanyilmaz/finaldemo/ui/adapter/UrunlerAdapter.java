package com.serkanyilmaz.finaldemo.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.serkanyilmaz.finaldemo.data.entity.Urunler;
import com.serkanyilmaz.finaldemo.databinding.ShoppingCardTasarimBinding;
import com.serkanyilmaz.finaldemo.ui.fragment.AnasayfaFragment;
import com.serkanyilmaz.finaldemo.ui.fragment.ShoppingAnasayfaFragmentDirections;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.List;

public class UrunlerAdapter extends RecyclerView.Adapter<UrunlerAdapter.shoppingCardTasarimTutucu>{
    private Context mContext;
    private List<Urunler> urunlerList;

    private CollectionReference urunlerCollection;

    public UrunlerAdapter(Context mContext, List<Urunler> urunlerList, CollectionReference urunlerCollection) {
        this.mContext = mContext;
        this.urunlerList = urunlerList;
        this.urunlerCollection = urunlerCollection;
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

        String imagePath = urun.getResim();

        // Load and set the image to the ImageView
        if (imagePath.endsWith("jpg")) {
            // Load and set the image from the file path
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            t.imageViewUrun.setImageBitmap(bitmap);
        } else {
            // Load and set the image from the drawable resources
            int resID = mContext.getResources().getIdentifier(imagePath, "drawable", mContext.getPackageName());
            t.imageViewUrun.setImageResource(resID);

        }

        t.textViewUrunBaslik.setText(urun.getAd());
        t.textViewUrunAciklama.setText(urun.getAciklama());
        t.textViewUrunFiyat.setText(urun.getFiyat() + "TL");

        t.cardViewUrun.setOnClickListener(view -> {
            ShoppingAnasayfaFragmentDirections.ShoppingDetayGecis gecis =
                    ShoppingAnasayfaFragmentDirections.shoppingDetayGecis(urun);
            Navigation.findNavController(view).navigate(gecis);
            urunlerCollection.add(urun);
        });

    }

    @Override
    public int getItemCount() {
        return urunlerList.size();
    }
}
