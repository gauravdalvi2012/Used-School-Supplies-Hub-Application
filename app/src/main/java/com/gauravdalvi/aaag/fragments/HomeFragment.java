package com.gauravdalvi.aaag.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gauravdalvi.aaag.DisplayProductsActivity;
import com.gauravdalvi.aaag.R;
import com.gauravdalvi.aaag.databinding.FragmentHomeBinding;
import com.gauravdalvi.aaag.databinding.FragmentYourProductsBinding;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

        //School Books
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DisplayProductsActivity.class);
                intent.putExtra("Name", "School Books");
                startActivity(intent);
            }
        });

        //College Books
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DisplayProductsActivity.class);
                intent.putExtra("Name", "College Books");
                startActivity(intent);
            }
        });

        //Stationary Items
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DisplayProductsActivity.class);
                intent.putExtra("Name", "Stationary Items");
                startActivity(intent);
            }
        });

        //Other
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DisplayProductsActivity.class);
                intent.putExtra("Name", "Other");
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}