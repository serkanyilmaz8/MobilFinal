package com.serkanyilmaz.finaldemo.ui.fragment;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.databinding.FragmentRegisterScreenBinding;

public class RegisterScreen extends Fragment {

    private FragmentRegisterScreenBinding binding;
    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterScreenBinding.inflate(inflater, container, false);

        showHidePwd();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = binding.editTextRegisterEmail.getText().toString();
                String textPassword = binding.editTextRegisterPwd.getText().toString();
                String textName = binding.editTextRegisterName.getText().toString();

                if(TextUtils.isEmpty(textName)){
                    Toast.makeText(getContext(), "İsminizi Giriniz", Toast.LENGTH_SHORT).show();
                    binding.editTextRegisterName.setError("İsim girilmesi zorunludur");
                    binding.editTextRegisterName.requestFocus();
                } else if(TextUtils.isEmpty(textEmail)){
                    Toast.makeText(getContext(), "Mailinizi Giriniz", Toast.LENGTH_SHORT).show();
                    binding.editTextRegisterEmail.setError("Mail girilmesi zorunludur");
                    binding.editTextRegisterEmail.requestFocus();
                } else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()){
                    Toast.makeText(getContext(), "Mailinizi tekrar giriniz", Toast.LENGTH_SHORT).show();
                    binding.editTextRegisterEmail.setError("Mail girilmesi zorunludur");
                    binding.editTextRegisterEmail.requestFocus();
                } else if(TextUtils.isEmpty(textPassword)){
                    Toast.makeText(getContext(), "Parolanızı Giriniz", Toast.LENGTH_SHORT).show();
                    binding.editTextRegisterPwd.setError("Parola girilmesi zorunludur");
                    binding.editTextRegisterPwd.requestFocus();
                } else if(textPassword.length() < 6){
                    Toast.makeText(getContext(), "Parola çok kısa", Toast.LENGTH_SHORT).show();
                    binding.editTextRegisterPwd.setError("Parolanız en az 6 haneli olmalıdır");
                    binding.editTextRegisterPwd.requestFocus();
                } else{
                    binding.progressBar.setVisibility(View.VISIBLE);
                    registerUser(textEmail, textName, textPassword, v);
                }

            }
        });
        
        return binding.getRoot();
    }

    private void registerUser(String textEmail, String textName, String textPassword, View v) {
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    if(firebaseUser != null){
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(textName).build();
                        firebaseUser.updateProfile(profileUpdates);

                        Toast.makeText(getContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();

                        Navigation.findNavController(v).navigate(R.id.action_registerScreen_to_loginScreen);
                    }
                }
            }
        });
    }

    private void showHidePwd(){
        binding.imagesViewShowHideRegister.setImageResource(R.drawable.ic_show_pwd);

        binding.imagesViewShowHideRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(binding.editTextRegisterPwd.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                binding.editTextRegisterPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.imagesViewShowHideRegister.setImageResource(R.drawable.ic_show_pwd);
            }
            else{
                binding.editTextRegisterPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.imagesViewShowHideRegister.setImageResource(R.drawable.ic_hide_pwd);
            }
        }
    });
}
}