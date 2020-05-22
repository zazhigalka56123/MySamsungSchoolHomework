package ru.dreamteam.goldse4enie.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Path;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.domain.TimeList;
import ru.dreamteam.goldse4enie.domain.User;
import ru.dreamteam.goldse4enie.getters.GetLoginRequest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login_et;
    private TextView password_et;
    private Button entrance_bt;

    private FirebaseAnalytics mFirebaseAnalytics;

    private String login;
    private String password;

    User CurrentUser;

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

        User us = new User(1, "1", "1", 1, 15, 1, "Наука");
        User us2 = new User(1, "2", "1", 2, 15, 1, "Наука");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");
        myRef.child("1").setValue(us);
        myRef.child("2").setValue(us2);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        setContentView(R.layout.activity_main);

        login_et = findViewById(R.id.et_login);
        password_et = findViewById(R.id.et_password);
        entrance_bt = findViewById(R.id.bt_entrance);

        entrance_bt.setOnClickListener(this);

        Date date = new Date();
        Log.d("Date", String.valueOf(date));


    }

    @Override
    protected void onStart() {
        
        super.onStart();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_entrance:
                login = String.valueOf(login_et.getText());
                password = String.valueOf(password_et.getText());
                @SuppressLint("RestrictedApi") Path aa = myRef.child("1").getPath();
                Log.d("li",aa.toString());
                myRef.child(login).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        CurrentUser =  dataSnapshot.getValue(User.class);
                        //CurrentUser = new User(dataSnapshot.child("id").getValue(Integer.class),"1",
                        //        "1",2,15,1,"Наука");
                        Log.d("li","" + dataSnapshot.child("id").getValue(Integer.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("li", "Failed to read value.", error.toException());
                    }
                });

        }

        if(CurrentUser != null) {
            switch (Enter(CurrentUser, password)) {
                case 1:
                    Intent intentlvl1 = new Intent(v.getContext(), MainActivityLvl1.class);
                    intentlvl1.putExtra(User.class.getSimpleName(),  CurrentUser);
                    v.getContext().startActivity(intentlvl1);
                    break;
                case 2:
                    Intent intentlvl2 = new Intent(v.getContext(), MainActivityLvl2.class);
                    v.getContext().startActivity(intentlvl2);
                    break;
                case 3:
                    //переход на лвл три
                    break;
                case 0:
                    Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }



    private int Enter(User us,String password){
        Log.d("enter",password + us.password);
        if(us.password.equals(password)){
            return us.lvl;
        }
        else{
            return 0;
        }
    }

}