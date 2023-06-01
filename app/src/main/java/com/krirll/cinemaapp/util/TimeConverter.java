package com.krirll.cinemaapp.util;

import java.sql.Timestamp;

public class TimeConverter {

    public long getCurrentTimeStamp() {
        return (new Timestamp(System.currentTimeMillis()).getTime() / 1000);
    }
}
