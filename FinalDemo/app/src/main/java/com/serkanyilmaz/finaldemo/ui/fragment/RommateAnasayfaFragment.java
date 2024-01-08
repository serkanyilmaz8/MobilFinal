package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentRommateAnasayfaBinding;


public class RommateAnasayfaFragment extends Fragment {
    private FragmentRommateAnasayfaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRommateAnasayfaBinding.inflate(inflater, container, false);
        binding.toolbarRommateAnasayfa.setTitle("Roommate");



        return binding.getRoot();
    }
}