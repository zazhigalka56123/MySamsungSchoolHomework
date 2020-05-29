package ru.dreamteam.goldse4enie.domain;

import java.io.Serializable;

public class User implements Serializable {
    public  int login;
    public  String name;
    public  int password;
    public  int lvl;
    public  int age;
    public  int campNumber;
    public  String campType;

    public User(int login, String name, int password,int lvl, int age,int campNumber, String campType) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.lvl = lvl;
        this.age = age;
        this.campNumber = campNumber;
        this.campType = campType;
    }
    public User(){}
}
