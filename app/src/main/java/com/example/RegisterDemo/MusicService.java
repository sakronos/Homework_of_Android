package com.example.RegisterDemo;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

import java.io.IOException;

public class MusicService extends Service {

    public MediaPlayer mediaPlayer;

    class MyBinder extends Binder {
        void play(String path,Activity activity) {

            try {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
                    mediaPlayer.setDataSource(path);
                    mediaPlayer.prepareAsync();

                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();

                        }
                    });
                    TextView textView = activity.findViewById(R.id.tv_play);
                    textView.setText("正在播放");
                }
                else {
                    int position = getCurrentProgress();
                    mediaPlayer.seekTo(position);
                    mediaPlayer.start();
                    TextView textView = activity.findViewById(R.id.tv_play);
                    textView.setText("正在播放");
                    textView = activity.findViewById(R.id.tv_pause);
                    textView.setText("暂停");
                    textView.setTextColor(Color.parseColor("#000000"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        void pause(Activity activity) {
            if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                TextView textView = activity.findViewById(R.id.tv_pause);
                textView.setTextColor(Color.parseColor("#ff0000"));
                textView.setText("已暂停");
                textView = activity.findViewById(R.id.tv_play);
                textView.setText("播放");
            }else if(mediaPlayer!=null&&(!mediaPlayer.isPlaying())){
                mediaPlayer.start();
                TextView textView = activity.findViewById(R.id.tv_pause);
                textView.setTextColor(Color.parseColor("#000000"));
                textView.setText("暂停");
                textView = activity.findViewById(R.id.tv_play);
                textView.setText("正在播放");
            }
        }
    }

    public int getCurrentProgress() {
        if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
            return mediaPlayer.getCurrentPosition();
        }else if (mediaPlayer!=null&&(!mediaPlayer.isPlaying())){
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();

    }
}
