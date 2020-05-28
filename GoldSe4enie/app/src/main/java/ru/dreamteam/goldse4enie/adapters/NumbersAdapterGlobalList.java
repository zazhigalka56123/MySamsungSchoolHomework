package ru.dreamteam.goldse4enie.adapters;

import android.app.Application;
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

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.ActivityGlobal;
import ru.dreamteam.goldse4enie.domain.ActivityLocal;
import ru.dreamteam.goldse4enie.domain.User;
import ru.dreamteam.goldse4enie.view.MainActivityLvl1;
import ru.dreamteam.goldse4enie.view.MoreInfoActivity;
import ru.dreamteam.goldse4enie.view.MoreInfoActivityG;

public class NumbersAdapterGlobalList extends RecyclerView.Adapter<NumbersAdapterGlobalList.NumberViewHolder> {

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
    private ArrayList<ActivityGlobal> List;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    private Button bt_go;
    private ArrayList<Button> bt_go_l;
    private Button bt_more_info;
    private ArrayList<Boolean> go;
    public int index = 0;

    Context context;

    public NumbersAdapterGlobalList(int numberItems, ArrayList<String> timeStart, ArrayList<String> timeEnd,
                                   ArrayList<String> place, ArrayList<String> activity, ArrayList<String> description,
                                   ArrayList<String> date, ArrayList<ArrayList<String>> peoples, User currentUser,
                                   ArrayList<Integer> maxPeople, ArrayList<ActivityGlobal> List,Context context) {
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
        this.List = List;
        this.context = context;

        Log.d("GOWRT",String.valueOf(peoples));

        for (int i = 0; i < getItemCount(); i++) {
            if(peoples.get(i) != null){
                if(peoples.get(i).contains(currentUser.name))go.add(true);
                else go.add(false);
            }else{
                go.add(false);
            }
        }
    }

    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final GenericTypeIndicator<ArrayList<ActivityGlobal>> ALTY = new GenericTypeIndicator<ArrayList<ActivityGlobal>>() {
        };
        myRef = database.getReference("Global Activity")
                .child(date.get(0).substring(3,5))
                .child(date.get(0).substring(0,2));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List = dataSnapshot.getValue(ALTY);
                Log.d("GOWRT","" + (List != null));
                Log.d("GOWRT","Im on dataChange");
                for (int i = 0; i < List.size(); i++) {
                    peoples.remove(i);
                    peoples.add(i,List.get(i).peoples);
                }
                Log.d("GOWRT",String.valueOf(peoples));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_activity;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        NumberViewHolder viewHolder = new NumberViewHolder(view);
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
            bt_go         = itemView.findViewById(R.id.bt_go);
            bt_go_l.add(bt_go);

        }
        void bind(final int listIndex) {
            Log.d("LOLOLOLOLOL", String.valueOf(timeStart));
            Log.d("LOLOLOLOLOL", String.valueOf(timeEnd));
            Log.d("LOLOLOLOLOL", String.valueOf(place));
            Log.d("LOLOLOLOLOL", String.valueOf(activity));
            bt_more_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentlvl1 = new Intent(v.getContext(), MoreInfoActivityG.class);
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
            bt_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("GOWRT","" + listIndex);
                    if(go.get(listIndex)) {
                        go.remove(listIndex);
                        go.add(listIndex,false);
                        bt_go_l.get(listIndex).setText("Пойду");
                        peoples.get(listIndex).remove(currentUser.name);
                        List.get(listIndex).peoples = peoples.get(listIndex);
                        myRef.setValue(List);
                    }
                    else{
                        if ((peoples.get(listIndex) == null || peoples.get(listIndex).size() == 0)) {
                            go.remove(listIndex);
                            go.add(listIndex,true);
                            bt_go_l.get(listIndex).setText("Не Пойду");
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
                            bt_go_l.get(listIndex).setText("Не Пойду");
                            peoples.get(listIndex).add(currentUser.name);
                            List.get(listIndex).peoples = peoples.get(listIndex);
                            myRef.setValue(List);
                        }
                        else{
                            Toast.makeText(context, "Места нет", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Log.d("GOWRT",String.valueOf(peoples));
                }
            });

            tv_time_start.setText(timeStart.get(listIndex));
            tv_time_end  .setText(timeEnd.get(listIndex));
            tv_place     .setText(place.get(listIndex));
            tv_activity  .setText(activity.get(listIndex));
            index = listIndex;
            if(go.get(listIndex) == true) bt_go.setText("Не Пойду");
            else bt_go.setText("Пойду");
        }


    }
}


