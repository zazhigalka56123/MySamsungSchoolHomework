package com.example.httpexample.interactor.user;

import android.os.AsyncTask;
import android.util.Log;

import com.example.httpexample.domain.user.User;
import com.example.httpexample.repository.jsonplaceholder.JsonPlaceholderParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UserTask extends AsyncTask<User[], User[], User[]> {
    JsonPlaceholderParser parser;
    UserResponse userResponse;

    public UserTask(JsonPlaceholderParser parser, UserResponse userResponse) {
        this.parser = parser;
        this.userResponse = userResponse;
    }

    @Override
    protected void onPostExecute(User[] user) {
        super.onPostExecute(user);
        userResponse.reponse(user);
    }

    @Override
    protected User[] doInBackground(User[]... users) {
        try {
            Log.d("USERS", "Пацаны ща парсить начну");
            User[] user = parser.getUser();
            return user;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e("USERS", "Ошибка парсинга " + e);
        }
        return null;
    }
}
