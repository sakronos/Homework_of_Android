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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.send_notice).setOnClickListener(this);
        imageView = (ImageView)findViewById(R.id.imageView);

    }

    private class UpdateImageView extends AsyncTask<Integer, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Integer... integers) {
            publishProgress(0);
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.penguin,null));
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //imageView.clearColorFilter();
            //imageView.setImageDrawable(getResources().getDrawable(R.drawable.penguin,null));
            super.onPostExecute(aBoolean);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                new UpdateImageView().execute();
                Toast.makeText(this,"点击",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
