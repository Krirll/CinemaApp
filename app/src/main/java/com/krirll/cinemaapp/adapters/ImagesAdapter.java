package com.krirll.cinemaapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.krirll.cinemaapp.R;
import com.krirll.cinemaapp.libs.TouchImageView;
import com.krirll.cinemaapp.network.models.Image;
import com.krirll.cinemaapp.ui.contracts.ViewPagerClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImagesAdapter extends PagerAdapter {

    private final List<Image> list;
    private final Context context;
    private final ViewPagerClickListener listener;
    private final int layout;

    public ImagesAdapter(List<Image> newList,
                         Context newContext,
                         ViewPagerClickListener newListener,
                         int newLayout) {
        list = newList;
        context = newContext;
        listener = newListener;
        layout = newLayout;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(layout, container, false);

        switch(layout) {

            case(R.layout.image_item): {
                ImageView imageView = view.findViewById(R.id.image);
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
                if (listener != null)
                    imageView.setOnClickListener(view1 -> listener.showPhoto());
                break;
            }

            case(R.layout.photo_item): {
                TouchImageView imageTouch = view.findViewById(R.id.imageTouch);
                Picasso.get()
                        .load(Uri.parse(list.get(position).imageUrl))
                        .into(imageTouch, new Callback() {
                            @Override
                            public void onSuccess() {
                                view.findViewById(R.id.progress).setVisibility(View.GONE);
                                imageTouch.setMaxZoom(4f);
                            }
                            @Override
                            public void onError(Exception e) { }
                        });

            }
        }
        view.setTag(position);
        ((ViewPager)container).addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
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
