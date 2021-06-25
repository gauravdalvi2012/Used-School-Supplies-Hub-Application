package com.gauravdalvi.aaag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.gauravdalvi.aaag.fragments.RecyclerFragment;

public class DisplayProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);


        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new RecyclerFragment()).commit();


    }
}