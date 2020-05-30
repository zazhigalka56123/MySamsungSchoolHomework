package ru.dreamteam.goldse4enie.domain;

import java.io.Serializable;

public class TimeListItem implements Serializable {
    public  String date;
    public  int campNumber;
    public  String campType;
    public  String timeStart;
    public  String timeEnd;
    public  String name;
    public  String place;

    public TimeListItem(String date, int campNumber, String campType, String timeStart, String timeEnd, String name, String place) {
        this.date = date;
        this.campNumber = campNumber;
        this.campType = campType;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
    }

    public TimeListItem(){

    }
}
