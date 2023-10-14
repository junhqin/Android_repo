package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextInputEditText usernameInputLayout = findViewById(R.id.username_input);
        TextInputEditText passwordInputLayout = findViewById(R.id.password_input);
        Button btnGoToSecond = findViewById(R.id.login_button);
        btnGoToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameInputLayout.getText().toString();
                String password = passwordInputLayout.getText().toString();


                if ("qjh".equals(username) && "1234".equals(password)) {
                    Intent intent = new Intent("two_start");
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {

                    Toast.makeText(MainActivity.this, "用户名和密码错误!", Toast.LENGTH_LONG).show();
                }
            }
        });


        ImageButton btnGoToInternet = findViewById(R.id.imageButton2);
        btnGoToInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.baidu.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        ImageButton btnGoToEmail = findViewById(R.id.imageButton1);
        btnGoToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri smsUri = Uri.parse("sms:");
                Intent intent = new Intent(Intent.ACTION_SENDTO, smsUri);
                startActivity(intent);
            }
        });

        ImageButton btnGoToCalculate = findViewById(R.id.imageButton3);
        btnGoToCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, calculateActivity.class);
                startActivity(intent);
            }
        });



    }
}