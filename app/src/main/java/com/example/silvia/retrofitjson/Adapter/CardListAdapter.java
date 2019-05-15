package com.example.silvia.retrofitjson.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.silvia.retrofitjson.Model.Item;
import com.example.silvia.retrofitjson.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewHolder> implements View.OnClickListener{

    private Context context;
    private List<Item> list;
    private View.OnClickListener listener;

    public CardListAdapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_item, parent, false);

        itemview.setOnClickListener(this);

        return new MyViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item item = list.get(position);

        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText(item.getPrice());


        Picasso.with(context)
                .load(item.getThumbnail())
                .into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {

        //validacion click listener

        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name, description, price;

        public ImageView thumbnail;

        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.descritption);
            price = itemView.findViewById(R.id.price);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            viewForeground = itemView.findViewById(R.id.view_foreground);


        }
    }
}
