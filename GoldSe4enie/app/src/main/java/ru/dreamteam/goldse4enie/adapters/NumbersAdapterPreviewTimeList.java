package ru.dreamteam.goldse4enie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.dreamteam.goldse4enie.R;

public class NumbersAdapterPreviewTimeList extends RecyclerView.Adapter<NumbersAdapterPreviewTimeList.NumberViewHolder> {
    private int numberItems;

    private ArrayList<String> timeStart;
    private ArrayList<String> timeEnd;
    private ArrayList<String> place;
    private ArrayList<String> activity;

    private TextView tv_activity;
    private TextView tv_place;
    private TextView tv_time_start;
    private TextView tv_time_end;

    private Button bt_delete;
    private Button bt_change;

    public NumbersAdapterPreviewTimeList(int numberOfItem, ArrayList<String> timeStartArr, ArrayList<String> timeEndArr,
                                  ArrayList<String> placeArr, ArrayList<String> activityArr ) {
        numberItems = numberOfItem;
        timeStart = timeStartArr;
        timeEnd = timeEndArr;
        place = placeArr;
        activity = activityArr;
    }

    @Override
    public NumbersAdapterPreviewTimeList.NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_preview_t_l;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        NumbersAdapterPreviewTimeList.NumberViewHolder viewHolder = new NumbersAdapterPreviewTimeList.NumberViewHolder(view);
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
    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_activity = itemView.findViewById(R.id.tv_activity);
            tv_place = itemView.findViewById(R.id.tv_place);
            tv_time_start = itemView.findViewById(R.id.tv_time_start);
            tv_time_end = itemView.findViewById(R.id.tv_time_end);
            bt_delete = itemView.findViewById(R.id.bt_delete);
            bt_change = itemView.findViewById(R.id.bt_change);
            bt_delete.setOnClickListener(this);
            bt_change.setOnClickListener(this);
        }
        void bind(int listIndex) {
            tv_time_start.setText(timeStart.get(listIndex));
            tv_time_end.setText(timeEnd.get(listIndex));
            tv_place.setText(place.get(listIndex));
            tv_activity.setText(activity.get(listIndex));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_delete:
//                    timeLi
            }
        }
    }
}