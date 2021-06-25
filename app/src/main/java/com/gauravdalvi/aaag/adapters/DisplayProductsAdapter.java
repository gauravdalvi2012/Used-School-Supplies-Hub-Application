package com.gauravdalvi.aaag.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.gauravdalvi.aaag.R;
import com.gauravdalvi.aaag.fragments.ProductDetailsFragment;
import com.gauravdalvi.aaag.models.Upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayProductsAdapter extends FirebaseRecyclerAdapter<Upload, DisplayProductsAdapter.myViewHolder>{
    FirebaseAuth auth;
    FirebaseDatabase database;

    public DisplayProductsAdapter(@NonNull FirebaseRecyclerOptions<Upload> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Upload model) {
        holder.dp_name.setText(model.getItemName());
        holder.dp_price.setText("Rs. " + model.getPrice());
        Glide.with(holder.dp_image.getContext()).load(model.getUrl()).into(holder.dp_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new ProductDetailsFragment(model.getItemName(), model.getPrice(), model.getDescription(), model.getUrl(), model.getEmail())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_displayproduct, parent, false);
        return new DisplayProductsAdapter.myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView dp_image;
        TextView dp_name, dp_price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            dp_image = (ImageView) itemView.findViewById(R.id.dp_image);
            dp_name = (TextView) itemView.findViewById(R.id.dp_name);
            dp_price = (TextView) itemView.findViewById(R.id.dp_price);

        }
    }
}
