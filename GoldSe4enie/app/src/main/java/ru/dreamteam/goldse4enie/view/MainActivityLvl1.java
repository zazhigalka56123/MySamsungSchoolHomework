package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterLocalList;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterTimeList;
import ru.dreamteam.goldse4enie.getters.GetGlobalActivityList;
import ru.dreamteam.goldse4enie.getters.GetLocalActivityList;
import ru.dreamteam.goldse4enie.getters.GetTimeList;

public class MainActivityLvl1 extends AppCompatActivity implements View.OnClickListener {
    private ImageButton time_list;
    private ImageButton local_activity;
    private ImageButton global_activity;
    private ImageButton settings;
    private TextView app_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lvl1);

        time_list = findViewById(R.id.ib_time_list);
        local_activity = findViewById(R.id.ib_local_activity);
        global_activity = findViewById(R.id.ib_global_activity);
        settings = findViewById(R.id.bt_settings);
        app_name = findViewById(R.id.textView2);

        time_list.setOnClickListener(this);
        local_activity.setOnClickListener(this);
        global_activity.setOnClickListener(this);
        settings.setOnClickListener(this);

        Intent intent = getIntent();
        app_name.setText(intent.getStringExtra("appname"));

        RecyclerView numbersList = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        GetTimeList getTimeList = new GetTimeList();

        String[] time_timeList = getTimeList.getTime();
        String[] place_timeList = getTimeList.getPlace();
        String[] activity_timeList = getTimeList.getActivity();

        NumbersAdapterTimeList numbersAdapter = new NumbersAdapterTimeList(
                time_timeList.length,
                time_timeList, place_timeList, activity_timeList);
        numbersList.setAdapter(numbersAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_time_list:
                RecyclerView numbersList = findViewById(R.id.rv_list);

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                numbersList.setLayoutManager(layoutManager);

                numbersList.setHasFixedSize(true);

                GetTimeList getTimeList = new GetTimeList();

                String[] time_timeList = getTimeList.getTime();
                String[] place_timeList = getTimeList.getPlace();
                String[] activity_timeList = getTimeList.getActivity();

                NumbersAdapterTimeList numbersAdapter = new NumbersAdapterTimeList(
                        time_timeList.length,
                        time_timeList, place_timeList, activity_timeList);
                numbersList.setAdapter(numbersAdapter);
                break;

            case R.id.ib_global_activity:
                numbersList = findViewById(R.id.rv_list);

                layoutManager = new LinearLayoutManager(this);
                numbersList.setLayoutManager(layoutManager);

                numbersList.setHasFixedSize(true);

                GetGlobalActivityList getGlobalActivityList = new GetGlobalActivityList();

                String[] time_activityList = getGlobalActivityList.getTime();
                String[] place_activityList = getGlobalActivityList.getPlace();
                String[] activity_activityList = getGlobalActivityList.getActivity();

                NumbersAdapterLocalList numbersAdapterKek = new NumbersAdapterLocalList(
                        time_activityList.length,
                        time_activityList, place_activityList, activity_activityList);
                numbersList.setAdapter(numbersAdapterKek);
                break;
            case R.id.ib_local_activity:
                numbersList = findViewById(R.id.rv_list);

                layoutManager = new LinearLayoutManager(this);
                numbersList.setLayoutManager(layoutManager);

                numbersList.setHasFixedSize(true);

                GetLocalActivityList getlocalActivityList = new GetLocalActivityList();

                String[] time_activityList_ = getlocalActivityList.getTime();
                String[] place_activityList_ = getlocalActivityList.getPlace();
                String[] activity_activityList_ = getlocalActivityList.getActivity();

                NumbersAdapterLocalList numbersAdapterKk = new NumbersAdapterLocalList(
                        time_activityList_.length,
                        time_activityList_, place_activityList_, activity_activityList_);
                numbersList.setAdapter(numbersAdapterKk);
                break;
            case R.id.bt_settings:
                Intent intentK = new Intent(v.getContext(), SettingsActivity.class);
                v.getContext().startActivity(intentK);
                break;

        }
    }
}
