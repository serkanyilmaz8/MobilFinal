package com.serkanyilmaz.finaldemo.ui.fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.serkanyilmaz.finaldemo.R;
import com.serkanyilmaz.finaldemo.data.entity.Urunler;
import com.serkanyilmaz.finaldemo.databinding.FragmentAnasayfaBinding;
import com.serkanyilmaz.finaldemo.databinding.FragmentShoppingEkleBinding;
import com.serkanyilmaz.finaldemo.ui.adapter.UrunlerAdapter;
import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShoppingEkleFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int GALLERY_PICK = 2;
    String currentPhotoPath;
    private FragmentShoppingEkleBinding binding;

    private FirebaseFirestore firestore;
    private CollectionReference urunlerCollection;

    private ArrayList<Urunler> urunlerList = new ArrayList<>();
    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShoppingEkleBinding.inflate(inflater, container, false);
       /* firestore = FirebaseFirestore.getInstance();
        urunlerCollection = firestore.collection("urunler");*/

        binding.toolbarshoppingEkle.setTitle("Ürün Ekle");

        binding.buttonResimEkle.setOnClickListener(view -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(getActivity(), new String[]{
                        Manifest.permission.CAMERA
                }, REQUEST_IMAGE_CAPTURE);
            }
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    Toast.makeText(getContext(), "Yanlışlık var!", Toast.LENGTH_SHORT).show();
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(requireContext(),
                            "com.example.android.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
        });

        binding.btnFromGallery.setOnClickListener(view -> {
            openGallery();
        });
        binding.buttonUrunEkle.setOnClickListener(view -> {
            // Get the entered values
            String name = binding.editTextUrunEkleBaslik.getText().toString().trim();
            String description = binding.editTextUrunEkleAciklama.getText().toString().trim();
            String priceText = binding.editTextUrunEkleFiyat.getText().toString().trim();
            // Assuming you have a method to get the image path or URI
            String imagePath = currentPhotoPath; // Replace with actual method

            // Validate the input (You can add more validation as per your requirements)
            if (name != null && description != null && priceText != null && imagePath != null ) {
                int price = Integer.parseInt(priceText);
                // Create a new product object
                Urunler newProduct = new Urunler("", name, description, imagePath, price);

                addUruntoFirebase(newProduct);
                ShoppingEkleFragmentDirections.EkledenAnasayfayaGecis gecis =
                        (ShoppingEkleFragmentDirections.EkledenAnasayfayaGecis) ShoppingEkleFragmentDirections.EkledenAnasayfayaGecis(newProduct);
                Navigation.findNavController(view).navigate(gecis);

            } else {
                // Show an error message or handle invalid input
                Toast.makeText(getContext(), "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_PICK);
    }

    private void addUruntoFirebase(Urunler newProduct) {
        firestore = FirebaseFirestore.getInstance();
        urunlerCollection = firestore.collection("urunler");
        urunlerCollection.add(newProduct).addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Product successfully added!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore", "Error adding document", e);
                    // Handle the error here if needed
                });
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check the request code to determine which operation was performed
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Handle the result of capturing an image from the camera
            handleCameraResult();
        } else if (requestCode == GALLERY_PICK && resultCode == RESULT_OK && data != null) {
            // Handle the result of selecting an image from the gallery
            handleGalleryResult(data.getData());
        }
    }

    private void handleCameraResult() {
        // Load the captured image from the file path into a Bitmap
        Bitmap originalBitmap = BitmapFactory.decodeFile(currentPhotoPath);

        // Specify the desired width and height for the resized image
        int desiredWidth = 400; // Set your desired width
        int desiredHeight = 400; // Set your desired height

        // Resize the image to the desired dimensions
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, desiredWidth, desiredHeight, true);

        // Set the resized Bitmap to the ImageView
        binding.imageViewUrunEkle.setImageBitmap(resizedBitmap);
    }

    // Function to handle the result of selecting an image from the gallery
    private void handleGalleryResult(Uri selectedImageUri) {
        try {
            // Load the selected image from the gallery into a Bitmap
            Bitmap originalBitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri);

            // Specify the desired width and height for the resized image
            int desiredWidth = 400; // Set your desired width
            int desiredHeight = 400; // Set your desired height

            // Resize the image to the desired dimensions
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, desiredWidth, desiredHeight, true);

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            currentPhotoPath = filePath;
            // Set the resized Bitmap to the ImageView
            binding.imageViewUrunEkle.setImageBitmap(resizedBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}