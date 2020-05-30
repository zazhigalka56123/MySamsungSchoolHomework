package ru.dreamteam.goldse4enie.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.ActivityLocal;
import ru.dreamteam.goldse4enie.domain.User;
import ru.dreamteam.goldse4enie.view.ListPeopleActivity;
import ru.dreamteam.goldse4enie.view.MoreInfoActivity;
import ru.dreamteam.goldse4enie.view.MoreInfoActivityP;

public class NumbersAdapterCheckPeopleLocal extends RecyclerView.Adapter<NumbersAdapterCheckPeopleLocal.NumberViewHolder> {


    private int numberItems;

    private ArrayList<String> timeStart;
    private ArrayList<String> timeEnd;
    private ArrayList<String> place;
    private ArrayList<String> activity;
    private ArrayList<String> description;
    private ArrayList<String> date;
    private ArrayList<ArrayList<String>> peoples;
    private ArrayList<Integer> maxPeople;

    private TextView tv_activity;
    private TextView tv_place;
    private TextView tv_time_start;
    private TextView tv_time_end;

    private User currentUser;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    private Button bt_people;
    private ArrayList<Button> bt_go_l;
    private Button bt_more_info;
    private ArrayList<Boolean> go;
    public int index = 0;
    Context context;

    public NumbersAdapterCheckPeopleLocal(int numberItems, ArrayList<String> timeStart, ArrayList<String> timeEnd,
                                   ArrayList<String> place, ArrayList<String> activity, ArrayList<String> description,
                                   ArrayList<String> date, ArrayList<ArrayList<String>> peoples, User currentUser,
                                   ArrayList<Integer> maxPeople,Context context) {
        this.peoples = new ArrayList<>();
        bt_go_l = new ArrayList<>();
        go = new ArrayList<>();
        this.numberItems = numberItems;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.place = place;
        this.activity = activity;
        this.description = description;
        this.date = date;
        this.peoples = peoples;
        this.currentUser = currentUser;
        this.maxPeople = maxPeople;

        this.context = context;

        Log.d("GOWRT",String.valueOf(peoples));
    }

    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_check_people;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        NumberViewHolder viewHolder = new NumbersAdapterCheckPeopleLocal.NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }
    class NumberViewHolder extends RecyclerView.ViewHolder {
        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_activity   = itemView.findViewById(R.id.tv_activity);
            tv_place      = itemView.findViewById(R.id.tv_place);
            tv_time_start = itemView.findViewById(R.id.tv_time_start);
            tv_time_end   = itemView.findViewById(R.id.tv_time_end);
            bt_more_info  = itemView.findViewById(R.id.bt_more_info);
            bt_people     = itemView.findViewById(R.id.bt_people);
        }
        void bind(final int listIndex) {
            Log.d("LOLOLOLOLOL", String.valueOf(timeStart));
            Log.d("LOLOLOLOLOL", String.valueOf(timeEnd));
            Log.d("LOLOLOLOLOL", String.valueOf(place));
            Log.d("LOLOLOLOLOL", String.valueOf(activity));
            bt_more_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentlvl1 = new Intent(v.getContext(), MoreInfoActivityP.class);
                    intentlvl1.putExtra("date"        ,date       .get(listIndex ));
                    intentlvl1.putExtra("timeStart"   ,timeStart  .get(listIndex));
                    intentlvl1.putExtra("timeEnd"     ,timeEnd    .get(listIndex));
                    intentlvl1.putExtra("activity"    ,activity   .get(listIndex));
                    intentlvl1.putExtra("place"       ,place      .get(listIndex));
                    intentlvl1.putExtra("description" ,description.get(listIndex));
                    intentlvl1.putExtra("peoples" , peoples);
                    intentlvl1.putExtra("maxPeople" , maxPeople);
                    intentlvl1.putExtra("currentUser" , currentUser);
                    intentlvl1.putExtra("index",listIndex);
                    intentlvl1.putExtra("go",go);
                    v.getContext().startActivity(intentlvl1);
                }
            });
            bt_people.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ListPeopleActivity.class);
                    intent.putExtra("peoples",peoples.get(listIndex));
                    v.getContext().startActivity(intent);
                }
            });

            tv_time_start.setText(timeStart.get(listIndex));
            tv_time_end  .setText(timeEnd.get(listIndex));
            tv_place     .setText(place.get(listIndex));
            tv_activity  .setText(activity.get(listIndex));
            index = listIndex;
        }


    }
}

