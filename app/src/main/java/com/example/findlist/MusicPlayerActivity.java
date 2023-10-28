package com.example.findlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.service.MediaPlayerService;

import java.util.Timer;
import java.util.TimerTask;

public class MusicPlayerActivity extends AppCompatActivity {
    private ImageView iv_disk;
    private static SeekBar sb;//进度条
    private static TextView tv_curTime;
    private static TextView tv_totalTime;
    private Button btn_play, btn_pause;
    private ObjectAnimator animator;
    private MediaPlayerService.MusicControl control_music;
    private Timer timer;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            control_music = (MediaPlayerService.MusicControl) iBinder;
            control_music.init();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //初始化组件参数
    public void init(){
        iv_disk = findViewById(R.id.iv_music);
        sb = findViewById(R.id.sb);
        tv_curTime = findViewById(R.id.tv_progressTime);
        tv_totalTime = findViewById(R.id.tv_totalTime);
        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);

        OnClick onClick = new OnClick();
        btn_play.setOnClickListener(onClick);
        btn_pause.setOnClickListener(onClick);
        animator =  ObjectAnimator.ofFloat(iv_disk, "rotation",0,360);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());//线性的，意味着匀速旋转
        animator.setRepeatCount(-1);
        Intent intent = new Intent(getApplicationContext(), MediaPlayerService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
        OnSeek onSeek = new OnSeek();
        sb.setOnSeekBarChangeListener(onSeek);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 当点击返回按钮时，结束当前活动
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //alt +insert
    class OnSeek implements  SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(progress == seekBar.getMax()){
                animator.pause();
            }
            if(fromUser){
                control_music.seekTo(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            control_music.pause();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            control_music.play();
        }
    }



    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_play:
                    control_music.play();
                    if (animator.isPaused()) {
                        animator.resume();
                    } else {
                        animator.start();
                    }
                    break;
                case R.id.btn_pause:
                    control_music.pause();
                    animator.pause();
                    break;

            }
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("music","pass");
        super.onDestroy();
        unbindService(conn);
        control_music.stop();
    }

    //获取从service传递过来的消息
    public static Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int duration = bundle.getInt("duration");
            int currentPos = bundle.getInt("currentPos");
            sb.setMax(duration);
            sb.setProgress(currentPos);
            String totalTime = ms2MinSec(duration);
            String currentTime = ms2MinSec(currentPos);
            tv_curTime.setText(currentTime);
            tv_totalTime.setText(totalTime);
        }
    };

    public static String ms2MinSec(int ms){
        int sec = ms / 1000;
        int min = sec/60;
        sec = sec - min*60;
        return String.format("%02d:%02d",min, sec);
    };

}