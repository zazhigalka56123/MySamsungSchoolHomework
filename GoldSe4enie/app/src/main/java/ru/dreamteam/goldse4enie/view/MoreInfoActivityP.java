package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.ActivityLocal;
import ru.dreamteam.goldse4enie.domain.User;

public class MoreInfoActivityP extends AppCompatActivity {

    private ActivityLocal activityLocal;

    private String date;
    private String timeStart;
    private String timeEnd;
    private String activity;
    private String place;
    private String description;
    private ArrayList<ArrayList<String>> peoples;
    private int listIndex;
    private ArrayList<Integer> maxPeople;
    private User currentUser;
    private ArrayList<Boolean> go;

    private TextView tv_date;
    private TextView tv_time;
    private TextView tv_name;
    private TextView tv_place;
    private TextView tv_description;
    private Button   bt_go;

    private ArrayList<ActivityLocal> List;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        init();
        bt_go.setText("OK");
    }

    public void init() {
        Bundle arguments = getIntent().getExtras();
        date = (String) arguments.getSerializable("date");
        timeStart = (String) arguments.getSerializable("timeStart");
        timeEnd = (String) arguments.getSerializable("timeEnd");
        activity = (String) arguments.getSerializable("activity");
        place = (String) arguments.getSerializable("place");
        description = (String) arguments.getSerializable("description");
        listIndex = (Integer) arguments.getSerializable("index");
        peoples = (ArrayList<ArrayList<String>>) arguments.getSerializable("peoples");
        maxPeople = (ArrayList<Integer>) arguments.getSerializable("maxPeople");
        currentUser = (User) arguments.getSerializable("currentUser");
        go = (ArrayList<Boolean>) arguments.getSerializable("go");

        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        tv_name = findViewById(R.id.tv_name);
        tv_place = findViewById(R.id.tv_place);
        tv_description = findViewById(R.id.tv_description);
        bt_go = findViewById(R.id.bt_remove);

        tv_date.setText(date);
        tv_time.setText(timeStart + " - " + timeEnd);
        tv_name.setText(activity);
        tv_place.setText(place);
        tv_description.setText(description);

        final GenericTypeIndicator<ArrayList<ActivityLocal>> ALTY = new GenericTypeIndicator<ArrayList<ActivityLocal>>() {
        };


        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckPeopleActivty.class);
                intent.putExtra(User.class.getSimpleName(), currentUser);
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
         //super.onBackPressed();
        Intent intentlvl1 = new Intent(this, MainActivityLvl1.class);
        intentlvl1.putExtra(User.class.getSimpleName(),  currentUser);
        intentlvl1.putExtra("from",  "local");
        this.startActivity(intentlvl1);
    }
}
