package ru.dreamteam.goldse4enie.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.dreamteam.goldse4enie.DatePickerFragememt;
import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.TimePickerFragment;

public class CreateTLActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener,AdapterView.OnItemSelectedListener {

    private Button bt_time_start;
    private Button bt_time_end;
    private Button bt_date;
    private Button bt_set_name;

    private Spinner spinner_otryad;
    private Spinner spinner_napr;

    private EditText et_dialog;
    public String vizov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_t_l);

        bt_time_start = findViewById(R.id.bt_time_start);
        bt_time_start.setOnClickListener(this);

        bt_time_end = findViewById(R.id.bt_time_end);
        bt_time_end.setOnClickListener(this);

        bt_date = findViewById(R.id.bt_date);
        bt_date.setOnClickListener(this);

        bt_set_name = findViewById(R.id.bt_set_name);
        bt_set_name.setOnClickListener(this);

        spinner_napr = findViewById(R.id.spinner_napr);
        spinner_otryad = findViewById(R.id.spinner_otryad);

        et_dialog = findViewById(R.id.et_dialog);

        Date currentDate = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);

        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);

        bt_date.setText(dateText);
        bt_time_start.setText(timeText);
        bt_time_end.setText(timeText);

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


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateTLActivity.this, R.style.Theme_AppCompat_Dialog_Alert);

                final View opa = getLayoutInflater().inflate(R.layout.dialog_et, null);

                builder.setView(opa);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = opa.findViewById(R.id.et_dialog);
                        bt_set_name.setText(editText.getText().toString());
                        Toast.makeText(getApplicationContext(),editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String min;
        String hour;

        if(minute == 0)
            min = "00";
        else if (minute < 10)
            min = "0"+minute;
        else
            min = String.valueOf(minute);

        if(hourOfDay == 0)
            hour = "00";
        else
            hour = String.valueOf(hourOfDay);
        Log.d("timeLol", String.valueOf(view));
        if (vizov == "1")
            bt_time_start.setText(hour+":"+min);
        else if (vizov == "2")
            bt_time_end.setText(hour+":"+min);
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

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
