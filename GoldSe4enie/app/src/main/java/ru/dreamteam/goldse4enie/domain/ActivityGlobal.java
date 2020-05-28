package ru.dreamteam.goldse4enie.domain;

import java.util.ArrayList;

public class ActivityGlobal {
    public String date;
    public int maxPeople;
    public String mainPeople;
    public String timeStart;
    public String timeEnd;
    public String name;
    public String place;
    public String description;
    public ArrayList<String> peoples;

    public ActivityGlobal(int maxPeople, String mainPeople, String timeStart, String timeEnd,
                          String name, String place,String date, String description, ArrayList<String> peoples) {
        this.maxPeople = maxPeople;
        this.mainPeople = mainPeople;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
        this.description = description;
        this.peoples = peoples;
        this.date = date;
    }

    public ActivityGlobal(){}
}
