package com.serkanyilmaz.finaldemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.serkanyilmaz.finaldemo.MainActivity;
import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentLoginScreenBinding;

public class LoginScreen extends Fragment {

    private FragmentLoginScreenBinding binding;

    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginScreenBinding.inflate(inflater, container, false);
        showHidePassword();
        binding.textViewLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginScreen_to_registerScreen);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = binding.editTextLoginEmail.getText().toString();
                String textPassword = binding.editTextLoginPwd.getText().toString();

                if(TextUtils.isEmpty(textEmail)){
                    Toast.makeText(getContext(), "Mailinizi Giriniz", Toast.LENGTH_SHORT).show();
                    binding.editTextLoginEmail.setError("Mail girilmesi zorunludur");
                    binding.editTextLoginEmail.requestFocus();
                } else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(getContext(), "Mailinizi tekrar giriniz", Toast.LENGTH_SHORT).show();
                    binding.editTextLoginEmail.setError("Mail girilmesi zorunludur");
                    binding.editTextLoginEmail.requestFocus();
                } else if(TextUtils.isEmpty(textPassword)){
                    Toast.makeText(getContext(), "Parolanızı Giriniz", Toast.LENGTH_SHORT).show();
                    binding.editTextLoginPwd.setError("Parola girilmesi zorunludur");
                    binding.editTextLoginPwd.requestFocus();
                } else{
                    binding.progressBar.setVisibility(View.VISIBLE);
                    loginUser(textEmail, textPassword, v);
                }
            }
        });
        auth = FirebaseAuth.getInstance();
        return binding.getRoot();
    }

    private void loginUser(String textEmail, String textPassword, View v) {
        auth.signInWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.action_loginScreen_to_anasayfaFragment);
                }
                else{
                    Toast.makeText(getContext(), "Kullanıcı Bulunamadı", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showHidePassword() {
        binding.imagesViewShowHide.setImageResource(R.drawable.ic_show_pwd);

        binding.imagesViewShowHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.editTextLoginPwd.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    binding.editTextLoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.imagesViewShowHide.setImageResource(R.drawable.ic_show_pwd);
                }
                else{
                    binding.editTextLoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.imagesViewShowHide.setImageResource(R.drawable.ic_hide_pwd);
                }
            }
        });
    }
}