package com.gauravdalvi.aaag.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.gauravdalvi.aaag.R;
import com.gauravdalvi.aaag.adapters.YourProductsAdapter;
import com.gauravdalvi.aaag.databinding.FragmentUploadBinding;
import com.gauravdalvi.aaag.databinding.FragmentYourProductsBinding;
import com.gauravdalvi.aaag.models.Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class YourProductsFragment extends Fragment {

    FragmentYourProductsBinding binding;
    RecyclerView recyclerView;
    YourProductsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentYourProductsBinding.inflate(getLayoutInflater(), container, false);

        recyclerView = binding.yourProductsRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Upload> options =
                new FirebaseRecyclerOptions.Builder<Upload>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Your Products"), Upload.class)
                        .build();
        

        adapter = new YourProductsAdapter(options);
        recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}