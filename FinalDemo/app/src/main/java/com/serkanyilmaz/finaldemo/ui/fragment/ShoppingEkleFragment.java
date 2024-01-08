package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentShoppingEkleBinding;

public class ShoppingEkleFragment extends Fragment {
    private FragmentShoppingEkleBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShoppingEkleBinding.inflate(inflater, container, false);

        binding.toolbarshoppingEkle.setTitle("Ürün Ekle");

        return binding.getRoot();
    }
}