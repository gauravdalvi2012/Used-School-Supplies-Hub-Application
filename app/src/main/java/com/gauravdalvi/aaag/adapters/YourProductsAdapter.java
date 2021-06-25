package com.gauravdalvi.aaag.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.gauravdalvi.aaag.R;
import com.gauravdalvi.aaag.models.Upload;

import org.w3c.dom.Text;

public class YourProductsAdapter extends FirebaseRecyclerAdapter<Upload, YourProductsAdapter.myViewHolder> {

    public YourProductsAdapter(@NonNull FirebaseRecyclerOptions<Upload> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Upload model) {
        holder.yp_name.setText(model.getItemName());
        holder.yp_price.setText("Rs. " + model.getPrice());
        holder.yp_category.setText(model.getCategory());
        Glide.with(holder.yp_image.getContext()).load(model.getUrl()).into(holder.yp_image);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design_yourproduct, parent, false);
        return new myViewHolder(view);
    }



    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView yp_image;
        TextView yp_name, yp_price, yp_category;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            yp_image = (ImageView) itemView.findViewById(R.id.yp_image);
            yp_name = (TextView) itemView.findViewById(R.id.yp_name);
            yp_price = (TextView) itemView.findViewById(R.id.yp_price);
            yp_category = (TextView) itemView.findViewById(R.id.yp_category);

        }
    }
}
