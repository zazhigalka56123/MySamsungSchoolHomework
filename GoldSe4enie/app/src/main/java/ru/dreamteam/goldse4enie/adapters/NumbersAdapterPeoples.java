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

public class NumbersAdapterPeoples extends RecyclerView.Adapter<NumbersAdapterPeoples.NumberViewHolder> {

    private int numberItems;
    private ArrayList<String> peoples;

    private TextView tv_number;
    private TextView tv_people;

    public NumbersAdapterPeoples( int numberItems, ArrayList<String> peoples) {
        this.numberItems = numberItems;
        this.peoples = peoples;
    }

    @Override
    public NumbersAdapterPeoples.NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_people;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        NumbersAdapterPeoples.NumberViewHolder viewHolder = new NumbersAdapterPeoples.NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersAdapterPeoples.NumberViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }
    class NumberViewHolder extends RecyclerView.ViewHolder {
        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_number = itemView.findViewById(R.id.tv_num);
            tv_people = itemView.findViewById(R.id.tv_people);
        }
        void bind(int listIndex) {
            tv_number.setText(listIndex + 1);
            tv_people.setText(peoples.get(listIndex));
        }
    }
}
