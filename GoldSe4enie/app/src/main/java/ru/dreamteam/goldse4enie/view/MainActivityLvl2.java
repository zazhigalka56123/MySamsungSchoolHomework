package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.User;

public class MainActivityLvl2 extends AppCompatActivity implements View.OnClickListener {
    private Button buttonTimeList;
    private Button buttonLocalActivity;
    private Button buttonGlobalActivity;
    private ImageButton buttonSettingsActivity;
    private User   currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lvl2);

        buttonTimeList       = findViewById(R.id.buttonTimeList);
        buttonLocalActivity  = findViewById(R.id.buttonLocalActivity);
        buttonGlobalActivity = findViewById(R.id.buttonGlobalActivity);
        buttonSettingsActivity = findViewById(R.id.bt_settings);
        buttonTimeList.setOnClickListener(this);
        buttonLocalActivity.setOnClickListener(this);
        buttonGlobalActivity.setOnClickListener(this);
        buttonSettingsActivity.setOnClickListener(this);

        Bundle arguments = getIntent().getExtras();
        currentUser = (User) arguments.getSerializable(User.class.getSimpleName());
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
            case R.id.buttonLocalActivity:
                Intent intentLocalActivity = new Intent(v.getContext(), CreateLocalActivity.class);
                v.getContext().startActivity(intentLocalActivity);
                break;
            case R.id.bt_settings:
                Intent intentK = new Intent(v.getContext(), SettingsActivity.class);
                intentK.putExtra(User.class.getSimpleName(), currentUser);
                v.getContext().startActivity(intentK);
                break;
        }
    }
}
