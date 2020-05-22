package ru.dreamteam.goldse4enie.domain;

import java.util.ArrayList;

public class TimeList {
    public int item;
    public String date;
    public int campNumber;
    public String campType;
    public ArrayList<TimeListItem> timeListArray;

    public TimeList(int item, String date, int campNumber, String campType, ArrayList<TimeListItem> timeListArray) {
        this.item = item;
        this.date = date;
        this.campNumber = campNumber;
        this.campType = campType;
        this.timeListArray = timeListArray;

    }

    public TimeList(){

    }

    public TimeList clone(){
        return new TimeList(item,date,campNumber,campType,timeListArray);
    }
}
