package com.example.findlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class BatteryActivity extends AppCompatActivity {
    private BroadcastReceiver receiver;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BroadcastReceiver receiver = new BatteryBroadcastReciver();
                //只接受电量变化的intent
                IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                registerReceiver(receiver, intentFilter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        BroadcastReceiver receiver = new BatteryBroadcastReciver();
        //只接受电量变化的intent
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}