package ru.dreamteam.goldse4enie.adapters;

import android.annotation.SuppressLint;
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
import ru.dreamteam.goldse4enie.domain.TimeList;
import ru.dreamteam.goldse4enie.view.ChangeActivity;
import ru.dreamteam.goldse4enie.view.PreviewActivityTL;

public class NumbersAdapterPreviewTimeList extends RecyclerView.Adapter<NumbersAdapterPreviewTimeList.NumberViewHolder> {
    private int numberItems;

    public ArrayList<String> timeStart;
    public ArrayList<String> timeEnd;
    public ArrayList<String> place;
    public ArrayList<String> activity;
    private TimeList currentTimeList;

    private TextView tv_activity;
    private TextView tv_place;
    private TextView tv_time_start;
    private TextView tv_time_end;

    private Button bt_delete;
    private Button bt_change;

    public NumbersAdapterPreviewTimeList(int numberOfItem, ArrayList<String> timeStartArr, ArrayList<String> timeEndArr,
                                         ArrayList<String> placeArr, ArrayList<String> activityArr,TimeList currentTimeList ) {

        numberItems = numberOfItem;
        timeStart = timeStartArr;
        timeEnd = timeEndArr;
        place = placeArr;
        activity = activityArr;
        this.currentTimeList = currentTimeList;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_time_list;

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
        return activity.size();
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
            int index = listIndex;
            tv_time_start.setText(timeStart.get(listIndex));
            tv_time_end.setText(timeEnd.get(listIndex));
            tv_place.setText(place.get(listIndex));
            tv_activity.setText(activity.get(listIndex));
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_delete:
                    int position = getAdapterPosition();
                    timeStart .remove(position);
                    timeEnd   .remove(position);
                    place     .remove(position);
                    activity  .remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, activity.size());
                    break;
                case R.id.bt_change:
                    Intent intentPreview = new Intent(v.getContext(), ChangeActivity.class);
                    intentPreview.putExtra("index",getAdapterPosition() );
                    intentPreview.putExtra("timeStart",timeStart );
                    intentPreview.putExtra("timeEnd",timeEnd );
                    intentPreview.putExtra("place",place );
                    intentPreview.putExtra("activity",activity );
                    intentPreview.putExtra("item",currentTimeList.item );
                    intentPreview.putExtra("date",currentTimeList.date );
                    intentPreview.putExtra("campNumber",currentTimeList.campNumber );
                    intentPreview.putExtra("campType",currentTimeList.campType );
                    intentPreview.putExtra("List", currentTimeList.timeListArray );
                    v.getContext().startActivity(intentPreview);
                    break;
            }
        }

    }
}