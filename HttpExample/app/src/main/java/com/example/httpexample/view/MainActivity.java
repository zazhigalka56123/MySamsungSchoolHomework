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
import com.example.httpexample.interactor.user.UserTask;
import com.example.httpexample.repository.jsonplaceholder.JsonPlaceholderParser;

import org.json.JSONException;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    public User[] users;
    JsonPlaceholderParser parser = new JsonPlaceholderParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("USERS", "hello, i am from MainActivity");
        setContentView(R.layout.activity_main);

        new UserInteractor().getUsers();

//        if (users != null)
//             goRv(users);
//         Log.d("USERS1", users[0].name);
    }


}
