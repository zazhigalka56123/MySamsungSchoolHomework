package ru.dreamteam.goldse4enie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.dreamteam.goldse4enie.R;

public class NumbersAdapterLocalList extends RecyclerView.Adapter<NumbersAdapterLocalList.NumberViewHolder> {

    private int numberItems;

    private String[] time;
    private String[] place;
    private String[] activity;

    private TextView tv_activity;
    private TextView tv_place;
    private TextView tv_time_start;
    private TextView tv_time_end;

    public NumbersAdapterLocalList(int numberOfItem, String[] timeArr, String[] placeArr,
                                  String[] activityArr ) {
        numberItems = numberOfItem;
        time = timeArr;
        place = placeArr;
        activity = activityArr;
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
            tv_time_start.setText(time[listIndex].split("-")[0]);
            tv_time_start.setText(time[listIndex].split("-")[1]);
            tv_place.setText(place[listIndex]);
            tv_activity.setText(activity[listIndex]);
        }
    }
}

