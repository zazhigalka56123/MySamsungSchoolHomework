package ru.dreamteam.goldse4enie;

import android.widget.EditText;

import java.util.HashMap;

public class GetLoginRequest {
    private EditText et_login;
    private EditText et_password;

    private String login;
    private String password;

    private int loginHash;
    private int passwordHash;

    private HashMap<Integer[], Integer[]> users;


    public GetLoginRequest(EditText login, EditText password) {
        this.et_login = login;
        this.et_password = password;
    }

    void setLoginAndPassword() {
        login = String.valueOf(et_login.getText());
        password = String.valueOf(et_password.getText());
    }

    void getUsersArray() {
        users = new HashMap<>();
        //сдесь будет считывание из файла
    }

    void goToHash() {
        loginHash = login.hashCode();
        passwordHash = login.hashCode();
    }

    boolean proove() {
        if ("a".equals("a")) {
            return true;
        }
        return false;
    }


    int chekLevel()
    {
        return 1;
    }

    String getAns() {
        if (proove() == true) {
            //int lvl = chekLevel();
            //String[] a = ["1", "2", "3","Name"]; // lvl, направлениеб отряд, имя
            // ret
        } else {
            ///return
        }
        return "жопа";
    }

}
