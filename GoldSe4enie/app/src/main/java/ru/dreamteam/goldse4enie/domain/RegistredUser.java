package ru.dreamteam.goldse4enie.domain;

public class RegistredUser {
    public User user;
    public String wifiMac;
    public RegistredUser(User user, String wifiMac){
        this.user = user;
        this.wifiMac = wifiMac;
    }
    RegistredUser(){}
}
