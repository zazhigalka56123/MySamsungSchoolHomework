package ru.dreamteam.goldse4enie.domain;

/**
 * Модель данных пользователя
 */
public class User {
    public final int id;
    public final int name;
    public final int password;
    public final int lvl;
    public final String age = "";
    public final int campNumber = 0;
    public final String campType = "";

    public User(int id, int name, int password,int lvl) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.lvl = lvl;
    }
}
