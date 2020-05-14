package ru.dreamteam.goldse4enie.domain;

public class ActivitylGlobal {
    public final String date;
    public final int maxPeople;
    public final String mainPeople;
    public final String timeStart;
    public final String timeEnd;
    public final String name;
    public final String place;
    public final String description;

    public ActivitylGlobal(String date, int maxPeople, String mainPeople, String timeStart, String timeEnd,
                           String name, String place, String description) {
        this.date = date;
        this.maxPeople = maxPeople;
        this.mainPeople = mainPeople;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
        this.description = description;
    }
}
