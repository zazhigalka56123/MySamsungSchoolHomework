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
import ru.dreamteam.goldse4enie.domain.ActivityGlobal;
import ru.dreamteam.goldse4enie.domain.ActivityLocal;
import ru.dreamteam.goldse4enie.domain.User;

public class MoreInfoActivityG extends AppCompatActivity {

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

    private ArrayList<ActivityGlobal> List;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        init();
        if(go.get(listIndex) == true) bt_go.setText("Не Пойду");
        else bt_go.setText("Пойду");
    }

    public void init(){
        Bundle arguments = getIntent().getExtras();
        date        = (String) arguments.getSerializable("date");
        timeStart   = (String) arguments.getSerializable("timeStart");
        timeEnd     = (String) arguments.getSerializable("timeEnd");
        activity    = (String) arguments.getSerializable("activity");
        place       = (String) arguments.getSerializable("place");
        description = (String) arguments.getSerializable("description");
        listIndex       = (Integer)arguments.getSerializable("index");
        peoples     = (ArrayList<ArrayList<String>>)arguments.getSerializable("peoples");
        maxPeople   = (ArrayList<Integer>)arguments.getSerializable("maxPeople");
        currentUser = (User)   arguments.getSerializable("currentUser");
        go          = (ArrayList<Boolean>)arguments.getSerializable("go");

        tv_date       =findViewById(R.id.tv_date);
        tv_time       =findViewById(R.id.tv_time);
        tv_name       =findViewById(R.id.tv_name);
        tv_place      =findViewById(R.id.tv_place);
        tv_description=findViewById(R.id.tv_description);
        bt_go         =findViewById(R.id.bt_remove);

        tv_date       .setText(date);
        tv_time       .setText(timeStart + " - " + timeEnd);
        tv_name       .setText(activity);
        tv_place      .setText(place);
        tv_description.setText(description);

        final GenericTypeIndicator<ArrayList<ActivityGlobal>> ALTY = new GenericTypeIndicator<ArrayList<ActivityGlobal>>() {
        };
        myRef = database.getReference("Global Activity")
                .child(date.substring(3,5))
                .child(date.substring(0,2));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List = dataSnapshot.getValue(ALTY);
                Log.d("GOWRTMIA","Im on dataChange");
                for (int i = 0; i < List.size(); i++) {
                    peoples.remove(i);
                    peoples.add(i,List.get(i).peoples);
                }
                Log.d("GOWRTMIA",String.valueOf(peoples));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GOWRTMIA","" + listIndex);
                if(go.get(listIndex)) {
                    go.remove(listIndex);
                    go.add(listIndex,false);
                    bt_go.setText("Пойду");
                    peoples.get(listIndex).remove(currentUser.name);
                    List.get(listIndex).peoples = peoples.get(listIndex);
                    myRef.setValue(List);
                }
                else{
                    if ((peoples.get(listIndex) == null || peoples.get(listIndex).size() == 0)) {
                        go.remove(listIndex);
                        go.add(listIndex,true);
                        bt_go.setText("Не Пойду");
                        peoples.remove(listIndex);
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(currentUser.name);
                        peoples.add(listIndex,temp);
                        List.get(listIndex).peoples = peoples.get(listIndex);
                        myRef.setValue(List);
                    }
                    else if((!peoples.get(listIndex).contains(currentUser.name) && peoples.get(listIndex).size() + 1 <= maxPeople.get(listIndex))||peoples.get(listIndex).contains(currentUser.name)){
                        go.remove(listIndex);
                        go.add(listIndex,true);
                        bt_go.setText("Не Пойду");
                        peoples.get(listIndex).add(currentUser.name);
                        List.get(listIndex).peoples = peoples.get(listIndex);
                        myRef.setValue(List);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Места нет", Toast.LENGTH_SHORT).show();
                    }
                }
                Log.d("GOWRTMIA",String.valueOf(peoples));
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intentlvl1 = new Intent(this, MainActivityLvl1.class);
        intentlvl1.putExtra(User.class.getSimpleName(),  currentUser);
        intentlvl1.putExtra("from",  "global");
        this.startActivity(intentlvl1);
    }
}
