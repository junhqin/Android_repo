package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class calculateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("calculate","onCreate");
        setContentView(R.layout.activity_calculate);
        EditText numberInput1 = findViewById(R.id.editTextNumber);
        EditText numberInput2 = findViewById(R.id.editTextNumber2);
        Button btnGoToDialog = findViewById(R.id.toggleButton2);
        btnGoToDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1 = Integer.parseInt(numberInput1.getText().toString());
                int value2 = Integer.parseInt(numberInput2.getText().toString());
                int result = value2 * value1;
                Intent intent = new Intent(calculateActivity.this, DialogActivity.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });

        Button btnGoToAnother = findViewById(R.id.toggleButton);
        btnGoToAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value1 = Integer.parseInt(numberInput1.getText().toString());
                int value2 = Integer.parseInt(numberInput2.getText().toString());
                int result = value2 * value1;
                Intent intent = new Intent(calculateActivity.this, anotherActivity.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("calculate","onStart");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("calculate","onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("calculate","onResume");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.w("calculate","onStop");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.w("calculate","onRestart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("calculate","onDestroy");
    }
}
