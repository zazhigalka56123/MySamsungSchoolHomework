package ru.dreamteam.goldse4enie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.User;

public class MainActivityLvl3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Button bt_name;
    private Button bt_campNumber;
    private Button bt_age;
    private Button bt_login;
    private Button bt_password;
    private Button bt_password_confirm;
    private Button bt_lvl;
    private Button bt_add_user;

    private ImageButton bt_home;
    private ImageButton bt_settings;

    private Spinner spinner_campType;

    private TextView tv_error;
    private TextView tv_appname;

    public  int login;
    public  String name;
    public  int password;
    public  int passwordConfirm;
    public  int lvl;
    public  int age;
    public  int campNumber;
    public  String campType;

    private Bundle arguments;
    private User currentUser;


    private FirebaseDatabase database;
    private DatabaseReference Ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lvl3);
        init();
    }

    public void init(){
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.napr, android.R.layout.simple_list_item_1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bt_name             = findViewById(R.id.bt_name);
        bt_campNumber       = findViewById(R.id.bt_campNumber);
        bt_age              = findViewById(R.id.bt_age);
        bt_login            = findViewById(R.id.bt_login);
        bt_password         = findViewById(R.id.bt_password);
        bt_password_confirm = findViewById(R.id.bt_password_confirm);
        bt_lvl              = findViewById(R.id.bt_lvl);
        bt_add_user         = findViewById(R.id.bt_add_user);
        bt_home             = findViewById(R.id.bt_home);
        bt_settings         = findViewById(R.id.bt_settings);
        spinner_campType    = findViewById(R.id.spinner_campType);
        tv_error            = findViewById(R.id.tv_error);
        tv_appname          = findViewById(R.id.tv_appname);

        bt_name            .setOnClickListener(this);
        bt_campNumber      .setOnClickListener(this);
        bt_age             .setOnClickListener(this);
        bt_login           .setOnClickListener(this);
        bt_password        .setOnClickListener(this);
        bt_password_confirm.setOnClickListener(this);
        bt_lvl             .setOnClickListener(this);
        bt_add_user        .setOnClickListener(this);
        bt_home            .setOnClickListener(this);
        bt_settings        .setOnClickListener(this);
        spinner_campType   .setOnItemSelectedListener(this);
        spinner_campType   .setAdapter(arrayAdapter);

        database = FirebaseDatabase.getInstance();
        Ref = database.getReference();

        arguments = getIntent().getExtras();
        currentUser = (User) arguments.getSerializable(User.class.getSimpleName());

        tv_appname.setText(currentUser.name);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.bt_name){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLvl3.this,
                    R.style.Theme_AppCompat_Dialog_Alert);
            final View view = getLayoutInflater().inflate(R.layout.dialog_et, null);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText editText = view.findViewById(R.id.et_dialog);
                    bt_name.setText(editText.getText().toString());
                    name = editText.getText().toString();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (id == R.id.bt_campNumber){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLvl3.this,
                    R.style.Theme_AppCompat_Dialog_Alert);
            final View view = getLayoutInflater().inflate(R.layout.dialog_et, null);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText editText = view.findViewById(R.id.et_dialog);
                    bt_campNumber.setText(editText.getText().toString());
                    campNumber = Integer.parseInt((editText.getText().toString()));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (id == R.id.bt_age){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLvl3.this,
                    R.style.Theme_AppCompat_Dialog_Alert);
            final View view = getLayoutInflater().inflate(R.layout.dialog_et, null);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText editText = view.findViewById(R.id.et_dialog);
                    bt_age.setText(editText.getText().toString());
                    age = Integer.parseInt((editText.getText().toString()));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (id == R.id.bt_login){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLvl3.this,
                    R.style.Theme_AppCompat_Dialog_Alert);
            final View view = getLayoutInflater().inflate(R.layout.dialog_et, null);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText editText = view.findViewById(R.id.et_dialog);
                    bt_login.setText(editText.getText().toString());
                    login = editText.getText().toString().hashCode();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (id == R.id.bt_password){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLvl3.this,
                    R.style.Theme_AppCompat_Dialog_Alert);
            final View view = getLayoutInflater().inflate(R.layout.dialog_et, null);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText editText = view.findViewById(R.id.et_dialog);
                    bt_password.setText(editText.getText().toString());
                    password = editText.getText().toString().hashCode();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (id == R.id.bt_password_confirm){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLvl3.this,
                    R.style.Theme_AppCompat_Dialog_Alert);
            final View view = getLayoutInflater().inflate(R.layout.dialog_et, null);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText editText = view.findViewById(R.id.et_dialog);
                    bt_password_confirm.setText(editText.getText().toString());
                    passwordConfirm = editText.getText().toString().hashCode();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (id == R.id.bt_lvl){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityLvl3.this,
                    R.style.Theme_AppCompat_Dialog_Alert);
            final View view = getLayoutInflater().inflate(R.layout.dialog_et, null);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    EditText editText = view.findViewById(R.id.et_dialog);
                    bt_lvl.setText(editText.getText().toString());
                    lvl = Integer.parseInt(editText.getText().toString());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (id == R.id.bt_add_user){
            if (password != passwordConfirm)
                tv_error.setText("Пароли не совпадают");
            else
                tv_error.setText("Ошибок нет");
                User user = new User(login, name, password, lvl, age, campNumber, campType);
                Ref
                        .child("User")
                        .child(String.valueOf(login))
                        .setValue(user);
                Toast.makeText(MainActivityLvl3.this, "Добавлено!", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.bt_settings){
            Intent intentK = new Intent(v.getContext(), SettingsActivity.class);
            intentK.putExtra(User.class.getSimpleName(), currentUser);
            v.getContext().startActivity(intentK);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        campType = (String) spinner_campType.getSelectedItem();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
