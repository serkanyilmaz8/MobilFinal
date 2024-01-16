package com.serkanyilmaz.finaldemo.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Urunler;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentShoppingDetayBinding;


public class ShoppingDetayFragment extends Fragment {

    private FragmentShoppingDetayBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShoppingDetayBinding.inflate(inflater, container, false);

        ShoppingDetayFragmentArgs bundle = ShoppingDetayFragmentArgs.fromBundle(getArguments());
        Urunler urun = bundle.getUrun();

        binding.toolbarShoppingDetay.setTitle(urun.getAd());
        String imagePath = urun.getResim();
        if (imagePath.endsWith("jpg")) {
            // Load and set the image from the file path
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            binding.imageViewUrunDetay.setImageBitmap(bitmap);
        } else {
            // Load and set the image from the drawable resources
            int resID = getContext().getResources().getIdentifier(imagePath, "drawable", getContext().getPackageName());
            binding.imageViewUrunDetay.setImageResource(resID);

        }

        binding.textViewUrunDetayBaslik.setText(urun.getAd());
        binding.textViewUtunDetayAciklama.setText((urun.getAciklama()));
        binding.textViewUrunDetayFiyat.setText(urun.getFiyat() + "TL");
        binding.buttonUrunDetay.setOnClickListener(view -> {
            Snackbar.make(view, "Talebiniz Satıcıya İletildi", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLUE)
                    //.setActionTextColor(Color.RED)
                    .show();;

        });

        return binding.getRoot();
    }
}