package com.krirll.cinemaapp.ui.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.krirll.cinemaapp.util.PhotoManager;
import com.krirll.cinemaapp.ui.contracts.PhotoContract;

public class PhotoPresenter {

    private static PhotoPresenter photoPresenter;
    private static PhotoContract photoContract;

    private PhotoPresenter(PhotoContract view) {
        photoContract = view;
    }

    public static PhotoPresenter getInstance(PhotoContract view) {
        if (photoPresenter == null)
            photoPresenter = new PhotoPresenter(view);
        return photoPresenter;
    }

    public void savePhoto(Drawable photo, Context context) {
        Bitmap bitmap = ((BitmapDrawable) photo).getBitmap();
        PhotoManager manager = new PhotoManager();
        String name = manager.createName();
        manager.save(name, bitmap, context);
        photoContract.showToast(manager.check(name));
    }

}
