package ru.dreamteam.goldse4enie.repository;

public class SQLScripts {
    public static String initDbScript() {
        return "create table user(" +
                "id integer primary key autoincrement," +
                "name integer not null," +
                "password integer not null," +
                "lvl integer not null" +
                ");";
    }

    public static String insertUserScript(int name, int password,int lvl) {
        String _name = "\"" + name + "\"";
        String _password = "\"" + password + "\"";
        String _lvl = "\"" + lvl + "\"";

        return "insert into user" +
                "(name, password, lvl)" +
                "values" +
                "(" + _name + "," + _password + "," + _lvl +
                ");";
    }

    public static String getUserScript(int name, int password) {
        String _name = "\"" + name + "\"";
        String _password = "\"" + password + "\"";

        return "select * from user" +
                " where name = " + _name +
                " and password = " + _password +
                ";";
    }

    public static String getAllUsersScript() {
        return "select * from user;";
    }

    public static String getAllUsersScript(int limit) {
        return "select * from user" +
                " limit " + limit +
                ";";
    }

}