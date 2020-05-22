package ru.dreamteam.goldse4enie.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Ref;
import java.util.Calendar;

import ru.dreamteam.goldse4enie.DatePickerFragememt;
import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.TimePickerFragment;
import ru.dreamteam.goldse4enie.domain.ActivityGlobal;

public class CreateGlobalActivity extends AppCompatActivity implements View.OnClickListener,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    private Button bt_time_start;
    private Button bt_time_end;
    private Button bt_date;
    private Button bt_set_name;
    private Button bt_set_place;
    private Button bt_add_tl;
    private Button bt_max_people;
    private Button bt_main_people_local;
    private Button bt_set_description;

    private TextView tv_error_tl;
    private String vizov;


    private String timeStart = "";
    private String timeEnd = "";
    private String name = "";
    private String place = "";
    private String date = "";
    private String description = "";
    private String mainPeople = "";
    private int maxPeople = 0;


    private FirebaseDatabase database;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_global);
        init();
    }

    public void init() {
        bt_time_start = findViewById(R.id.bt_time_start);
        bt_time_start.setOnClickListener(this);

        bt_time_end = findViewById(R.id.bt_time_end);
        bt_time_end.setOnClickListener(this);

        bt_date = findViewById(R.id.bt_date);
        bt_date.setOnClickListener(this);

        bt_set_name = findViewById(R.id.bt_set_name);
        bt_set_name.setOnClickListener(this);

        bt_set_place = findViewById(R.id.bt_set_place);
        bt_set_place.setOnClickListener(this);

        bt_max_people = findViewById(R.id.bt_max_people);
        bt_max_people.setOnClickListener(this);

        bt_main_people_local = findViewById(R.id.bt_main_people_local);
        bt_main_people_local.setOnClickListener(this);

        bt_set_description = findViewById(R.id.bt_set_description);
        bt_set_description.setOnClickListener(this);

        bt_add_tl = findViewById(R.id.bt_add_item_tl);
        bt_add_tl.setOnClickListener(this);

        tv_error_tl = findViewById(R.id.tv_error_tl);

        database = FirebaseDatabase.getInstance();
        Ref = database.getReference("GA Time List");
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            //Установка начального вермени
            case R.id.bt_time_start:
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                vizov = "1";
                break;

            //Установка конечного вермени
            case R.id.bt_time_end:
                DialogFragment timePicker2 = new TimePickerFragment();
                timePicker2.show(getSupportFragmentManager(), "time picker2");
                vizov = "2";
                break;

            //Установка даты
            case R.id.bt_date:
                DialogFragment datePicker = new DatePickerFragememt();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;

            //Установка имени
            case R.id.bt_set_name:

                AlertDialog.Builder builderName = new AlertDialog.Builder(CreateGlobalActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewName = getLayoutInflater().inflate(R.layout.dialog_et, null);
                builderName.setView(viewName);
                builderName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = viewName.findViewById(R.id.et_dialog);
                        bt_set_name.setText(editText.getText().toString());
                        name = editText.getText().toString();
                    }
                });
                builderName.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogName = builderName.create();
                alertDialogName.show();
                break;

            //Установка места
            case R.id.bt_set_place:
                AlertDialog.Builder builderPlace = new AlertDialog.Builder(CreateGlobalActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewPlace = getLayoutInflater().inflate(R.layout.dialog_et, null);
                builderPlace.setView(viewPlace);
                builderPlace.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = viewPlace.findViewById(R.id.et_dialog);
                        bt_set_place.setText(editText.getText().toString());
                        place = editText.getText().toString();

                    }
                });
                builderPlace.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogPlace = builderPlace.create();
                alertDialogPlace.show();
                break;

            //Установка описания
            case R.id.bt_set_description:
                AlertDialog.Builder builderDescription = new AlertDialog.Builder(CreateGlobalActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewDescription = getLayoutInflater().inflate(R.layout.dialog_et, null);
                builderDescription.setView(viewDescription);
                builderDescription.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = viewDescription.findViewById(R.id.et_dialog);
                        bt_set_description.setText(editText.getText().toString());
                        description = editText.getText().toString();

                    }
                });
                builderDescription.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogDescription = builderDescription.create();
                alertDialogDescription.show();
                break;

            //Установка максимального числа участников
            case R.id.bt_max_people:
                AlertDialog.Builder builderMaxPeople = new AlertDialog.Builder(CreateGlobalActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewMaxPeople = getLayoutInflater().inflate(R.layout.dialog_et, null);
                builderMaxPeople.setView(viewMaxPeople);
                builderMaxPeople.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = viewMaxPeople.findViewById(R.id.et_dialog);
                        bt_max_people.setText(editText.getText().toString());
                        maxPeople = Integer.parseInt(editText.getText().toString());

                    }
                });
                builderMaxPeople.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogMaxPeople = builderMaxPeople.create();
                alertDialogMaxPeople.show();
                break;

            //Установка организатора
            case R.id.bt_main_people_local:
                AlertDialog.Builder builderMainPeople = new AlertDialog.Builder(CreateGlobalActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewMainPeople = getLayoutInflater().inflate(R.layout.dialog_et, null);
                builderMainPeople.setView(viewMainPeople);
                builderMainPeople.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = viewMainPeople.findViewById(R.id.et_dialog);
                        bt_main_people_local.setText(editText.getText().toString());
                        mainPeople = editText.getText().toString();

                    }
                });
                builderMainPeople.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogMainPeople = builderMainPeople.create();
                alertDialogMainPeople.show();
                break;

            //Загрузка
            case R.id.bt_add_item_tl:
                ActivityGlobal activity = new ActivityGlobal( maxPeople, mainPeople, timeStart, timeEnd, name, place, description);

                Ref.child(date.substring(0,2)).setValue(activity);
                Toast.makeText(CreateGlobalActivity.this, "Добавлено!", Toast.LENGTH_SHORT).show();
                Intent intentlvl2 = new Intent(v.getContext(), MainActivityLvl2.class);
                v.getContext().startActivity(intentlvl2);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String min;
        String hour;

        if (minute == 0)
            min = "00";
        else if (minute < 10)
            min = "0" + minute;
        else
            min = String.valueOf(minute);

        if (hourOfDay == 0)
            hour = "00";
        else
            hour = String.valueOf(hourOfDay);
        if (vizov == "1") {
            bt_time_start.setText(hour + ":" + min);
            timeStart = hour + ":" + min;
        } else if (vizov == "2") {
            bt_time_end.setText(hour + ":" + min);
            timeEnd = hour + ":" + min;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String m, d;
        month += 1;
        if (month < 10)
            m = "0" + month;
        else
            m = String.valueOf(month);

        if (dayOfMonth < 10)
            d = "0" + dayOfMonth;
        else
            d = String.valueOf(dayOfMonth);
        bt_date.setText(d + "." + m);
        date = (d + "." + m);
    }
}
