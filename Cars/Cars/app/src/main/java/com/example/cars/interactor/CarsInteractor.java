package com.example.cars.interactor;

import android.widget.Button;

import androidx.annotation.ColorInt;

import com.example.cars.domain.Car;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CarsInteractor {

    public ArrayList<Car> getCars() {
        return CarsRepository.generateCarList(10);
    }

    public ArrayList<Car> sortCars(ArrayList<Car> rawCarsList, Comparator<Car> comparator) {
        ArrayList<Car> sortedCarsList = rawCarsList;
        Collections.sort(sortedCarsList, comparator);

        return sortedCarsList;
    }

}
