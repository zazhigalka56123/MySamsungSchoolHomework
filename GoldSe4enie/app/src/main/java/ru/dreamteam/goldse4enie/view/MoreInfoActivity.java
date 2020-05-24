package ru.dreamteam.goldse4enie.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.ActivityLocal;

public class MoreInfoActivity extends AppCompatActivity {

    private ActivityLocal activityLocal;

    private String date;
    private String timeStart;
    private String timeEnd;
    private String activity;
    private String place;
    private String description;

    private TextView tv_date;
    private TextView tv_time;
    private TextView tv_name;
    private TextView tv_place;
    private TextView tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        init();
    }

    public void init(){
        Bundle arguments = getIntent().getExtras();
        date        = (String) arguments.getSerializable("date");
        timeStart   = (String) arguments.getSerializable("timeStart");
        timeEnd     = (String) arguments.getSerializable("timeEnd");
        activity    = (String) arguments.getSerializable("activity");
        place       = (String) arguments.getSerializable("place");
        description = (String) arguments.getSerializable("description");

        tv_date       .findViewById(R.id.tv_date);
        tv_time       .findViewById(R.id.tv_time);
        tv_name       .findViewById(R.id.tv_name);
        tv_place      .findViewById(R.id.tv_place);
        tv_description.findViewById(R.id.tv_description);


        tv_date       .setText(date);
        tv_time       .setText(timeStart + " - " + timeEnd);
        tv_name       .setText(activity);
        tv_place      .setText(place);
        tv_description.setText(description);
    }
}
