package com.gauravdalvi.aaag.fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gauravdalvi.aaag.MainActivity;
import com.gauravdalvi.aaag.R;
import com.gauravdalvi.aaag.databinding.FragmentUploadBinding;
import com.gauravdalvi.aaag.models.Upload;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Objects;


public class UploadFragment extends Fragment {

    FragmentUploadBinding binding;
    Uri imageUri;

    StorageReference storageReference;
    DatabaseReference yourProductReference, categoryReference;
    FirebaseAuth auth;

    String itemName, price, category, description;
    String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUploadBinding.inflate(getLayoutInflater(), container, false);

        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCropActivity();
            }
        });

        auth = FirebaseAuth.getInstance();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.category.setAdapter(adapter);

        binding.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDetails();
            }
        });


        return binding.getRoot();
    }

    private void uploadDetails() {

        if (imageUri != null) {
            itemName = binding.name.getText().toString();
            price = binding.price.getText().toString();
            description = binding.description.getText().toString();
            if (binding.category.getSelectedItemPosition() != 0) {
                category = binding.category.getSelectedItem().toString();
            }


            if (!itemName.equals("") && !price.equals("") && !description.equals("") && binding.category.getSelectedItemPosition() != 0) {
                auth = FirebaseAuth.getInstance();
                storageReference = FirebaseStorage.getInstance().getReference().child("Images").child(auth.getUid()).child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
                yourProductReference = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getUid()).child("Your Products").push();
                categoryReference = FirebaseDatabase.getInstance().getReference().child("Categories").child(category).push();

                storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getUid()).child("Account Details").child("email");
                                database.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        email = snapshot.getValue(String.class);

                                        Upload yourProduct = new Upload(itemName,price,category,description,uri.toString());
                                        Upload categoryProduct = new Upload(itemName,price,category,description,uri.toString(), email);
                                        yourProductReference.setValue(yourProduct);
                                        categoryReference.setValue(categoryProduct);

                                        Toast.makeText(getContext(), "Details Uploaded", Toast.LENGTH_SHORT).show();
                                        imageUri = null;
                                        itemName = null;
                                        price = null;
                                        category = null;
                                        description = null;
                                        email = null;

                                        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ic_baseline_add_photo_alternate_24);
                                        binding.image.setImageBitmap(bitmap);
                                        binding.image.setBackgroundResource(R.drawable.text_view_border);
                                        binding.name.setText(null);
                                        binding.price.setText(null);
                                        binding.category.setSelection(0);
                                        binding.description.setText(null);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        });
                    }
                });
            }
            else {
                Toast.makeText(getContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getContext(), "Please select image", Toast.LENGTH_SHORT).show();
        }

    }

    private String getFileExtension(Uri uri) {
        return uri.getPath().substring(uri.getPath().lastIndexOf(".") + 1);
    }


    private void startCropActivity() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .setRequestedSize(500,500)
                .start(getContext(), UploadFragment.this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == getActivity().RESULT_OK) {
                imageUri = result.getUri();
                binding.image.setImageURI(imageUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}