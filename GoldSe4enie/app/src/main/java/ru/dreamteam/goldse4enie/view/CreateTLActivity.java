package ru.dreamteam.goldse4enie.view;

import android.annotation.SuppressLint;
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
import ru.dreamteam.goldse4enie.domain.TimeListItem;

public class CreateTLActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private Button bt_time_start;
    private Button bt_time_end;
    private Button bt_date;
    private Button bt_set_name;
    private Button bt_set_place;
    private Button bt_add_tl;
    private Button bt_add_item_tl;
    private Button bt_preview_tl;
    private Button bt_set_otryad;

    private Spinner spinner_napr;

    private TextView tv_error_tl;

    private ArrayList<TimeListItem> timeListItemArrayList = new ArrayList<TimeListItem>();
    private TimeListItem timeListItem;
    private String vizov;

    private String name;
    private String place;
    private String timeStart;
    private String timeEnd;
    private String date;
    private String campType;
    private int campNumber;
    private FirebaseDatabase database;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_t_l);

        init();    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {

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

            //Установка отряда
            case R.id.bt_set_otryad:
                AlertDialog.Builder builderOtr = new AlertDialog.Builder(CreateTLActivity.this,
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

            case R.id.bt_set_name:
                AlertDialog.Builder builderName = new AlertDialog.Builder(CreateTLActivity.this,
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

            case R.id.bt_set_place:
                AlertDialog.Builder builderPlace = new AlertDialog.Builder(CreateTLActivity.this,
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

            case R.id.bt_add_item_tl:
                timeListItem = new TimeListItem(date, campNumber, campType, timeStart, timeEnd, name, place);
                timeListItemArrayList.add(timeListItem);
                Toast.makeText(CreateTLActivity.this, "Добавлено!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_preview_tl:
                TimeList timeListPreview = new TimeList(timeListItemArrayList.size(), timeListItem.date,
                        timeListItem.campNumber, timeListItem.campType, timeListItemArrayList);
                Intent intentPreview = new Intent(v.getContext(), PreviewActivityTL.class);
                intentPreview.putExtra("item",         timeListPreview.item);
                intentPreview.putExtra("date",         timeListPreview.date);
                intentPreview.putExtra("campNumber",   timeListPreview.campNumber);
                intentPreview.putExtra("campType",     timeListPreview.campType);
//                intentPreview.putParcelableArrayListExtra("timeListArray", (ArrayList<? extends Parcelable>) timeListPreview.timeListArray);
                v.getContext().startActivity(intentPreview);


            case R.id.bt_add_tl:
                TimeListItem timeListItemLast = new TimeListItem(date, campNumber, campType, timeStart, timeEnd, name, place);
                TimeList timeList = new TimeList(timeListItemArrayList.size(), timeListItemLast.date,
                        timeListItemLast.campNumber, timeListItemLast.campType, timeListItemArrayList);
                Ref
                        .child("Time List")
                        .child(campType)
                        .child("" + campNumber)
                        .child(date.substring(3,5))
                        .child(date.substring(0,2))
                        .setValue(timeList);
                Toast.makeText(CreateTLActivity.this, "Добавлено!", Toast.LENGTH_SHORT).show();

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

        tv_error_tl = findViewById(R.id.tv_error_tl);
        ArrayAdapter<CharSequence> arrayAdapterNapr = ArrayAdapter.createFromResource(this,
                R.array.napr, android.R.layout.simple_list_item_1);

        spinner_napr.setAdapter(arrayAdapterNapr);

        spinner_napr.setOnItemSelectedListener(this);

        database = FirebaseDatabase.getInstance();
        Ref = database.getReference();

        bt_preview_tl = findViewById(R.id.bt_preview_tl);
        bt_preview_tl.setOnClickListener(this);

        bt_set_otryad = findViewById(R.id.bt_set_otryad);
        bt_set_otryad.setOnClickListener(this);

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
        campType = (spinner_napr.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
