package ru.dreamteam.goldse4enie.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import ru.dreamteam.goldse4enie.domain.User;

public class DatabaseRepository {
    private final String TAG = "DatabaseRepository";
    DatabaseHelper databaseHelper;

    public DatabaseRepository(Context context) {
        initDb(context);
    }

    private void initDb(Context context) {
        databaseHelper = new DatabaseHelper(context, "UserDb", null, 1);
    }

    public boolean insertUser(int name, int password, int lvl) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if (getUser(name, password) != null) {
            return false;
        } else {
            db.execSQL(SQLScripts.insertUserScript(name, password, lvl));
            return true;
        }
    }

    public User getUser(int name, int password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.getUserScript(name, password), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");
            int lvlColIndex = userCursor.getColumnIndex("lvl");

            do {
                // получаем значения по номерам столбцов

                return null;
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
        } else {
            userCursor.close();
            return null;
        }
    }

    public ArrayList<User> getUsers(int limit) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor userCursor = db.rawQuery(SQLScripts.getAllUsersScript(limit), null);
        // Cтавим позицию курсора на первую строку выборки
        // Eсли в выборке нет строк, вернется false
        if (userCursor.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColIndex = userCursor.getColumnIndex("id");
            int nameColIndex = userCursor.getColumnIndex("name");
            int passwordColIndex = userCursor.getColumnIndex("password");
            int lvlColIndex = userCursor.getColumnIndex("lvl");

            ArrayList<User> userList = new ArrayList();
            do {
                // получаем значения по номерам столбцов

                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (userCursor.moveToNext());
            userCursor.close();
            return userList;
        } else {
            userCursor.close();
            return null;
        }
    }
}
