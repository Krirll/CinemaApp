package com.krirll.cinemaapp.common;

import java.sql.Timestamp;

public class TimeConverter {

    public long getCurrentTimeStamp() {
        return (new Timestamp(System.currentTimeMillis()).getTime() / 1000);
    }
}
