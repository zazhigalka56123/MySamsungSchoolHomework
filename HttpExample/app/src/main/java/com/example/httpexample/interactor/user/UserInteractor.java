package com.example.httpexample.interactor.user;

import android.os.Build;
import android.util.Log;

import com.example.httpexample.domain.user.User;
import com.example.httpexample.repository.jsonplaceholder.JsonPlaceholderParser;
import com.example.httpexample.view.MainActivity;


public class UserInteractor implements UserResponse {

    JsonPlaceholderParser parser = new JsonPlaceholderParser();

    public void getUsers() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            Log.d("USERS", "уже уже");
            new UserTask(parser, this).execute();
        }
    }

    @Override
    public void reponse(User[] array) {
        MainActivity mainActivity = new MainActivity();
        Log.d("USERS", "Не ну нифига");
        Log.d("USERS", array[0].name);
        mainActivity.goRv(array);
    }
}

interface UserResponse {
    void reponse(User[] array);

}
