package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.dreamteam.goldse4enie.R;

public class MainActivityLvl2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lvl2);

        Button buttonTimeList       = findViewById(R.id.buttonTimeList);
        Button buttonLocalActivity  = findViewById(R.id.buttonLocalActivity);
        Button buttonGlobalActivity = findViewById(R.id.buttonGlobalActivity);

        buttonTimeList.      setOnClickListener(this);
        buttonLocalActivity. setOnClickListener(this);
        buttonGlobalActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonTimeList:
                Intent intent1 = new Intent(v.getContext(), CreateTLActivity.class);
                v.getContext().startActivity(intent1);
                break;
        }
    }
}
