package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        TextView text = findViewById(R.id.textView2);
        Intent intent = getIntent();
        int result = intent.getIntExtra("result",-1);
        text.setText(""+result);
    }
}