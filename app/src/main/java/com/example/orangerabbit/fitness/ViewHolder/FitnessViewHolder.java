package com.example.orangerabbit.fitness.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orangerabbit.fitness.Interface.ItemClickListener;
import com.example.orangerabbit.fitness.R;

public class  FitnessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtFitnessName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public FitnessViewHolder(View itemView) {
        super(itemView);

        txtFitnessName = (TextView)itemView.findViewById(R.id.attribute_name);
        imageView = (ImageView)itemView.findViewById(R.id.attributes_img);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
