package ru.dreamteam.goldse4enie.domain;

import java.io.Serializable;

public class User implements Serializable {
    public  int id;
    public  String name;
    public  String password;
    public  int lvl;
    public  int age;
    public  int campNumber;
    public  String campType  ;

    public User(int id, String name, String password,int lvl, int age,int campNumber, String campType) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.lvl = lvl;
        this.age = age;
        this.campNumber = campNumber;
        this.campType = campType;
    }
    public User(){}
}
