package com.example.cars.comparators;

import com.example.cars.domain.Car;

import java.util.Comparator;

public class MileageComparator implements Comparator<Car> {
    private boolean flag;

    public MileageComparator(boolean flag){
        this.flag = flag;
    }
    @Override
    public int compare(Car o1, Car o2) {
        if (flag == true) {
            if (o1.mileage > o2.mileage) return 1;
            else if (o1.mileage == o2.mileage) return 0;
            else return -1;
        }else{
            if (o1.mileage > o2.mileage) return -1;
            else if (o1.mileage == o2.mileage) return 0;
            else return 1;
        }
    }
}
