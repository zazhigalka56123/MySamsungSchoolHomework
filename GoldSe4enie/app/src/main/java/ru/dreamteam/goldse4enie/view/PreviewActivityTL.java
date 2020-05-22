package ru.dreamteam.goldse4enie.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.adapters.NumbersAdapterTimeList;
import ru.dreamteam.goldse4enie.domain.TimeList;
import ru.dreamteam.goldse4enie.domain.TimeListItem;

public class PreviewActivityTL extends AppCompatActivity {

    private TimeList timeListPreview;
    private TimeListItem timeListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_t_l);
        Bundle arg = getIntent().getExtras();
//        timeListPreview = new TimeList(
//                arg.getInt("item"),
//                arg.getString("date"),
//                arg.getInt("campNumber"),
//                arg.getString("campType"),
//                arg.("timeListArray")); // Надо как то передать arrayList<TimeListItem>
//
//        RecyclerView numbersList = findViewById(R.id.rv_preview_list);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        numbersList.setLayoutManager(layoutManager);
//
//        numbersList.setHasFixedSize(true);
//
//        NumbersAdapterTimeList numbersAdapter = new NumbersAdapterTimeList(
//                timeListPreview.name.size(),
//                timeListPreview.timeStart,
//                timeListPreview.timeEnd,
//                timeListPreview.place,
//                timeListPreview.name);
//        numbersList.setAdapter(numbersAdapter);
    }
}
