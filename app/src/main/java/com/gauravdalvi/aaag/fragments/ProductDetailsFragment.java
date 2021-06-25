package com.gauravdalvi.aaag.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gauravdalvi.aaag.R;
import com.gauravdalvi.aaag.databinding.FragmentProductDetailsBinding;
import com.gauravdalvi.aaag.databinding.FragmentRecyclerBinding;


public class ProductDetailsFragment extends Fragment {

    private String name, price, description, url, email;

    public ProductDetailsFragment() {

    }

    public ProductDetailsFragment(String name, String price, String description, String url, String email) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.url = url;
        this.email = email;
    }

    FragmentProductDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductDetailsBinding.inflate(getLayoutInflater(), container, false);

        Glide.with(binding.detailsImage.getContext()).load(url).into(binding.detailsImage);
        binding.detailsName.setText(name);
        binding.detailsPrice.setText("Rs. " + price);
        binding.detailsDescription.setText(description);
        binding.detailsEmail.setText(email);

        return binding.getRoot();
    }
}