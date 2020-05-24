package ru.dreamteam.goldse4enie.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.view.MainActivityLvl1;

public class NumbersAdapterLocalList extends RecyclerView.Adapter<NumbersAdapterLocalList.NumberViewHolder> {

    private int numberItems;

    private ArrayList<String> timeStart;
    private ArrayList<String> timeEnd;
    private ArrayList<String> place;
    private ArrayList<String> activity;
    private ArrayList<String> description;
    private ArrayList<String> date;

    private TextView tv_activity;
    private TextView tv_place;
    private TextView tv_time_start;
    private TextView tv_time_end;


    private Button bt_go;
    private Button bt_more_info;
    public int index = 0;

    public NumbersAdapterLocalList(int numberItems, ArrayList<String> timeStart, ArrayList<String> timeEnd,
                                   ArrayList<String> place, ArrayList<String> activity, ArrayList<String> description, ArrayList<String> date) {
        this.numberItems = numberItems;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.place = place;
        this.activity = activity;
        this.description = description;
        this.date = date;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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


        }
        void bind(final int listIndex) {
            Log.d("LOLOLOLOLOL", String.valueOf(timeStart));
            Log.d("LOLOLOLOLOL", String.valueOf(timeEnd));
            Log.d("LOLOLOLOLOL", String.valueOf(place));
            Log.d("LOLOLOLOLOL", String.valueOf(activity));
            bt_more_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentlvl1 = new Intent(v.getContext(), MainActivityLvl1.class);
                    intentlvl1.putExtra("date"        ,date       .get(listIndex ));
                    intentlvl1.putExtra("timeStart"   ,timeStart  .get(listIndex));
                    intentlvl1.putExtra("timeEnd"     ,timeEnd    .get(listIndex));
                    intentlvl1.putExtra("activity"    ,activity   .get(listIndex));
                    intentlvl1.putExtra("place"       ,place      .get(listIndex));
                    intentlvl1.putExtra("description" ,description.get(listIndex));
                    v.getContext().startActivity(intentlvl1);
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

