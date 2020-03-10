package com.example.RegisterDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView usr=(TextView)findViewById(R.id.usr);
        TextView pass=(TextView)findViewById(R.id.pass);


        Intent intent = getIntent();
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");

        usr.setText("用户名:"+username);
        pass.setText("密    码:"+password);
    }
}
