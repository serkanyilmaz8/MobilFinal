package com.serkanyilmaz.finaldemo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.EvArkadaslari;
import com.serkanyilmaz.finaldemo.databinding.FragmentRommateDetayBinding;


public class RommateDetayFragment extends Fragment {
    private FragmentRommateDetayBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRommateDetayBinding.inflate(inflater, container, false);

        RommateDetayFragmentArgs bundle = RommateDetayFragmentArgs.fromBundle(getArguments());
        EvArkadaslari arkadas = bundle.getArkadas();

        binding.toolbarRoommateDetay.setTitle("İlan Detayları");

        binding.imageViewRoommateDetay.setImageResource(
                getResources()
                        .getIdentifier("person_img", "drawable", requireContext().getPackageName())
        );

        binding.textViewRoommateDetayBaslik.setText(arkadas.getName());
        binding.textViewRommateDetayAciklama.setText(arkadas.getDescription());
        binding.buttonRoomateDetay.setOnClickListener(view -> {
            Snackbar.make(view, "Talebiniz İlan Sahibine İletildi", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLUE)

                    .show();;
        });

        return binding.getRoot();
    }
}