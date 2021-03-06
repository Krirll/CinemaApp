package com.krirll.cinemaapp.ui.contracts;

import com.krirll.cinemaapp.network.models.Images;

public interface CinemaInfoContract extends ViewPagerClickListener {
    void startActivity(Images list, int id);
    void openCallApp(String phoneNumber);
    void openMap(String address);
}
