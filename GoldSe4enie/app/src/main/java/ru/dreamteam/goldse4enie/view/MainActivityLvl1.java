package ru.dreamteam.goldse4enie.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class MainActivityLvl1 extends AppCompatActivity implements View.OnClickListener {
    private ImageButton time_list;
    private ImageButton local_activity;
    private ImageButton global_activity;
    private ImageButton settings;
    private TextView app_name;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private User currentUser;
    private TimeList currentTimeList;
    private ActivityLocalList currentLocalActivityList;
    private ActivityGlobalList currentGlobalActivityList;

    private ValueEventListener timeListListener;
    private ValueEventListener localActivityListener;
    private ValueEventListener globalActivityListener;

    private ArrayList time_timeListStart ;
    private ArrayList time_timeListEnd ;
    private ArrayList place_timeList ;
    private ArrayList activity_timeList ;
    private RecyclerView numbersList;
    private SwipeRefreshLayout swipeRefresh_lvl1;

    private boolean receivedDataTimeList       = true;
    private boolean receivedDataLocalActivity  = true;
    private boolean receivedDataGlobalActivity = true;

    private LinearLayoutManager layoutManager;

    private ServiceConnection SerConn;

    private ArrayList<String> timeStart ;
    private ArrayList<String> timeEnd;
    private ArrayList<String> place;
    private ArrayList<String> activity;

    private String TimeNowMinute ;
    private String TimeNowHour   ;
    private String DateNowDay    ;
    private String DateNowMonth  ;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lvl1);

        init();

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
                if (receivedDataTimeList == true) {
                    RecyclerView numbersList = findViewById(R.id.rv_list);

                    layoutManager = new LinearLayoutManager(this);
                    numbersList.setLayoutManager(layoutManager);

                    numbersList.setHasFixedSize(true);

                    timeStart = new ArrayList<>();
                    timeEnd = new ArrayList<>();
                    activity = new ArrayList<>();
                    place = new ArrayList<>();

                    for (int i = 0; i < currentTimeList.timeListArray.size(); i++) {
                        timeStart.add(currentTimeList.timeListArray.get(i).timeStart);
                        timeEnd.add(currentTimeList.timeListArray.get(i).timeEnd);
                        activity.add(currentTimeList.timeListArray.get(i).name);
                        place.add(currentTimeList.timeListArray.get(i).place);
                    }

                    NumbersAdapterTimeList numbersAdapter = new NumbersAdapterTimeList(timeStart.size(), timeStart, timeEnd, place, activity);
                    numbersList.setAdapter(numbersAdapter);
                }else{
                    Toast.makeText(this, "Отсутствуют данные( Напомните своему вожатому!", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.ib_global_activity:
                if (receivedDataGlobalActivity == true) {
                    numbersList = findViewById(R.id.rv_list);

                    layoutManager = new LinearLayoutManager(this);
                    numbersList.setLayoutManager(layoutManager);

                    numbersList.setHasFixedSize(true);

                    timeStart = new ArrayList<>();
                    timeEnd = new ArrayList<>();
                    activity = new ArrayList<>();
                    place = new ArrayList<>();

                    for (int i = 0; i < currentGlobalActivityList.List.size(); i++) {
                        timeStart.add(currentGlobalActivityList.List.get(i).timeStart);
                        timeEnd.add(currentGlobalActivityList.List.get(i).timeEnd);
                        activity.add(currentGlobalActivityList.List.get(i).name);
                        place.add(currentGlobalActivityList.List.get(i).place);
                    }

                    NumbersAdapterLocalList numbersAdapterKek = new NumbersAdapterLocalList(timeStart.size(), timeStart, timeEnd, place, activity);
                    numbersList.setAdapter(numbersAdapterKek);
                }else {
                    Toast.makeText(this, "Отсутствуют мероприятия( Что то здесь не так...", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.ib_local_activity:
                if (receivedDataLocalActivity == true) {
                    numbersList = findViewById(R.id.rv_list);
                    //                    //
                    //                    //                    layoutManager = new LinearLayoutManager(this);
                    //                    //                    numbersList.setLayoutManager(layoutManager);
                    //                    //
                    //                    //                    numbersList.setHasFixedSize(true);
                    //                    //
                    //                    //                    GetLocalActivityList getlocalActivityList = new GetLocalActivityList();
                    //                    //
                    //                    //                    String[] time_activityList_ = getlocalActivityList.getTime();
                    //                    //                    String[] place_activityList_ = getlocalActivityList.getPlace();
                    //                    //                    String[] activity_activityList_ = getlocalActivityList.getActivity();
                    //                    //
                    //                    //                    //NumbersAdapterLocalList numbersAdapterKk = new NumbersAdapterLocalList(
                    //                    //                    //        time_activityList_.length,
                    //                    //                    //        time_activityList_, place_activityList_, activity_activityList_);
                    //                    //                   // numbersList.setAdapter(numbersAdapterKk);
                }else {
                    Toast.makeText(this, "Отсутствуют мероприятия( Организуйте их сами и сообщите вожатому!", Toast.LENGTH_SHORT).show();
                }
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
    
    public void init(){

        time_list         = findViewById(R.id.ib_time_list);
        local_activity    = findViewById(R.id.ib_local_activity);
        global_activity   = findViewById(R.id.ib_global_activity);
        settings          = findViewById(R.id.bt_settings);
        app_name          = findViewById(R.id.textView2);
        numbersList       = findViewById(R.id.rv_list);
        swipeRefresh_lvl1 = findViewById(R.id.swipeRefresh_lvl1);

        time_list        .setOnClickListener(this);
        local_activity   .setOnClickListener(this);
        global_activity  .setOnClickListener(this);
        settings         .setOnClickListener(this);
        numbersList      .setHasFixedSize(true);

        String[] a = updateTime();

        TimeNowMinute = a[0];
        TimeNowHour   = a[1];
        DateNowDay    = a[2];
        DateNowMonth  = a[3];

        Bundle arguments = getIntent().getExtras();
        currentUser = (User) arguments.getSerializable(User.class.getSimpleName());

        app_name.setText(currentUser.name);

        try {
            myRef
                .child("Time List")
                .child(currentUser.campType)
                .child("" + currentUser.campNumber)
                .child(DateNowMonth)
                .child(DateNowDay)
                .addValueEventListener(timeListListener);

        } catch(NullPointerException e) {
            receivedDataTimeList = false;
            Toast.makeText(this, "Отсутствуют данные( Напомните своему вожатому!", Toast.LENGTH_SHORT).show();
        }

        try {
            Log.d("KEKEKEKKE", currentUser.campType);
            Log.d("KEKEKEKKE", String.valueOf(currentUser.campNumber));
            Log.d("KEKEKEKKE", DateNowMonth);
            Log.d("KEKEKEKKE", DateNowDay);
            myRef
                    .child("Local Activity")
                    .child(currentUser.campType)
                    .child(String.valueOf(currentUser.campNumber))
                    .child(DateNowMonth)
                    .child(DateNowDay)
                    .addValueEventListener(localActivityListener);
        } catch(NullPointerException e) {
            receivedDataLocalActivity = false;
        }

        try {
            myRef
                    .child("Global Activity")
                    .child(DateNowMonth)
                    .child(DateNowDay)
                    .addValueEventListener(globalActivityListener);

        }catch(NullPointerException e) {
            receivedDataGlobalActivity = false;
        }


        timeListListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                currentTimeList = dataSnapshot.getValue(TimeList.class);

                Log.d("TLGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TLGET", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        localActivityListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                currentLocalActivityList = dataSnapshot.getValue(ActivityLocalList.class);

                Log.d("LALGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("LALGET", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        globalActivityListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                currentGlobalActivityList = dataSnapshot.getValue(ActivityGlobalList.class);

                Log.d("GALGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("GALGET", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };


        swipeRefresh_lvl1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        numbersList.setLayoutManager(layoutManager);


        
    }

}
