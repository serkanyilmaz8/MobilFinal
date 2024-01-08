package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Sekmeler;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.ui.adapter.SekmelerAdapter;

import java.util.ArrayList;


public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);
        binding.toolbarAnasayfa.setTitle("Anasayfa");
        binding.anasayfaRv.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        ArrayList<Sekmeler> sekmelerListesi = new ArrayList<>();
        Sekmeler s1 = new Sekmeler("Alışveriş", "shopping_resim");
        Sekmeler s2 = new Sekmeler("Rommate", "rommate_resim");
        Sekmeler s3 = new Sekmeler("Places", "places_resim");
        sekmelerListesi.add(s1);
        sekmelerListesi.add(s2);
        sekmelerListesi.add(s3);

        SekmelerAdapter sekmelerAdapter = new SekmelerAdapter(
                requireContext(), sekmelerListesi);
        binding.anasayfaRv.setAdapter(sekmelerAdapter);


        return binding.getRoot();
    }
}