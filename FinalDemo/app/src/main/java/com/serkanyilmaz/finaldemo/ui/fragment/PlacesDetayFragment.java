package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Yerler;
import com.serkanyilmaz.finaldemo.databinding.FragmentPlacesAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentPlacesDetayBinding;


public class PlacesDetayFragment extends Fragment {
    private FragmentPlacesDetayBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlacesDetayBinding.inflate(inflater, container, false);

        PlacesDetayFragmentArgs bundle = PlacesDetayFragmentArgs.fromBundle(getArguments());
        Yerler yer = bundle.getYerler();

        binding.toolbarPlacesDetay.setTitle(yer.getAd());

        binding.imageViewPlace.setImageResource(
                getResources()
                        .getIdentifier(yer.getResim(), "drawable", requireContext().getPackageName()));

        binding.textViewPlacesDetayAciklama.setText(yer.getAciklama());


        return binding.getRoot();
    }
}