package ru.dreamteam.goldse4enie.domain;

public class ActivityGlobal {
    public int maxPeople;
    public String mainPeople;
    public String timeStart;
    public String timeEnd;
    public String name;
    public String place;
    public String description;

    public ActivityGlobal(int maxPeople, String mainPeople, String timeStart, String timeEnd,
                          String name, String place, String description) {
        this.maxPeople = maxPeople;
        this.mainPeople = mainPeople;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
        this.description = description;
    }
    public  ActivityGlobal(){}
}
