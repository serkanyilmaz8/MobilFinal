package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serkanyilmaz.finaldemo.data.entity.EvArkadaslari;
import com.serkanyilmaz.finaldemo.databinding.FragmentRommateAnasayfaBinding;
import com.serkanyilmaz.finaldemo.ui.adapter.ArkadaslarAdapter;

import java.util.ArrayList;


public class RommateAnasayfaFragment extends Fragment {
    private FragmentRommateAnasayfaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRommateAnasayfaBinding.inflate(inflater, container, false);

        binding.toolbarRommateAnasayfa.setTitle("Ev Arkadaşı");
        binding.roommateAnasayfaRv.setLayoutManager(new LinearLayoutManager(requireContext()));

        ArrayList<EvArkadaslari> arkadaslarList = new ArrayList<>();

        EvArkadaslari e1 = new EvArkadaslari(1, "Serkan Yilmaz", "Ceng 3. Sınıf, Sigara içmiyor");
        EvArkadaslari e2 = new EvArkadaslari(2, "Numan Varol", "Ceng 4.sınıf, sigara içiyor");
        EvArkadaslari e3 = new EvArkadaslari(3, "Berke Özkul", "Ceng 3.sınıf, sigara içmiyor");
        EvArkadaslari e4 = new EvArkadaslari(4, "Dodo T.oğlu", "Ceng 3.sınıf, sigara içiyor");
        EvArkadaslari e5 = new EvArkadaslari(5, "Okan Ç.", "Ceng 3.sınıf, puro içiyor");
        EvArkadaslari e6 = new EvArkadaslari(6, "Tuğçik", "Ceng 3.sınıf, sigara içiyor");
        EvArkadaslari e7 = new EvArkadaslari(7, "Emre Constructor", "Ceng 3.sınıf, sigara içmiyor");

        arkadaslarList.add(e1);
        arkadaslarList.add(e2);
        arkadaslarList.add(e3);
        arkadaslarList.add(e4);
        arkadaslarList.add(e5);
        arkadaslarList.add(e6);
        arkadaslarList.add(e7);

        ArkadaslarAdapter arkadaslarAdapter = new ArkadaslarAdapter(requireContext(), arkadaslarList);
        binding.roommateAnasayfaRv.setAdapter(arkadaslarAdapter);


        return binding.getRoot();
    }
}