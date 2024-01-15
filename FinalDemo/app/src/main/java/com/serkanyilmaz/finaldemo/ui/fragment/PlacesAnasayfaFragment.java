package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Yerler;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentPlacesAnasayfaBinding;
import com.serkanyilmaz.finaldemo.ui.adapter.PlacesAdapter;

import java.util.ArrayList;


public class PlacesAnasayfaFragment extends Fragment {
    private FragmentPlacesAnasayfaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlacesAnasayfaBinding.inflate(inflater, container, false);
        binding.toolbarPlacesAnasayfa.setTitle("Yerler");

        binding.placesRv.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        ArrayList<Yerler> yerlerListesi = new ArrayList<>();
        Yerler yer1 = new Yerler(1, "CİCİ DÖNER", "Döner ve kebap salonu", "restoren_img" );
        Yerler yer2 = new Yerler(2, "CİCİ DÖNER", "Döner ve kebap salonu", "restoren_img" );
        Yerler yer3 = new Yerler(3, "CİCİ DÖNER", "Döner ve kebap salonu", "restoren_img" );
        Yerler yer4 = new Yerler(4, "CİCİ DÖNER", "Döner ve kebap salonu", "restoren_img" );
        Yerler yer5 = new Yerler(5, "CİCİ DÖNER", "Döner ve kebap salonu", "restoren_img" );
        Yerler yer6 = new Yerler(6, "CİCİ DÖNER", "Döner ve kebap salonu", "restoren_img" );
        Yerler yer7 = new Yerler(7, "CİCİ DÖNER", "Döner ve kebap salonu", "restoren_img" );

        yerlerListesi.add(yer1);
        yerlerListesi.add(yer2);
        yerlerListesi.add(yer3);
        yerlerListesi.add(yer4);
        yerlerListesi.add(yer5);
        yerlerListesi.add(yer6);
        yerlerListesi.add(yer7);

        PlacesAdapter placesAdapter = new PlacesAdapter(
                requireContext(), yerlerListesi);
        binding.placesRv.setAdapter(placesAdapter);

        return binding.getRoot();
    }
}