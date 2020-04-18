package ru.dreamteam.goldse4enie.getters;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import ru.dreamteam.goldse4enie.domain.User;
import ru.dreamteam.goldse4enie.interactor.UsersInteractor;

public class GetLoginRequest {
    public UsersInteractor usersInteractor ;
    private String login;
    private String password;

    private Context context;

    private int loginHash;
    private int passwordHash;

    private HashMap<Integer[], Integer[]> users;

    public GetLoginRequest(String login, String password, Context context) {
        this.login = login;
        this.password = password;
        this.context = context;
        usersInteractor = new UsersInteractor(context);
    }

    public String GetLogin(){
        return login;
    }

    public int getRequest(){
        User user = usersInteractor.getUser(login.hashCode(), password.hashCode());

        if (user != null) {
            Log.d("User", String.valueOf(user));
            return user.lvl;
        } else {
            return 0;
        }
    }

}
