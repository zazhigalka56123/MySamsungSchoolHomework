package ru.dreamteam.goldse4enie.domain;

public class ActivityLocal {
    public final int maxPeople;
    public final int campNumber;
    public final String campType;
    public final String timeStart;
    public final String timeEnd;
    public final String name;
    public final String place;
    public final String description;

    public ActivityLocal(int maxPeople, int campNumber, String campType,
                         String timeStart, String timeEnd, String name, String place,
                         String description) {

        this.maxPeople = maxPeople;
        this.campNumber = campNumber;
        this.campType = campType;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
        this.description = description;
    }
}
