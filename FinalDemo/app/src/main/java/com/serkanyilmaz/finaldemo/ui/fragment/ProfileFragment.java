package com.serkanyilmaz.finaldemo.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.serkanyilmaz.finaldemo.R;

public class ProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    private TextView displayNameTextView, emailTextView;
    private Button notificationsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        displayNameTextView = view.findViewById(R.id.displayNameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        notificationsButton = view.findViewById(R.id.notificationsButton);

        // Display user information
        displayUserInfo();
        notificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationsPopup(v);
            }
        });
        return view;
    }

    private void displayUserInfo() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String displayName = user.getDisplayName();
            String email = user.getEmail();

            // Display user information in TextViews
            displayNameTextView.setText("Merhaba " + displayName + "!");
            emailTextView.setText("( " + email + " )");
        }
    }

    // Method to show notifications popup
    public void showNotificationsPopup(View view) {
        // Inflate the popup layout
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_notifications, null);

        // Create an AlertDialog to show the popup
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(popupView);

        // Set up the ListView with dummy data (replace with your notifications data)
        String[] notificationsData = {"Verdiğiniz T-shirt ilanına geri dönüş aldınız. Detayları görmek için tıklayınız!", "Verdiğiniz Ev arkadaşı ilanına geri dönüş aldınız. Detayları görmek için tıklayınız"};
        ListView notificationsListView = popupView.findViewById(R.id.notificationsListView);
        notificationsListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, notificationsData));

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}