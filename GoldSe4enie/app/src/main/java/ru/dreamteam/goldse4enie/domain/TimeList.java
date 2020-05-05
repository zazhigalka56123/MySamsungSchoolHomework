package ru.dreamteam.goldse4enie.domain;

import java.util.ArrayList;

public class TimeList {
    public final String date;
    public final int campNumber;
    public final String campType;
    public final ArrayList<String> timeStart;
    public final ArrayList<String> timeEnd;
    public final ArrayList<String> name;
    public final ArrayList<String> place;

    public TimeList(String date, int campNumber, String campType, ArrayList<String> timeStart, ArrayList<String> timeEnd, ArrayList<String> name, ArrayList<String> place) {
        this.date = date;
        this.campNumber = campNumber;
        this.campType = campType;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
    }
}
