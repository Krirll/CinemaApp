package com.krirll.cinemaapp.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import java.util.UUID;

public class PhotoManager {

    public void save(String name, Bitmap bitmap, Context context) {
        MediaStore.Images.Media.insertImage(
                context.getContentResolver(),
                bitmap,
                name,
                ""
        );
    }

    public String createName() {
        return UUID.randomUUID().toString();
    }

    public boolean check(String name) {
        return MediaStore.Images.Media.getContentUri(name) != null;
    }

}
