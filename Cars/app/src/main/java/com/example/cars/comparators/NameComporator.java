package com.example.cars.comparators;

import android.util.Log;

import com.example.cars.domain.Car;

import java.util.Comparator;

public class NameComporator implements Comparator<Car> {
    private boolean flag;

    public NameComporator(boolean flag){
        this.flag = flag;
    }
    @Override
    public int compare(Car o1, Car o2) {
        Log.d("comparator", String.valueOf(flag));
        int kek = o1.name.compareTo(o2.name);
        if (flag == true) return kek;
        return -kek;


    }
}
