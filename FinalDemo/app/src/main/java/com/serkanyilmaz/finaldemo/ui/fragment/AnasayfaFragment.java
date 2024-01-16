package com.serkanyilmaz.finaldemo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.serkanyilmaz.finaldemo.MainActivity;
import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Sekmeler;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.ui.adapter.SekmelerAdapter;

import java.util.ArrayList;


public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);
        binding.toolbarAnasayfa.setTitle("Anasayfa");
        binding.anasayfaRv.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        ArrayList<Sekmeler> sekmelerListesi = new ArrayList<>();
        Sekmeler s1 = new Sekmeler("Alışveriş", "shopping_resim");
        Sekmeler s2 = new Sekmeler("Ev Arkadaşı", "rommate_resim");
        Sekmeler s3 = new Sekmeler("Yerler", "places_resim");
        sekmelerListesi.add(s1);
        sekmelerListesi.add(s2);
        sekmelerListesi.add(s3);

        SekmelerAdapter sekmelerAdapter = new SekmelerAdapter(
                requireContext(), sekmelerListesi);
        binding.anasayfaRv.setAdapter(sekmelerAdapter);

        navigationControls();

        return binding.getRoot();
    }

    private void navigationControls() {
        // Access the NavController from the MainActivity
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        // Access the BottomNavigationView from the MainActivity
        BottomNavigationView bottomNavigationView = ((MainActivity) requireActivity()).findViewById(R.id.bottomnav);

        // Set up BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home){
                navController.navigate(R.id.anasayfaFragment);
            } else if (item.getItemId() == R.id.profile) {
                navController.navigate(R.id.profileFragment);
            }
            return true;
        });
    }
}