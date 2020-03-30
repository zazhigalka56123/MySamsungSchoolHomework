package ru.dreamteam.goldse4enie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.dreamteam.goldse4enie.R;
import ru.dreamteam.goldse4enie.getters.GetLoginRequest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView login_et;
    private TextView password_et;
    private Button  entrance_bt;


    private String login;
    private String password;

    public GetLoginRequest getLoginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_et = findViewById(R.id.et_login);
        password_et = findViewById(R.id.et_password);
        entrance_bt = findViewById(R.id.bt_entrance);

        entrance_bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_entrance:
                login = String.valueOf(login_et.getText());
                password = String.valueOf(password_et.getText());
                getLoginRequest = new GetLoginRequest(login, password,this);

                switch (getLoginRequest.getRequest()){
                    case 1:
                        Intent intent = new Intent(v.getContext(), MainActivityLvl1.class);
                        intent.putExtra("appname",getLoginRequest.GetLogin());
                        v.getContext().startActivity(intent);
                        break;
                    case 2:
                        //переход на лвл два

                        break;
                    case 3:
                        //переход на лвл три
                        break;
                    case 0:
                        Toast.makeText(this,"try again",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
        }
    }
}
