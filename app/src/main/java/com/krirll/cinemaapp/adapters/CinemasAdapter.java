package com.krirll.cinemaapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.network.models.Cinema;
import com.krirll.cinemaapp.ui.contracts.CinemaListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CinemasAdapter extends RecyclerView.Adapter<CinemasAdapter.ViewHolder> implements Serializable {

    private List<Cinema> list = new ArrayList<>();
    private CinemaListener listener;

    public void setListener(CinemaListener newListener) {
        listener = newListener;
    }

    public List<Cinema> getList() {
        return list;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Cinema> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        TextView session;
        TextView price;
        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
            session = view.findViewById(R.id.session);
            price = view.findViewById(R.id.price);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.cinema_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get()
                .load(String.valueOf(list.get(position).place.listImages.get(0).imageUrl))
                .fit()
                .centerCrop()
                .into(holder.image);
        holder.title.setText(list.get(position).place.title);
        holder.session.setText(
                holder.itemView.getContext().getString(R.string.session)
                .concat(
                        ": ".concat(new SimpleDateFormat("dd.MM, HH:mm", Locale.ROOT)
                                        .format(new Date(list.get(position).dateTime * 1000)))
                )
        );
        if (holder.price != null) //если телефон расположен горизонтально, то добавляется цена
                holder.price.setText(
                        holder.itemView.getContext().getString(R.string.price)
                        .concat(
                                ": ".concat(
                                        (list.get(position).price == null) ?
                                        holder.itemView.getContext().getString(R.string.no_info) : list.get(position).price)
                        )
                );
        holder.itemView.setOnClickListener(view -> listener.onClick(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
