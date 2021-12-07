package com.krirll.cinemaapp.ui.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;

import com.krirll.cinemaapp.ui.contracts.PhotoContract;

import java.util.UUID;

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
        BitmapDrawable bitmapDrawable = (BitmapDrawable) photo;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        //todo проверить что сохранилось
        MediaStore.Images.Media.insertImage(
                context.getContentResolver(),
                bitmap,
                String.valueOf(UUID.randomUUID()),
                ""
        );
        photoContract.showToast();
    }

}
