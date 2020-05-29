package ru.dreamteam.goldse4enie.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterCheckPeopleLocal;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterGlobalList;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterLocalList;
import ru.dreamteam.goldse4enie.domain.ActivityGlobal;
import ru.dreamteam.goldse4enie.domain.ActivityLocal;
import ru.dreamteam.goldse4enie.domain.TimeList;
import ru.dreamteam.goldse4enie.domain.User;

public class CheckPeopleActivty extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private User currentUser;
    private TimeList currentTimeList;
    private ArrayList<ActivityLocal> currentLocalActivityList;
    private ArrayList<ActivityGlobal> currentGlobalActivityList;


    private boolean receivedDataLocalActivity  = false;
    private boolean receivedDataGlobalActivity = false;
    private SwipeRefreshLayout swipeRefresh_lvl1;


    private boolean setLocalActivityFlag  = false;
    private boolean setGlobalActivityFlag = false;

    private ValueEventListener localActivityListener;
    private ValueEventListener globalActivityListener;

    private String DateNowDay    ;
    private String DateNowMonth  ;

    private ArrayList<String> timeStart ;
    private ArrayList<String> timeEnd;
    private ArrayList<String> place;
    private ArrayList<String> activity;
    private ArrayList<String> description;
    private ArrayList<String> date;
    private ArrayList<Integer> maxPeople;
    private  ArrayList<ArrayList<String>> peoples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_people);

        init();
    }


    public void init(){
        Bundle arguments = getIntent().getExtras();
        currentUser = (User) arguments.getSerializable(User.class.getSimpleName());

        Log.d("KODRED", currentUser.campType);
        Log.d("KODRED", String.valueOf(currentUser.campNumber));

        swipeRefresh_lvl1 = findViewById(R.id.swipeRefresh_lvl1);
        String[] a = updateTime();

        DateNowDay    = a[2];
        DateNowMonth  = a[3];

        final GenericTypeIndicator<ArrayList<ActivityGlobal>> AGTY = new GenericTypeIndicator<ArrayList<ActivityGlobal>>() {
        };
        final GenericTypeIndicator<ArrayList<ActivityLocal>> ALTY = new GenericTypeIndicator<ArrayList<ActivityLocal>>() {
        };



        localActivityListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentLocalActivityList = dataSnapshot.getValue(ALTY);
                receivedDataLocalActivity = currentLocalActivityList != null;
                Log.d("LALGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LALGET", "loadPost:onCancelled", databaseError.toException());
            }
        };

        globalActivityListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentGlobalActivityList = dataSnapshot.getValue(AGTY);
                receivedDataGlobalActivity = currentGlobalActivityList != null;
                Log.d("GALGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("GALGET", "loadPost:onCancelled", databaseError.toException());
            }

        };


        myRef
                .child("Local Activity")
                .child(currentUser.campType)
                .child(String.valueOf(currentUser.campNumber))
                .child(DateNowMonth)
                .child(DateNowDay)
                .addValueEventListener(localActivityListener);



        myRef
                .child("Global Activity")
                .child(DateNowMonth)
                .child(DateNowDay)
                .addValueEventListener(globalActivityListener);

        swipeRefresh_lvl1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (setLocalActivityFlag == true){
                    setLocalActivity();
                }else if (setGlobalActivityFlag == true){
                    setGlobalActivity();
                }
                swipeRefresh_lvl1.setRefreshing(false);
            }
        });
        setLocalActivity();

    }

    public void setLocalActivity(){
        setGlobalActivityFlag = false;
        setLocalActivityFlag  = true;
        RecyclerView numbersList = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        timeStart = new ArrayList<>();
        timeEnd = new ArrayList<>();
        activity = new ArrayList<>();
        place = new ArrayList<>();
        description = new ArrayList<>();
        date = new ArrayList<>();
        peoples = new ArrayList<>();
        maxPeople = new ArrayList<>();

        if (currentLocalActivityList != null){

            Log.d("LOLOLOLOLOL", String.valueOf(currentLocalActivityList.get(0).timeStart));
            Log.d("LOLOLOLOLOL", String.valueOf(currentLocalActivityList.get(0).timeEnd));
            Log.d("LOLOLOLOLOL", String.valueOf(currentLocalActivityList.get(0).name));
            Log.d("LOLOLOLOLOL", String.valueOf(currentLocalActivityList.get(0).place));
            Log.d("LOLOLOLOLOL", String.valueOf(currentLocalActivityList.get(0).description));
            Log.d("LOLOLOLOLOL", String.valueOf(currentLocalActivityList.get(0).date));
            Log.d("LOLOLOLOLOL", String.valueOf(currentLocalActivityList.size()));
            Log.d("LOLOLOLOLOL", "))))))))))))))))");
            for (int i = 0; i < currentLocalActivityList.size(); i++) {
                timeStart  .add(currentLocalActivityList.get(i).timeStart);
                timeEnd    .add(currentLocalActivityList.get(i).timeEnd);
                activity   .add(currentLocalActivityList.get(i).name);
                place      .add(currentLocalActivityList.get(i).place);
                description.add(currentLocalActivityList.get(i).description);
                date       .add(currentLocalActivityList.get(i).date);
                peoples    .add(currentLocalActivityList.get(i).peoples);
                maxPeople  .add(currentLocalActivityList.get(i).maxPeople);
            }

            NumbersAdapterCheckPeopleLocal numbersAdapterKk = new NumbersAdapterCheckPeopleLocal(currentLocalActivityList.size(),
                    timeStart, timeEnd, place, activity, description, date,peoples,currentUser,maxPeople,currentLocalActivityList,this);
            numbersList.setAdapter(numbersAdapterKk);
        }else{
            Log.d("KODRED", "fuck");
        }
    }

    public void  setGlobalActivity(){
        setGlobalActivityFlag = true;
        setLocalActivityFlag  = false;
        RecyclerView numbersList = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        timeStart = new ArrayList<>();
        timeEnd = new ArrayList<>();
        activity = new ArrayList<>();
        place = new ArrayList<>();
        description = new ArrayList<>();
        date = new ArrayList<>();
        peoples = new ArrayList<>();
        maxPeople = new ArrayList<>();

        for (int i = 0; i < currentGlobalActivityList.size(); i++) {
            timeStart  .add(currentGlobalActivityList.get(i).timeStart);
            timeEnd    .add(currentGlobalActivityList.get(i).timeEnd);
            activity   .add(currentGlobalActivityList.get(i).name);
            place      .add(currentGlobalActivityList.get(i).place);
            description.add(currentGlobalActivityList.get(i).description);
            date       .add(currentGlobalActivityList.get(i).date);
            peoples    .add(currentGlobalActivityList.get(i).peoples);
            maxPeople  .add(currentGlobalActivityList.get(i).maxPeople);
        }

        NumbersAdapterGlobalList numbersAdapterKek = new NumbersAdapterGlobalList(timeStart.size()
                , timeStart, timeEnd, place, activity,description,date,peoples,currentUser,maxPeople,currentGlobalActivityList,this);
        numbersList.setAdapter(numbersAdapterKek);
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
