package com.krirll.cinemaapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.network.models.Image;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieImagesAdapter extends PagerAdapter {

    private final List<Image> list;
    private final Context context;

    public MovieImagesAdapter(List<Image> newList, Context newContext) {
        list = newList;
        context = newContext;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_image_item, container, false);
        ImageView imageView = view.findViewById(R.id.movie_image);
        Picasso.get()
                .load(Uri.parse(list.get(position).imageUrl))
                .fit()
                .centerCrop()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        view.findViewById(R.id.progress).setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Exception e) { }
                });
        container.addView(view, position);
        return view;
    }

    @Override
    public int getCount() {
        return list.size() - 1;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
