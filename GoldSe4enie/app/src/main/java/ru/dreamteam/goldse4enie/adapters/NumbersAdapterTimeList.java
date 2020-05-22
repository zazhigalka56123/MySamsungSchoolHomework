package ru.dreamteam.goldse4enie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.TimeList;


public class NumbersAdapterTimeList extends RecyclerView.Adapter<NumbersAdapterTimeList.NumberViewHolder> {

    private int numberItems;

    TimeList TL;

    private ArrayList<String> timeStart;
    private ArrayList<String> timeEnd;
    private ArrayList<String> place;
    private ArrayList<String> activity;

    private TextView tv_activity;
    private TextView tv_place;
    private TextView tv_time_start;
    private TextView tv_time_end;

    public NumbersAdapterTimeList( int numberItems, ArrayList<String> timeStart, ArrayList<String> timeEnd, ArrayList<String> place, ArrayList<String> activity) {
        this.numberItems = numberItems;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.place = place;
        this.activity = activity;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_time_list;

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
            tv_activity = itemView.findViewById(R.id.tv_activity);
            tv_place = itemView.findViewById(R.id.tv_place);
            tv_time_start = itemView.findViewById(R.id.tv_time_start);
            tv_time_end = itemView.findViewById(R.id.tv_time_end);
        }
        void bind(int listIndex) {
            tv_time_start.setText(timeStart.get(listIndex));
            tv_time_end.setText(timeEnd.get(listIndex));
            tv_place.setText(place.get(listIndex));
            tv_activity.setText(activity.get(listIndex));
        }
    }
}

