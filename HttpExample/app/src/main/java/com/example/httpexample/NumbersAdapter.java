package com.example.httpexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.httpexample.domain.user.User;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder> {

    private int numberItems;
    private User[] arr;

    public NumbersAdapter(int numberOfItem, User[] users) {
        numberItems = numberOfItem;
        arr = users;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item;

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
        TextView address;
        TextView name;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            address =  itemView.findViewById(R.id.address);
            name =  itemView.findViewById(R.id.name);
        }

        void bind(int listIndex) {
            name.setText(arr[listIndex].name);
            address.setText((CharSequence) arr[listIndex].address);
        }
    }
}

