package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterPreviewTimeList;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterTimeList;
import ru.dreamteam.goldse4enie.domain.TimeList;
import ru.dreamteam.goldse4enie.domain.TimeListItem;

public class PreviewActivityTL extends AppCompatActivity {

    private TimeList currentTimeList;
    private TimeListItem timeListItem;
    private ArrayList<String> timeStart ;
    private ArrayList<String> timeEnd;
    private ArrayList<String> place;
    private ArrayList<String> activity;
    private ArrayList<String> description;
    private ArrayList<String> date;
    private ArrayList<Integer> maxPeople;
    private  ArrayList<ArrayList<String>> peoples;
    private NumbersAdapterPreviewTimeList numbersAdapter;

    private LinearLayoutManager layoutManager;
    private RecyclerView numbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_t_l);
        Bundle arg = getIntent().getExtras();
        if(arg.getString("from") == "CTA") {
            currentTimeList = new TimeList((Integer) arg.getSerializable("item"), (String) arg.getSerializable("date"),
                    (Integer) arg.getSerializable("campNumber"), (String) arg.getSerializable("campType"), (ArrayList<TimeListItem>) arg.getSerializable("List"));

            Log.d("PREV", String.valueOf(numbersList));
        }
        else{
            timeStart = (ArrayList<String>) arg.getSerializable("timeStart");
            timeEnd =   (ArrayList<String>) arg.getSerializable("timeEnd");
            place =     (ArrayList<String>) arg.getSerializable("place");
            activity =  (ArrayList<String>) arg.getSerializable("activity");
            currentTimeList = new TimeList((Integer) arg.getSerializable("item"),(String) arg.getSerializable("date"),
                    (Integer) arg.getSerializable("campNumber"),(String) arg.getSerializable("campType"),(ArrayList<TimeListItem>) arg.getSerializable("List"));
        }
        numbersList = findViewById(R.id.rv_preview_list);
        layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        if(timeStart ==null) {

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
        }

        numbersAdapter = new NumbersAdapterPreviewTimeList(timeStart.size(), timeStart,
                timeEnd, place, activity,currentTimeList);
        numbersList.setAdapter(numbersAdapter);
    }

    @Override
    public void onBackPressed() {
        if (numbersAdapter.activity.size() > 0) {
            timeStart = new ArrayList<>();
            timeEnd = new ArrayList<>();
            activity = new ArrayList<>();
            place = new ArrayList<>();
            for (int i = 0; i < numbersAdapter.activity.size(); i++) {
                timeStart.add(currentTimeList.timeListArray.get(i).timeStart = numbersAdapter.timeStart.get(i));
                timeEnd.add(currentTimeList.timeListArray.get(i).timeEnd = numbersAdapter.timeEnd.get(i));
                activity.add(currentTimeList.timeListArray.get(i).name = numbersAdapter.activity.get(i));
                place.add(currentTimeList.timeListArray.get(i).place = numbersAdapter.place.get(i));
            }
            for (int i = numbersAdapter.activity.size() ; i < currentTimeList.timeListArray.size(); i++) {
                currentTimeList.timeListArray.remove(i);
            }
            Intent intent = new Intent(getApplicationContext(), CreateTLActivity.class);
            Log.d("TLTRANS", "im on back pressed");
            intent.putExtra("currentTL", currentTimeList);
            intent.putExtra("from", "prev");
            this.startActivity(intent);
        }
        else {
            Intent intent = new Intent(getApplicationContext(), CreateTLActivity.class);
            Log.d("TLTRANS", "im on back pressed");
            intent.putExtra("from", "prev1");
            this.startActivity(intent);
        }
    }
}
