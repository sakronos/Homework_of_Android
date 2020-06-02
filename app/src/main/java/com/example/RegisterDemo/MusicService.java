package com.example.RegisterDemo;

import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

public class MusicService extends Service {

    public MediaPlayer mediaPlayer;

    class MyBinder extends Binder {
        void play(String path) {

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
                }
                else {
                    int position = getCurrentProgress();
                    mediaPlayer.seekTo(position);
                    mediaPlayer.start();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        void pause() {
            if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }else if(mediaPlayer!=null&&(!mediaPlayer.isPlaying())){
                mediaPlayer.start();
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
