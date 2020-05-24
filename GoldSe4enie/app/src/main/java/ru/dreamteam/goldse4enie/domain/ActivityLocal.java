package ru.dreamteam.goldse4enie.domain;

import java.util.ArrayList;

public class ActivityLocal {
    public int maxPeople;
    public int campNumber;
    public String campType;
    public String timeStart;
    public String timeEnd;
    public String name;
    public String place;
    public String date;
    public String description;
    public ArrayList<String> peoples;

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

    public ActivityLocal(){}
}
