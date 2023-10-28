package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import com.example.findlist.MusicPlayerActivity;
import com.example.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class MediaPlayerService extends Service {
    //构造函数
    private MediaPlayer mediaPlayer;//多媒体对象
    private Timer timer;//时钟对象


    @Override
    public IBinder onBind(Intent intent) {
//        throw new UnsupportedOperationException("Not yet implemented");
        return new MusicControl();
    }


    public MediaPlayerService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //增加计时器
    public void addTimer(){
        if(timer == null){
            timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    int duration = mediaPlayer.getDuration();
                    int currentPos = mediaPlayer.getCurrentPosition();
                    Message msg = MusicPlayerActivity.handler.obtainMessage();//创建消息对象
                    Bundle bundle = new Bundle();
                    bundle.putInt("duration", duration);
                    bundle.putInt("currentPos", currentPos);
                    msg.setData(bundle);
                    MusicPlayerActivity.handler.sendMessage(msg);
                }
            };
            timer.schedule(task, 5,500);
        }
    }
    public class MusicControl extends Binder{
        public void init(){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.star);
            addTimer();
        }
        public void play(){
            mediaPlayer.start();
        }
        public void pause(){
            mediaPlayer.pause();
        }
        public void stop(){
            mediaPlayer.stop();
            mediaPlayer.release();
            timer.cancel();
        }
        public void seekTo(int ms){
            mediaPlayer.seekTo(ms);
        }
    }
}