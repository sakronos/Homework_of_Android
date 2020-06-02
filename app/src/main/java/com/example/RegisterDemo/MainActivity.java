package com.example.RegisterDemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_path;
    //private TextView tv_play;
    //private TextView tv_pause;

    MusicService.MyBinder myBinder;
    private MyConn myConn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        et_path = findViewById(R.id.et_path);
        findViewById(R.id.tv_play).setOnClickListener(this);
        findViewById(R.id.tv_pause).setOnClickListener(this);

        queryAuthority();
    }

    private void queryAuthority(){
        int hasReadExternalPermission;
        hasReadExternalPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasReadExternalPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        myConn = new MyConn();
        Intent intent = new Intent(this,MusicService.class);
        bindService(intent,myConn,BIND_AUTO_CREATE);
    }


    @Override
    public void onClick(View v) {
        String pathway = et_path.getText().toString().trim();
        File SD_path = Environment.getExternalStorageDirectory();
        File file = new File(SD_path,pathway);
        String path = file.getAbsolutePath();
        switch (v.getId()){
            case R.id.tv_play:
                if (file.exists()&& file.length()>0){
                    myBinder.play(path);
                }else{
                    Toast.makeText(this,"音频文件未找到",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_pause:
                myBinder.pause();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(myConn);
        super.onDestroy();
    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MusicService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
