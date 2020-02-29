package com.example.cars.comparators;

import com.example.cars.domain.Car;

import java.util.Comparator;

public class VolumeComporator implements Comparator<Car> {
    private boolean flag;

    public VolumeComporator (boolean flag){
        this.flag = flag;
    }
    @Override
    public int compare(Car o1, Car o2) {
        if (flag == true) {

            if (o1.volume > o2.volume) return 1;
            else if (o1.volume == o2.volume) return 0;
            else return -1;
        }else
        {
            if (o1.volume > o2.volume) return -1;
            else if (o1.volume == o2.volume) return 0;
            else return 1;
        }

    }
}
