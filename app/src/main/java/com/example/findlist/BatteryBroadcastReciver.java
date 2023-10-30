package com.example.findlist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatteryBroadcastReciver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
            //得到系统当前电量
            int level=intent.getIntExtra("level", 0);
            //取得系统总电量
            int total=intent.getIntExtra("scale", 100);
//            textView.setText("当前电量："+(level*100)/total+"%");
            //当电量小于15%时触发
            if(level<15){
                Toast.makeText(context, "当前电量已小于15%",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, "当前电量为："+level+"%",Toast.LENGTH_LONG).show();
            }
        }
    }

}