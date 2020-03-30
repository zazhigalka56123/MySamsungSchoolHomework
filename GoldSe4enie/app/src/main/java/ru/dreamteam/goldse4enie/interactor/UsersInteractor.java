package ru.dreamteam.goldse4enie.interactor;

import android.content.Context;
import java.util.ArrayList;
import ru.dreamteam.goldse4enie.domain.User;
import ru.dreamteam.goldse4enie.repository.DatabaseRepository;

public class UsersInteractor {
    DatabaseRepository repository;

    public UsersInteractor(Context context) {
        repository = new DatabaseRepository(context);
        update_bd();
    }

    public boolean insertUser(int name, int password, int lvl) {
        return repository.insertUser(name, password, lvl);
    }

    public boolean update_bd(){
        insertUser("Иван Пасечник".hashCode(),"12314".hashCode(), 1);
        return insertUser("Дима Зайцев".hashCode(),"12314".hashCode(), 1);
    }

    public User getUser(int name, int password) {
        return repository.getUser(name, password);
    }

    public ArrayList<User> getUsers(int limit) {
        return repository.getUsers(limit);
    }
}
