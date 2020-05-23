package ru.dreamteam.goldse4enie.domain;

import java.util.ArrayList;

public class ActivityLocal {
    public final int maxPeople;
    public final int campNumber;
    public final String campType;
    public final String timeStart;
    public final String timeEnd;
    public final String name;
    public final String place;
    public final String date;
    public final String description;
    public final ArrayList<String> peoples;

    public ActivityLocal(int maxPeople, int campNumber, String campType,
                         String timeStart, String timeEnd, String name, String place,
                         String date, String description, ArrayList<String> peoples) {

        this.maxPeople = maxPeople;
        this.campNumber = campNumber;
        this.campType = campType;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
        this.date = date;
        this.description = description;
        this.peoples = peoples;
    }
}
