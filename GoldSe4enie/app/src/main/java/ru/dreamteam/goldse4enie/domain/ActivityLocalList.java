package ru.dreamteam.goldse4enie.domain;

import java.util.ArrayList;

public class ActivityLocalList {
    public String date;
    public ArrayList<ActivityLocal> List;

    public ActivityLocalList(String date, ArrayList<ActivityLocal> list) {
        this.date = date;
        List = list;
    }
    public ActivityLocalList(){}
}
