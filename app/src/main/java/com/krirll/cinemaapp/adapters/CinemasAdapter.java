package com.krirll.cinemaapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.network.models.CinemaModel;

import java.util.ArrayList;
import java.util.List;

public class CinemasAdapter extends RecyclerView.Adapter<CinemasAdapter.ViewHolder> {

    private List<CinemaModel> list = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<CinemaModel> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        //todo объявить поля
        ViewHolder(View view) {
            super(view);
            //todo присвоить поля
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
