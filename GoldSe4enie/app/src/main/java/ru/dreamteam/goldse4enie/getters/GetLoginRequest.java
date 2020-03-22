package ru.dreamteam.goldse4enie.getters;

import android.widget.EditText;

import java.util.HashMap;

public class GetLoginRequest {

    private String login;
    private String password;

    private int loginHash;
    private int passwordHash;

    private HashMap<Integer[], Integer[]> users;


    public GetLoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    int getRequest(){
        return 1;
    }

}
