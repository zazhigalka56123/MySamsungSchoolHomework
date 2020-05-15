package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.dreamteam.goldse4enie.R;

public class MainActivityLvl2 extends AppCompatActivity implements View.OnClickListener {
    private Button buttonTimeList;
    private Button buttonLocalActivity;
    private Button buttonGlobalActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lvl2);

        buttonTimeList       = findViewById(R.id.buttonTimeList);
        buttonLocalActivity  = findViewById(R.id.buttonLocalActivity);
        buttonGlobalActivity = findViewById(R.id.buttonGlobalActivity);

        buttonTimeList.      setOnClickListener(this);
        buttonLocalActivity. setOnClickListener(this);
        buttonGlobalActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonTimeList:
                Intent intentTimeList = new Intent(v.getContext(), CreateTLActivity.class);
                v.getContext().startActivity(intentTimeList);
                break;
            case R.id.buttonGlobalActivity:
                Intent intentGlobalActivity = new Intent(v.getContext(), CreateGlobalActivity.class);
                v.getContext().startActivity(intentGlobalActivity);
                break;
        }
    }
}
