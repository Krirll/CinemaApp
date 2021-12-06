package com.krirll.cinemaapp.ui.contracts;

import com.krirll.cinemaapp.network.models.Images;

public interface MovieInfoContract extends ViewPagerClickListener {
    void openCinemas(int id);
    void startActivity(Images list);
}
