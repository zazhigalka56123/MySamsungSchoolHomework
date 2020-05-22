package ru.dreamteam.goldse4enie.domain;

import java.util.ArrayList;

public class ActivityLocalList {
    public final String date;
    public final ArrayList<ActivityLocal> List;

    public ActivityLocalList(String date, ArrayList<ActivityLocal> list) {
        this.date = date;
        List = list;
    }
}
