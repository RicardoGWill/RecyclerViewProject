package com.ricardogwill.recyclerviewproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener; // This is NOT the widget, but our own created interface below.

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImageView;
        public TextView textView1;
        public TextView textView2;
        public ImageView deleteImageView;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            itemImageView = itemView.findViewById(R.id.item_imageView);
            textView1 = itemView.findViewById(R.id.textView_1);
            textView2 = itemView.findViewById(R.id.textView_2);
            deleteImageView = itemView.findViewById(R.id.delete_imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override   // Note that this is automatically created by clicking "Ctrl I".
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(v, mListener);
        return exampleViewHolder;

    }

    @Override   // Note that this is automatically created by clicking "Ctrl I".
                // This passes values to the views.
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        holder.itemImageView.setImageResource(currentItem.getItemImageViewInt());
        holder.textView1.setText(currentItem.getTextView1String());
        holder.textView2.setText(currentItem.getTextView2String());
    }

    @Override   // Note that this is automatically created by clicking "Ctrl I".
    public int getItemCount() {
        return mExampleList.size();
    }
}
