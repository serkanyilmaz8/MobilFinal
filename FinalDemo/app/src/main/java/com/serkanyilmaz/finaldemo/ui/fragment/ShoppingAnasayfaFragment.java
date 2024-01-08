package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Urunler;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentShoppingAnasayfaBinding;
import com.serkanyilmaz.finaldemo.ui.adapter.UrunlerAdapter;

import java.util.ArrayList;

public class ShoppingAnasayfaFragment extends Fragment {
    private FragmentShoppingAnasayfaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShoppingAnasayfaBinding.inflate(inflater, container, false);

        binding.toolbarShoppingAnasayfa.setTitle("Alışveriş");
        binding.shoppingAnasayfaRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        ArrayList<Urunler> urunlerList = new ArrayList<>();
        Urunler urun1 = new Urunler(1, "Macbook Pro", "16GB", "macbook_resim", 34544);
        Urunler urun2 = new Urunler(2, "Bisiklet", "16GB", "bisiklet_resim", 4535);
        Urunler urun3 = new Urunler(3, "Tişört", "M, kırmızı ", "tisort_resim", 123);
        Urunler urun4 = new Urunler(4, "Ayakkabı", "41, spor ayyakabı", "ayakkkabi_resim", 790);
        Urunler urun5 = new Urunler(5, "Telefon", "8GB RAM, 128GB hafıza", "telefon_resim", 7342);
        urunlerList.add(urun1);
        urunlerList.add(urun2);
        urunlerList.add(urun3);
        urunlerList.add(urun4);
        urunlerList.add(urun5);

        UrunlerAdapter urunlerAdapter = new UrunlerAdapter(requireContext(), urunlerList);
        binding.shoppingAnasayfaRv.setAdapter(urunlerAdapter);
        binding.floatingActionButton.setOnClickListener(view -> {
            ShoppingAnasayfaFragmentDirections.ShoppingEkleGecis gecis =
                    ShoppingAnasayfaFragmentDirections.shoppingEkleGecis(urun1);
            Navigation.findNavController(view).navigate(gecis);
        });


        return binding.getRoot();
    }
}