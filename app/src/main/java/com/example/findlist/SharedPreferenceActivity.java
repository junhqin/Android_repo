package com.example.findlist;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

//键值对进行传递
public class SharedPreferenceActivity extends AppCompatActivity {
    private Button btn_save, btn_restore, btn_fsave, btn_fdelete;
    private EditText et_name, et_age, et_married;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        init();
    }

    public void init() {
        btn_restore = findViewById(R.id.btn_restore);
        btn_save = findViewById(R.id.btn_save);
        et_age = findViewById(R.id.et_age);
        et_married = findViewById(R.id.et_married);
        et_name = findViewById(R.id.et_name);
        btn_fdelete = findViewById(R.id.btn_fdelete);
        btn_fsave = findViewById(R.id.btn_fsave);
        StringBuilder sb = new StringBuilder();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });
        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get(view);
            }
        });
        btn_fsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fsave(view);
            }
        });
        btn_fdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferenceActivity.this.deleteFile("data ");
                Toast.makeText(view.getContext(), "File Deleted", Toast.LENGTH_SHORT).show();
            }
        });



        try{
            FileInputStream input = openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(input));
            String line;
            List <String> datalist = new ArrayList<>();
            while((line = bufferedReader.readLine())!=null){
                datalist.add(line);
            }
            et_name.setText(datalist.get(0));
            et_age.setText(datalist.get(1));
            et_married.setText(datalist.get(2));
        } catch (IOException e) {
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void save(View view) {
        Log.d("12345","PASS");
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String married = et_married.getText().toString();
        SharedPreferences record = getSharedPreferences("record", MODE_PRIVATE);
        SharedPreferences.Editor editor = record.edit();
        editor.putString("name", name);
        editor.putString("age", age);
        editor.putString("married", married);
        editor.commit();//提交当前数据 （editor.apply更快，是暂时读入内存，适合的时候再读入磁盘）
    }

    public void fsave(View view) {
        try {
            FileOutputStream output = openFileOutput("data", MODE_PRIVATE);
//            FileInputStream fsave = openFileInput("data");
//            bufferedReader = new BufferedReader(new InputStreamReader(fsave));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
            String name = et_name.getText().toString();
            String age = et_age.getText().toString();
            String married = et_married.getText().toString();
            String result = name + "\n"+ age + "\n" + married;
            bufferedWriter.write(result);
            Toast.makeText(this, "Data saved to file", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(this, "Error saving data to file", Toast.LENGTH_SHORT).show();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void get(View view) {
        SharedPreferences record = getSharedPreferences("record", MODE_PRIVATE);
        String a1 = record.getString("name", "");
        String b1 = record.getString("age", "");
        String c1 = record.getString("married", "");
        Toast.makeText(this, "name is " + a1 + "\n" + "age is " + b1 + "\n" + "married is " + c1, Toast.LENGTH_LONG).show();
    }
}