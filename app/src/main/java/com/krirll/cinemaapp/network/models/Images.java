package com.krirll.cinemaapp.network.models;

import java.io.Serializable;
import java.util.List;

public class Images implements Serializable {

    private final List<Image> list;

    public Images(List<Image> newList) {
        list = newList;
    }

    public List<Image> getList() {
        return list;
    }
}
