package com.gauravdalvi.aaag.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.gauravdalvi.aaag.R;
import com.gauravdalvi.aaag.adapters.DisplayProductsAdapter;
import com.gauravdalvi.aaag.adapters.YourProductsAdapter;
import com.gauravdalvi.aaag.databinding.FragmentRecyclerBinding;
import com.gauravdalvi.aaag.models.Upload;
import com.google.firebase.database.FirebaseDatabase;


public class RecyclerFragment extends Fragment {

    FragmentRecyclerBinding binding;
    RecyclerView recyclerView;
    DisplayProductsAdapter adapter;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRecyclerBinding.inflate(getLayoutInflater(), container, false);

        recyclerView = binding.recView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Upload> options =
                new FirebaseRecyclerOptions.Builder<Upload>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Categories").child(getActivity().getIntent().getStringExtra("Name")), Upload.class)
                        .build();

        adapter = new DisplayProductsAdapter(options);
        recyclerView.setAdapter(adapter);

        searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

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

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        getActivity().getMenuInflater().inflate(R.menu.searchmenu, menu);
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) item.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                processsearch(s);
//                return false;
//            }
//        });
//
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    private void processsearch(String s) {
        FirebaseRecyclerOptions<Upload> options =
                new FirebaseRecyclerOptions.Builder<Upload>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Categories").child(getActivity().getIntent().getStringExtra("Name")).orderByChild("itemName").startAt(s).endAt(s + "\uf8ff"), Upload.class)
                        .build();

        adapter = new DisplayProductsAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}