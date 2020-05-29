package ru.dreamteam.goldse4enie.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.RegistredUser;
import ru.dreamteam.goldse4enie.domain.User;


public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton bt_home;
    private ImageButton bt_logout;

    private TextView tv_age;
    private TextView tv_name;
    private TextView tv_campNumber;
    private TextView tv_campType;
    private TextView tv_appname;

    private User currentUser;
    private Intent intent;
    private String wifiMac;
    private HashMap<String,Object> RUL;

    private boolean f = false;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.bt_home:
                if(currentUser.lvl  == 1) {
                    intent = new Intent(v.getContext(), MainActivityLvl1.class);
                }
                else {
                    intent = new Intent(v.getContext(), MainActivityLvl2.class);
                }
                intent.putExtra(User.class.getSimpleName(), currentUser);
                v.getContext().startActivity(intent);
                break;
            case R.id.bt_logout:
                AlertDialog.Builder builderOtr = new AlertDialog.Builder(SettingsActivity.this,
                        R.style.Theme_AppCompat_Dialog_Alert);
                final View viewOtr = getLayoutInflater().inflate(R.layout.dialog_exit, null);
                builderOtr.setView(viewOtr);
                builderOtr.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        f = true;
                        Log.d("RemoveUser","" + myRef.child("Registred").child(wifiMac).removeValue().isSuccessful());
                        intent = new Intent(v.getContext(), MainActivity.class);
                        v.getContext().startActivity(intent);
                    }
                });
                builderOtr.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogOtr = builderOtr.create();
                alertDialogOtr.show();

        }
    }

    public void init(){
        Bundle arguments = getIntent().getExtras();
        currentUser = (User) arguments.getSerializable(User.class.getSimpleName());

        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiMac = wifiManager.getConnectionInfo().getMacAddress();

        myRef = database.getReference();

        final GenericTypeIndicator<HashMap<String, Object>> RTY = new GenericTypeIndicator<HashMap<String, Object>>(){};

        myRef.child("Registred").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RUL = dataSnapshot.getValue(RTY);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bt_home = findViewById(R.id.bt_home);
        bt_logout = findViewById(R.id.bt_logout);
        bt_home.setOnClickListener(this);
        bt_logout.setOnClickListener(this);

        tv_age = findViewById(R.id.tv_age);
        tv_name = findViewById(R.id.tv_name);
        tv_campNumber = findViewById(R.id.tv_campNumber);
        tv_campType = findViewById(R.id.tv_campType);
        tv_appname = findViewById(R.id.tv_appname);

        tv_age       .setText("Возраст: " + currentUser.age);
        tv_name      .setText("Имя: " + currentUser.name);
        tv_campNumber.setText("Отряд: " + currentUser.campNumber);
        tv_campType  .setText("Направление: " + currentUser.campType);
        tv_appname  .setText(currentUser.name);


    }
}
