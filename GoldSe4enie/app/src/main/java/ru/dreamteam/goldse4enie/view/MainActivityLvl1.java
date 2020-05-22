package ru.dreamteam.goldse4enie.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterLocalList;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterTimeList;
import ru.dreamteam.goldse4enie.domain.ActivityGlobalList;
import ru.dreamteam.goldse4enie.domain.ActivityLocalList;
import ru.dreamteam.goldse4enie.domain.TimeList;
import ru.dreamteam.goldse4enie.domain.User;
import ru.dreamteam.goldse4enie.getters.GetGlobalActivityList;
import ru.dreamteam.goldse4enie.getters.GetLocalActivityList;

public class MainActivityLvl1 extends AppCompatActivity implements View.OnClickListener {
    private ImageButton time_list;
    private ImageButton local_activity;
    private ImageButton global_activity;
    private ImageButton settings;
    private TextView app_name;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    User CurrentUser;
    TimeList CurrentTL;
    ActivityLocalList CurrentLAL;
    ActivityGlobalList CurrentGAL;

    ValueEventListener TLListener;
    ValueEventListener LALListener;
    ValueEventListener GALListener;

    ArrayList time_timeListStart ;
    ArrayList time_timeListEnd ;
    ArrayList place_timeList ;
    ArrayList activity_timeList ;
    RecyclerView numbersList;

    ServiceConnection SerConn;

    ArrayList<String> timeStart ;
    ArrayList<String> timeEnd;
    ArrayList<String> place;
    ArrayList<String> activity;

    Intent intent;

    String TimeNowMinute ;
    String TimeNowHour   ;
    String DateNowDay    ;
    String DateNowMonth  ;

    @SuppressLint("RestrictedApi")
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

        String[] a = updateTime();
        TimeNowMinute = a[0];
        TimeNowHour   = a[1];
        DateNowDay    = a[2];
        DateNowMonth  = a[3];

        Bundle arguments = getIntent().getExtras();
        CurrentUser = (User) arguments.getSerializable(User.class.getSimpleName());

        app_name.setText(CurrentUser.name);

        numbersList = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        TLListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                CurrentTL = dataSnapshot.getValue(TimeList.class);

                Log.d("TLGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TLGET", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        LALListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                CurrentLAL = dataSnapshot.getValue(ActivityLocalList.class);

                Log.d("LALGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("LALGET", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        GALListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                CurrentGAL = dataSnapshot.getValue(ActivityGlobalList.class);

                Log.d("GALGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("GALGET", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        myRef.child("Time List").child("" + CurrentUser.campNumber).child(DateNowDay).addValueEventListener(TLListener);
        myRef.child("LA Time List").child("" + CurrentUser.campNumber).child(DateNowDay).addValueEventListener(LALListener);
        myRef.child("GA Time List").child(DateNowDay).addValueEventListener(GALListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_time_list:

                RecyclerView numbersList = findViewById(R.id.rv_list);

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                numbersList.setLayoutManager(layoutManager);

                numbersList.setHasFixedSize(true);

                timeStart = new ArrayList<>();
                timeEnd = new ArrayList<>();
                activity = new ArrayList<>();
                place = new ArrayList<>();

                for (int i = 0; i < CurrentTL.timeListArray.size() ; i++) {
                timeStart.add(CurrentTL.timeListArray.get(i).timeStart);
                timeEnd.add(CurrentTL.timeListArray.get(i).timeEnd);
                activity.add(CurrentTL.timeListArray.get(i).name);
                place.add(CurrentTL.timeListArray.get(i).place);
                }

                NumbersAdapterTimeList numbersAdapter = new NumbersAdapterTimeList(timeStart.size(), timeStart, timeEnd, place, activity);
                numbersList.setAdapter(numbersAdapter);
                break;

            case R.id.ib_global_activity:
                numbersList = findViewById(R.id.rv_list);

                layoutManager = new LinearLayoutManager(this);
                numbersList.setLayoutManager(layoutManager);

                numbersList.setHasFixedSize(true);

                timeStart = new ArrayList<>();
                timeEnd = new ArrayList<>();
                activity = new ArrayList<>();
                place = new ArrayList<>();

                for (int i = 0; i < CurrentGAL.List.size() ; i++) {
                    timeStart.add(CurrentGAL.List.get(i).timeStart);
                    timeEnd.add(CurrentGAL.List.get(i).timeEnd);
                    activity.add(CurrentGAL.List.get(i).name);
                    place.add(CurrentGAL.List.get(i).place);
                }

                NumbersAdapterLocalList numbersAdapterKek = new NumbersAdapterLocalList(timeStart.size(), timeStart, timeEnd, place, activity);
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

                //NumbersAdapterLocalList numbersAdapterKk = new NumbersAdapterLocalList(
                //        time_activityList_.length,
                //        time_activityList_, place_activityList_, activity_activityList_);
               // numbersList.setAdapter(numbersAdapterKk);
                break;
            case R.id.bt_settings:
                Intent intentK = new Intent(v.getContext(), SettingsActivity.class);
                v.getContext().startActivity(intentK);
                break;

        }
    }

    public static String[] updateTime() {
        Date currentDate = new Date();
        String [] a = new String[4];
        DateFormat timeFormatMinute = new SimpleDateFormat("mm", Locale.getDefault());
        a[0] = timeFormatMinute.format(currentDate);

        DateFormat timeFormatHoure = new SimpleDateFormat("HH", Locale.getDefault());
        a[1] = timeFormatHoure.format(currentDate);

        DateFormat dateFormatDay = new SimpleDateFormat("dd", Locale.getDefault());
        a[2] = dateFormatDay.format(currentDate);

        DateFormat dateFormatMonth = new SimpleDateFormat("MM", Locale.getDefault());
        a[3] = dateFormatMonth.format(currentDate);

        return a;
    }
}
