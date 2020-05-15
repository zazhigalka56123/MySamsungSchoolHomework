package ru.dreamteam.goldse4enie.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.dreamteam.goldse4enie.DatePickerFragememt;
import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.TimePickerFragment;
import ru.dreamteam.goldse4enie.domain.TimeList;

public class CreateTLActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private Button bt_time_start;
    private Button bt_time_end;
    private Button bt_date;
    private Button bt_set_name;
    private Button bt_set_place;
    private Button bt_add_tl;
    private Button bt_add_item_tl;
    private Button bt_preview_tl;

    private Spinner spinner_otryad;
    private Spinner spinner_napr;

    private TextView tv_error_tl;

    private String TimeNowMinute;
    private String TimeNowHoure;
    private String DateNowDay;
    private String DateNowMonth;

    private String vizov;

    private ArrayList<String> TimeStart = new ArrayList<>();
    private ArrayList<String> TimeEnd   = new ArrayList<>();
    private ArrayList<String> name      = new ArrayList<>();
    private ArrayList<String> place     = new ArrayList<>();
    private String TimeStartStr         = "";
    private String TimeEndStr           = "";
    private String nameStr              = "";
    private String placeStr             = "";
    private String Date                 = "";
    private String DateOld              = "";
    private String CampType             = "Наука";
    private String CampTypeOld          = "";

    private int CampNumber    = 1;
    private int CampNumberOld = 0;

    private int items = 0;

    private FirebaseDatabase database;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_t_l);

        init();
        updateTime();
        // ссылка на realtime database https://console.firebase.google.com/project/gold-se4enie-lvl2-test/database/gold-se4enie-lvl2-test/data
        bt_date.setText(DateNowDay + "." + DateNowMonth);
        bt_time_start.setText(TimeNowHoure + ":" + TimeNowMinute);
        bt_time_end.setText(TimeNowHoure + ":" + TimeNowMinute);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        String[] a = updateTime();
        TimeNowMinute = a[0];
        TimeNowHoure  = a[1];
        DateNowDay    = a[2];
        DateNowMonth  = a[4];

        switch (v.getId()) {

            case R.id.bt_time_start:
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                vizov = "1";
                break;

            case R.id.bt_time_end:
                DialogFragment timePicker2 = new TimePickerFragment();
                timePicker2.show(getSupportFragmentManager(), "time picker2");
                vizov = "2";
                break;

            case R.id.bt_date:
                DialogFragment datePicker = new DatePickerFragememt();
                datePicker.show(getSupportFragmentManager(), "date picker");
                break;

            case R.id.bt_set_name:
                AlertDialog.Builder builderName = new AlertDialog.Builder(CreateTLActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewName = getLayoutInflater().inflate(R.layout.dialog_et, null);
                builderName.setView(viewName);
                builderName.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = viewName.findViewById(R.id.et_dialog);
                        bt_set_name.setText(editText.getText().toString());
                        nameStr = editText.getText().toString();
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

            case R.id.bt_set_place:
                AlertDialog.Builder builderPlace = new AlertDialog.Builder(CreateTLActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewPlace = getLayoutInflater().inflate(R.layout.dialog_et, null);
                builderPlace.setView(viewPlace);
                builderPlace.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = viewPlace.findViewById(R.id.et_dialog);
                        bt_set_place.setText(editText.getText().toString());
                        placeStr = editText.getText().toString();

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

            case R.id.bt_add_item_tl:

                if (TimeEndStr.equals("") || TimeStartStr.equals("") || Date.equals("")) {

                    tv_error_tl.setText("Заполните все поля!");
                    tv_error_tl.setTextColor(R.color.red);

                } else if (Date.equals(DateNowDay + "." + DateNowMonth) || Date.equals("")) {

                    tv_error_tl.setText("Невозможно редактировать расписание на сегодня!");
                    tv_error_tl.setTextColor(R.color.red);

                } else {

                    TimeStart.add(TimeStartStr);
                    TimeEnd.add(TimeEndStr);
                    name.add(nameStr);
                    place.add(placeStr);

                    DateOld = Date;
                    CampTypeOld = CampType;
                    CampNumberOld = CampNumber;

                    TimeStartStr = "";
                    TimeEndStr = "";
                    nameStr = "";
                    placeStr = "";

                    items += 1;

                    Toast.makeText(CreateTLActivity.this, "Записано!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_preview_tl:

                TimeList timeListPreview = new TimeList(Date, CampNumber, CampType, TimeStart, TimeEnd, name, place);
                Intent intentPreview = new Intent(v.getContext(), PreviewActivityTL.class);
                intentPreview.putExtra("tl", (Serializable) timeListPreview);
                v.getContext().startActivity(intentPreview);

            case R.id.bt_add_tl:

                Toast.makeText(CreateTLActivity.this, "Добавлено!", Toast.LENGTH_SHORT).show();
                TimeList timeList = new TimeList(Date, CampNumber, CampType, TimeStart, TimeEnd, name, place);
                Ref.push().setValue(timeList);
                Log.d("tl", String.valueOf(timeList.date));
                Log.d("tl", String.valueOf(timeList.campNumber));
                Log.d("tl", String.valueOf(timeList.campType));
                Log.d("tl", String.valueOf(timeList.timeStart));
                Log.d("tl", String.valueOf(timeList.timeEnd));
                Log.d("tl", String.valueOf(timeList.name));
                Log.d("tl", String.valueOf(timeList.place));

                Intent intentLvl2 = new Intent(v.getContext(), MainActivityLvl2.class);
                v.getContext().startActivity(intentLvl2);
                break;
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
            TimeStartStr = hour + ":" + min;
        } else if (vizov == "2") {
            bt_time_end.setText(hour + ":" + min);
            TimeEndStr = hour + ":" + min;
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
        Date = (d + "." + m);
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

        bt_add_item_tl = findViewById(R.id.bt_add_item_tl);
        bt_add_item_tl.setOnClickListener(this);

        spinner_napr = findViewById(R.id.spinner_napr);
        spinner_otryad = findViewById(R.id.spinner_otryad);

        tv_error_tl = findViewById(R.id.tv_error_tl);

        ArrayAdapter<CharSequence> arrayAdapterOtr = ArrayAdapter.createFromResource(this,
                R.array.otr, android.R.layout.simple_list_item_1);
        arrayAdapterOtr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> arrayAdapterNapr = ArrayAdapter.createFromResource(this,
                R.array.napr, android.R.layout.simple_list_item_1);
        arrayAdapterOtr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_otryad.setAdapter(arrayAdapterOtr);
        spinner_napr.setAdapter(arrayAdapterNapr);

        spinner_otryad.setOnItemSelectedListener(this);
        spinner_napr.setOnItemSelectedListener(this);

        database = FirebaseDatabase.getInstance();
        Ref = database.getReference("Time list");

        bt_preview_tl.findViewById(R.id.bt_preview_tl);
        bt_preview_tl.setOnClickListener(this);
    }

    public static String[] updateTime() {
        Date currentDate = new Date();
        String [] a = new String[4];
        DateFormat timeFormatMinute = new SimpleDateFormat("mm", Locale.getDefault());
        a[0] = timeFormatMinute.format(currentDate);

        DateFormat timeFormatHoure = new SimpleDateFormat("HH", Locale.getDefault());
        a[1] = timeFormatHoure.format(currentDate);

        DateFormat dateFormatDay = new SimpleDateFormat("dd", Locale.getDefault());
        a[2] = dateFormatDay.format(currentDate);

        DateFormat dateFormatMonth = new SimpleDateFormat("MM", Locale.getDefault());
        a[3] = dateFormatMonth.format(currentDate);

        return a;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CampType = (spinner_napr.getSelectedItem().toString());
//        CampNumber = (int) spinner_napr.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
