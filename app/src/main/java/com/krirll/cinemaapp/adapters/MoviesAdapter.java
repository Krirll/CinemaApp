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
import com.krirll.cinemaapp.network.models.Movie;
import com.krirll.cinemaapp.ui.contracts.MoviesListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements Serializable {

    private List<Movie> list = new ArrayList<>();
    private MoviesListener listener;

    public void setListener(MoviesListener listener) {
        this.listener = listener;
    }

    public List<Movie> getList() {
        return list;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Movie> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;
        TextView description;
        ViewHolder(View view) {
            super(view);
            poster = view.findViewById(R.id.poster);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get()
                .load(String.valueOf(list.get(position).poster.imageUrl))
                .fit()
                .centerCrop()
                .into(holder.poster);
        holder.title.setText(list.get(position).title);
        if (holder.description != null) { //если телефон расположен горизонтально, то добавляется описание
            //если описание слишком большое, то оно будет сокращено до 300 символов с добавлением "..."
            if (list.get(position).description.length() > 300) {
                String shortDescription = list.get(position).description;
                while (shortDescription.length() > 300)
                    shortDescription = shortDescription.substring(
                            0,
                            shortDescription.lastIndexOf(' ')
                    );
                shortDescription = shortDescription.concat("...");
                holder.description.setText(shortDescription);
            }
            else
                holder.description.setText(list.get(position).description);
        }
        holder.itemView.setOnClickListener(view -> listener.onClick(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
