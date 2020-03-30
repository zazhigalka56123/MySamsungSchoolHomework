package ru.dreamteam.goldse4enie.domain;

/**
 * Модель данных пользователя
 */
public class User {
    private final int id;
    private final int name;
    private final int password;
    private final int lvl;
    private final String age = "";
    private final int campNumber = 0;
    private final String campType = "";

    private User(int id, int name, int password,int lvl) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.lvl = lvl;
    }
}
