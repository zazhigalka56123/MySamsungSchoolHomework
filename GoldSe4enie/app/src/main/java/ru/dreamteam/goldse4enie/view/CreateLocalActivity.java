package ru.dreamteam.goldse4enie.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import ru.dreamteam.goldse4enie.DatePickerFragememt;
import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.TimePickerFragment;
import ru.dreamteam.goldse4enie.domain.ActivityLocal;

public class CreateLocalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private Button bt_time_start;
    private Button bt_time_end;
    private Button bt_date;
    private Button bt_set_name;
    private Button bt_set_place;
    private Button bt_add_tl;
    private Button bt_max_people;
    private Button bt_set_description;
    private Button bt_set_otryad;

    private TextView tv_error_tl;

    private Spinner spinner_napr;

    private String TimeNowMinute;
    private String TimeNowHoure;
    private String DateNowDay;
    private String DateNowMonth;
    private String vizov;


    private String timeStart = "";
    private String timeEnd = "";
    private String name = "";
    private String place = "";
    private String date = "";
    private String description = "";
    private String campType = "";
    private int campNumber = 0;
    private int maxPeople = 0;


    private FirebaseDatabase database;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_local);
        init();
        String[] a = CreateTLActivity.updateTime();
        TimeNowMinute = a[0];
        TimeNowHoure = a[1];
        DateNowDay = a[2];
        DateNowMonth = a[4];
        bt_date.setText(DateNowDay + "." + DateNowMonth);
        bt_time_start.setText(TimeNowHoure + ":" + TimeNowMinute);
        bt_time_end.setText(TimeNowHoure + ":" + TimeNowMinute);
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

        bt_add_tl = findViewById(R.id.bt_add_tl);
        bt_add_tl.setOnClickListener(this);

        spinner_napr = findViewById(R.id.spinner_napr);

        tv_error_tl = findViewById(R.id.tv_error_tl);

        ArrayAdapter<CharSequence> arrayAdapterOtr = ArrayAdapter.createFromResource(this,
                R.array.otr, android.R.layout.simple_list_item_1);
        arrayAdapterOtr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> arrayAdapterNapr = ArrayAdapter.createFromResource(this,
                R.array.napr, android.R.layout.simple_list_item_1);
        arrayAdapterOtr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_napr.setAdapter(arrayAdapterNapr);

        bt_max_people = findViewById(R.id.bt_max_people);
        bt_max_people.setOnClickListener(this);

        bt_set_description = findViewById(R.id.bt_set_description);
        bt_set_description.setOnClickListener(this);

        bt_set_otryad = findViewById(R.id.bt_set_otryad);
        bt_set_otryad.setOnClickListener(this);

        spinner_napr.setOnItemSelectedListener(this);

        database = FirebaseDatabase.getInstance();
        Ref = database.getReference("Time list");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        campType = (String) spinner_napr.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String[] a = CreateTLActivity.updateTime();
        TimeNowMinute = a[0];
        TimeNowHoure = a[1];
        DateNowDay = a[2];
        DateNowMonth = a[4];
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

            //Установка отряда
            case R.id.bt_set_otryad:
                AlertDialog.Builder builderOtr = new AlertDialog.Builder(CreateLocalActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewOtr = getLayoutInflater().inflate(R.layout.dialog_et, null);
                builderOtr.setView(viewOtr);
                builderOtr.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = viewOtr.findViewById(R.id.et_dialog);
                        bt_set_otryad.setText(editText.getText().toString());
                        campNumber = Integer.parseInt(editText.getText().toString());
                    }
                });
                builderOtr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogOtr = builderOtr.create();
                alertDialogOtr.show();
                break;


            //Установка имени
            case R.id.bt_set_name:

                AlertDialog.Builder builderName = new AlertDialog.Builder(CreateLocalActivity.this,
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
                AlertDialog.Builder builderPlace = new AlertDialog.Builder(CreateLocalActivity.this,
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
                AlertDialog.Builder builderDescription = new AlertDialog.Builder(CreateLocalActivity.this,
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
                AlertDialog.Builder builderMaxPeople = new AlertDialog.Builder(CreateLocalActivity.this,
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

            //Загрузка
            case R.id.bt_add_tl:
                ActivityLocal activity = new ActivityLocal(date, maxPeople, campNumber, campType,
                        timeStart, timeEnd, name, place, description);
                Ref.push().setValue(activity);
                Toast.makeText(CreateLocalActivity.this, "Добавлено!", Toast.LENGTH_SHORT).show();
                Intent intentLvl2 = new Intent(v.getContext(), MainActivityLvl2.class);
                v.getContext().startActivity(intentLvl2);

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
