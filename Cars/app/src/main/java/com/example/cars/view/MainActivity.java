package com.example.cars.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cars.R;
import com.example.cars.comparators.MileageComparator;
import com.example.cars.comparators.NameComporator;
import com.example.cars.comparators.PowerComparator;
import com.example.cars.comparators.VolumeComporator;
import com.example.cars.interactor.CarsInteractor;
import com.example.cars.view.adapter.CarsAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView carsRecycler;
    private CarsAdapter carsAdapter;
    private CarsInteractor carsInteractor;

    private Button bt_name;
    private Button bt_mileage;
    private Button bt_volume;
    private Button bt_power;

    private boolean name = false;
    private boolean mileage = true;
    private boolean volume = true;
    private boolean power = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt_name = findViewById(R.id.bt_name);
        bt_mileage = findViewById(R.id.bt_mileage);
        bt_volume = findViewById(R.id.bt_volume);
        bt_power = findViewById(R.id.bt_power);

        bt_name.setOnClickListener(this);
        bt_mileage.setOnClickListener(this);
        bt_volume.setOnClickListener(this);
        bt_power.setOnClickListener(this);

        carsInteractor = new CarsInteractor();
        carsRecycler = findViewById(R.id.carsRecycler);
        carsRecycler.setLayoutManager(new LinearLayoutManager(this));
        carsAdapter = new CarsAdapter(carsInteractor.getCars());
        carsRecycler.setAdapter(carsAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_mileage:
                carsAdapter.updateAdapter(carsInteractor.sortCars(carsAdapter.getCarsList(), new MileageComparator(mileage)));
                if (mileage == true) mileage = false;
                else mileage = true;
                break;
            case R.id.bt_power:
                carsAdapter.updateAdapter(carsInteractor.sortCars(carsAdapter.getCarsList(), new PowerComparator(power)));
                if (power == true) power = false;
                else power = true;
                break;
            case R.id.bt_volume:
                carsAdapter.updateAdapter(carsInteractor.sortCars(carsAdapter.getCarsList(), new VolumeComporator(volume)));
                if (volume == true) volume = false;
                else volume = true;
                break;
            case R.id.bt_name:
                carsAdapter.updateAdapter(carsInteractor.sortCars(carsAdapter.getCarsList(), new NameComporator(name)));
                if (name == true) name = false;
                else name = true;
                break;

        }
    }
}
