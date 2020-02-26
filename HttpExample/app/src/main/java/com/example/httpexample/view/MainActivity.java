package com.example.httpexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.httpexample.NumbersAdapter;
import com.example.httpexample.R;
import com.example.httpexample.domain.user.User;
import com.example.httpexample.interactor.user.UserInteractor;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("errorr", "hello, i am from MainActivity");
        setContentView(R.layout.activity_main);

        new UserInteractor().getUsers();
    }

    public void goRv(User[] array)
    {
        for (int i = 0; i < 5; i++) {
            Log.e("User" + i, array[i].name);
        }
        RecyclerView numbersList = findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);

        NumbersAdapter numbersAdapter = new NumbersAdapter(5, array);
        numbersList.setAdapter(numbersAdapter);
    }

}
