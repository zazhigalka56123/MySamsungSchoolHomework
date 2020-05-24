package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.User;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton bt_home;

    private TextView tv_age;
    private TextView tv_name;
    private TextView tv_campNumber;
    private TextView tv_campType;
    private TextView tv_appname;

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_home:
                Intent intent = new Intent(v.getContext(), MainActivityLvl1.class);
                v.getContext().startActivity(intent);
        }
    }

    public void init(){
        Bundle arguments = getIntent().getExtras();
        currentUser = (User) arguments.getSerializable(User.class.getSimpleName());

        bt_home = findViewById(R.id.bt_home);
        bt_home.setOnClickListener(this);

        tv_age = findViewById(R.id.tv_age);
        tv_name = findViewById(R.id.tv_name);
        tv_campNumber = findViewById(R.id.tv_campNumber);
        tv_campType = findViewById(R.id.tv_campType);
        tv_appname = findViewById(R.id.tv_appname);

        tv_age       .setText("Возраст: " + currentUser.age);
        tv_name      .setText("Имя: " + currentUser.name);
        tv_campNumber.setText("Отряд: " + currentUser.campNumber);
        tv_campType  .setText("Направление: " + currentUser.campType);
        tv_appname  .setText(currentUser.name);


    }
}
