package ru.dreamteam.goldse4enie.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.RegistredUser;
import ru.dreamteam.goldse4enie.domain.TimeList;
import ru.dreamteam.goldse4enie.domain.User;
import ru.dreamteam.goldse4enie.getters.GetLoginRequest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login_et;
    private TextView password_et;
    private Button entrance_bt;
    private CheckBox rememberUser;
    boolean remember;
    boolean isRemember = false;
    String wifiMac;

    private FirebaseAnalytics mFirebaseAnalytics;

    private int login;
    private int password;

    User currentUser;
    Map<String,Object>  UL;
    HashMap<String,User> ULC;

    public GetLoginRequest getLoginRequest;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private TimeList CurrentTimeList;

    @Override
    protected void onResume() {

        super.onResume();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiMac = wifiManager.getConnectionInfo().getMacAddress();

        myRef = database.getReference();

        ULC = new HashMap<>();

        final GenericTypeIndicator<HashMap<String,User>> ULTY = new GenericTypeIndicator<HashMap<String, User>>() {
        };

        myRef.child("Registred").child(wifiMac).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RegistredUser uk = dataSnapshot.getValue(RegistredUser.class);
                if(uk != null){
                    currentUser = uk.user;
                    isRemember = true;
                    Log.d("RegistredGET","Im here");
                }
                else {
                    myRef.child("User").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            ULC =  dataSnapshot.getValue(ULTY);
                            Log.d("GETUSER","downloaded" );
                            Log.d("GETUSER", String.valueOf(ULC));
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            Log.w("li", "Failed to read value.", error.toException());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        setContentView(R.layout.activity_main);

        login_et = findViewById(R.id.et_login);
        password_et = findViewById(R.id.et_password);
        entrance_bt = findViewById(R.id.bt_entrance);
        rememberUser = findViewById(R.id.checkBox);
        rememberUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                remember = isChecked;
            }
        });
        rememberUser.setOnClickListener(this);
        entrance_bt.setOnClickListener(this);

        Date date = new Date();
        Log.d("Date", String.valueOf(date));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("RegistredGET","" + (currentUser == null));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rememberUser.callOnClick();
            }
        },1200);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_entrance:
                login = String.valueOf(login_et.getText()).hashCode();
                password = String.valueOf(password_et.getText()).hashCode();
                currentUser = ULC.get(String.valueOf(login));
                Log.d("GETUSER", "login " + login);
                Log.d("GETUSER","" + (currentUser == null));
                break;
            case R.id.checkBox:

                break;
        }
        Log.d("RegistredGET","" + (currentUser == null));
        if(isRemember == true){
            if(currentUser != null) {
                switch (Enter(currentUser, currentUser.password)) {
                    case 1:
                        Intent intentlvl1 = new Intent(this, MainActivityLvl1.class);
                        intentlvl1.putExtra("from",  "Main");
                        intentlvl1.putExtra(User.class.getSimpleName(),  currentUser);
                        this.startActivity(intentlvl1);
                        break;
                    case 2:
                        Intent intentlvl2 = new Intent(this, MainActivityLvl2.class);
                        intentlvl2.putExtra(User.class.getSimpleName(),  currentUser);
                        this.startActivity(intentlvl2);
                        break;
                    case 3:
                        Intent intentlvl3 = new Intent(this, MainActivityLvl3.class);
                        intentlvl3.putExtra(User.class.getSimpleName(),  currentUser);
                        this.startActivity(intentlvl3);
                        break;
                    case 0:
                        break;
                }
            }
        }
        if(isRemember != true) {
            if (currentUser != null) {
                switch (Enter(currentUser, password)) {
                    case 1:
                        Intent intentlvl1 = new Intent(v.getContext(), MainActivityLvl1.class);
                        intentlvl1.putExtra("from", "Main");
                        intentlvl1.putExtra(User.class.getSimpleName(), currentUser);
                        v.getContext().startActivity(intentlvl1);
                        if (remember == true) {
                            myRef.child("Registred").child(wifiMac).setValue(new RegistredUser(currentUser, wifiMac));
                        }
                        break;
                    case 2:
                        Intent intentlvl2 = new Intent(v.getContext(), MainActivityLvl2.class);
                        intentlvl2.putExtra(User.class.getSimpleName(), currentUser);
                        v.getContext().startActivity(intentlvl2);
                        if (remember == true) {
                            myRef.child("Registred").child(wifiMac).setValue(new RegistredUser(currentUser, wifiMac));
                        }
                        break;
                    case 3:
                        Intent intentlvl3 = new Intent(v.getContext(), MainActivityLvl3.class);
                        intentlvl3.putExtra(User.class.getSimpleName(), currentUser);
                        v.getContext().startActivity(intentlvl3);
                        if (remember == true) {
                            myRef.child("Registred").child(wifiMac).setValue(new RegistredUser(currentUser, wifiMac));
                        }
                        break;
                    case 0:
                        Toast.makeText(getApplicationContext(), "try again", Toast.LENGTH_SHORT).show();
                        break;
                }
            } else {
            }
        }
    }



    private int Enter(User us,int password){
        Log.d("enter",password + " " + us.password);
        if(us.password == password){
            return us.lvl;
        }
        else{
            return 0;
        }
    }

    private void SetRemember(){

    }

}