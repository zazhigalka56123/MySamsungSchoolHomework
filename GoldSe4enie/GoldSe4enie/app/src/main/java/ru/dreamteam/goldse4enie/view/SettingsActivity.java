package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import ru.dreamteam.goldse4enie.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        back = findViewById(R.id.bt_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_back:
                Intent intent = new Intent(v.getContext(), MainActivityLvl1.class);
                v.getContext().startActivity(intent);
        }
    }
}
