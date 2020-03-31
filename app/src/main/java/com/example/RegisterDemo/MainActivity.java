package com.example.RegisterDemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String channelId = "1";
    private String channelName = "Button";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.send_notice).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this,NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                @SuppressLint("WrongConstant")
                NotificationChannel notificationChannel = new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(notificationChannel);
                Notification notification = new NotificationCompat.Builder(this,channelId)
                        .setContentTitle("TITLE")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.penguin))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();
                assert manager != null;
                manager.notify(1,notification);
                Toast.makeText(this,"Notice",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
