package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serkanyilmaz.finaldemo.data.entity.Urunler;
import com.serkanyilmaz.finaldemo.databinding.FragmentShoppingAnasayfaBinding;
import com.serkanyilmaz.finaldemo.ui.adapter.UrunlerAdapter;


import java.util.ArrayList;

public class ShoppingAnasayfaFragment extends Fragment{
    private FragmentShoppingAnasayfaBinding binding;
    private FirebaseFirestore firestore;
    private CollectionReference urunlerCollection;
    private ArrayList<Urunler> urunlerList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShoppingAnasayfaBinding.inflate(inflater, container, false);

        firestore = FirebaseFirestore.getInstance();
        urunlerCollection = firestore.collection("urunler");
        binding.toolbarShoppingAnasayfa.setTitle("Alışveriş");
        binding.shoppingAnasayfaRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.progressBar.setVisibility(View.VISIBLE);
        urunlerCollection.get().addOnCompleteListener(value -> {
            binding.progressBar.setVisibility(View.GONE);
            if (value.isSuccessful()) {
                urunlerList.clear();
                for (DocumentSnapshot d : value.getResult()) {
                    Urunler urun = d.toObject(Urunler.class);
                    if (urun != null) {
                        urun.setId(d.getId());
                        urunlerList.add(urun);
                    }
                }
                UrunlerAdapter urunlerAdapter = new UrunlerAdapter(requireContext(), urunlerList, urunlerCollection);
                binding.shoppingAnasayfaRv.setAdapter(urunlerAdapter);
                binding.floatingActionButton.setOnClickListener(view -> {
                    ShoppingAnasayfaFragmentDirections.ShoppingEkleGecis gecis =
                            ShoppingAnasayfaFragmentDirections.shoppingEkleGecis(urunlerList.get(0));
                    Navigation.findNavController(view).navigate(gecis);
                });
            } else {
                Log.e("Firestore", "Error getting documents: ", value.getException());
                // Handle the error here if needed
            }
        });


        return binding.getRoot();
    }

}