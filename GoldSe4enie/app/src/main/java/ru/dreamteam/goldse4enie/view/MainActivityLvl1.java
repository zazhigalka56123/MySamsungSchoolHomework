package ru.dreamteam.goldse4enie.view;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.content.ServiceConnection;
        import android.os.Bundle;
        import android.os.Handler;
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
        import com.google.firebase.database.GenericTypeIndicator;
        import com.google.firebase.database.ValueEventListener;

        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.Locale;

        import ru.dreamteam.goldse4enie.R;
        import ru.dreamteam.goldse4enie.adapters.NumbersAdapterGlobalList;
        import ru.dreamteam.goldse4enie.adapters.NumbersAdapterLocalList;
        import ru.dreamteam.goldse4enie.adapters.NumbersAdapterTimeList;
        import ru.dreamteam.goldse4enie.domain.ActivityGlobal;
        import ru.dreamteam.goldse4enie.domain.ActivityLocal;
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
    private ArrayList<ActivityLocal> currentLocalActivityList;
    private ArrayList<ActivityGlobal> currentGlobalActivityList;

    private ValueEventListener timeListListener;
    private ValueEventListener localActivityListener;
    private ValueEventListener globalActivityListener;

    private ArrayList time_timeListStart ;
    private ArrayList time_timeListEnd ;
    private ArrayList place_timeList ;
    private ArrayList activity_timeList ;
    private RecyclerView numbersList;
    private SwipeRefreshLayout swipeRefresh_lvl1;

    private boolean receivedDataTimeList       = false;
    private boolean receivedDataLocalActivity  = false;
    private boolean receivedDataGlobalActivity = false;

    private boolean setTimeListFlag       = true;
    private boolean setLocalActivityFlag  = false;
    private boolean setGlobalActivityFlag = false;

    private LinearLayoutManager layoutManager;

    private Bundle arguments;

    private ArrayList<String> timeStart ;
    private ArrayList<String> timeEnd;
    private ArrayList<String> place;
    private ArrayList<String> activity;
    private ArrayList<String> description;
    private ArrayList<String> date;
    private ArrayList<Integer> maxPeople;
    private  ArrayList<ArrayList<String>> peoples;

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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch((String) arguments.getSerializable("from")){
                    case "local":
                        local_activity.callOnClick();
                        break;
                    case "Main":
                        time_list.callOnClick();
                        break;
                    case "global":
                        global_activity.callOnClick();
                        break;
                }
            }
        },500);
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
                    setTimeList();
                }else{
                    Toast.makeText(this, "Отсутствуют данные( Напомните своему вожатому!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.ib_global_activity:
                if (receivedDataGlobalActivity == true) {
                    setGlobalActivity();
                }else {
                    Toast.makeText(this, "Отсутствуют мероприятия( Что то здесь не так...", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.ib_local_activity:
                if (receivedDataLocalActivity == true) {
                    setLocalActivity();
                }else {
                    Toast.makeText(this, "Отсутствуют мероприятия( Организуйте их сами и сообщите вожатому!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.bt_settings:
                Intent intentK = new Intent(v.getContext(), SettingsActivity.class);
                intentK.putExtra(User.class.getSimpleName(), currentUser);
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

        arguments = getIntent().getExtras();
        currentUser = (User) arguments.getSerializable(User.class.getSimpleName());

        app_name.setText(currentUser.name);

        final GenericTypeIndicator<ArrayList<ActivityGlobal>> AGTY = new GenericTypeIndicator<ArrayList<ActivityGlobal>>() {
        };
        final GenericTypeIndicator<ArrayList<ActivityLocal>> ALTY = new GenericTypeIndicator<ArrayList<ActivityLocal>>() {
        };

        timeListListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentTimeList = dataSnapshot.getValue(TimeList.class);
                receivedDataTimeList = currentTimeList != null;
                Log.d("TLGET","" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TLGET", "loadPost:onCancelled", databaseError.toException());
            }
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
                .child("Time List")
                .child(currentUser.campType)
                .child("" + currentUser.campNumber)
                .child(DateNowMonth)
                .child(DateNowDay)
                .addValueEventListener(timeListListener);

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
                if (setTimeListFlag == true){
                    setTimeList();
                }else if (setLocalActivityFlag == true){
                    setLocalActivity();
                }else if (setGlobalActivityFlag == true){
                    setGlobalActivity();
                }
                swipeRefresh_lvl1.setRefreshing(false);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        numbersList.setLayoutManager(layoutManager);
    }

    public void setTimeList(){
        setGlobalActivityFlag = false;
        setLocalActivityFlag  = false;
        setTimeListFlag       = true;
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

        NumbersAdapterTimeList numbersAdapter = new NumbersAdapterTimeList(timeStart.size(), timeStart,
                timeEnd, place, activity);
        numbersList.setAdapter(numbersAdapter);
    }

    public void setLocalActivity(){
        setGlobalActivityFlag = false;
        setLocalActivityFlag  = true;
        setTimeListFlag       = false;
        numbersList = findViewById(R.id.rv_list);

        layoutManager = new LinearLayoutManager(this);
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

        NumbersAdapterLocalList numbersAdapterKk = new NumbersAdapterLocalList(currentLocalActivityList.size(),
                timeStart, timeEnd, place, activity, description, date,peoples,currentUser,maxPeople,currentLocalActivityList,this);
        numbersList.setAdapter(numbersAdapterKk);
    }

    public void  setGlobalActivity(){
        setGlobalActivityFlag = true;
        setLocalActivityFlag  = false;
        setTimeListFlag       = false;
        numbersList = findViewById(R.id.rv_list);

        layoutManager = new LinearLayoutManager(this);
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
}
