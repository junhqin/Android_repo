package com.example.findlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import org.w3c.dom.Text;

//键值对进行传递
public class SharedPreferenceActivity extends AppCompatActivity {
    private Button btn_save,btn_restore;
    private EditText et_name,et_age,et_married;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        init();
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


    }

    public void init(){
        btn_restore=findViewById(R.id.btn_restore);
        btn_save = findViewById(R.id.btn_save);
        et_age = findViewById(R.id.et_age);
        et_married = findViewById(R.id.et_married);
        et_name = findViewById(R.id.et_name);
    }
    public void save(View view){
        String name = et_name .getText().toString();
        String age = et_age.getText().toString();
        String married = et_married.getText().toString();
        SharedPreferences record = getSharedPreferences("record", MODE_PRIVATE);
        SharedPreferences.Editor editor = record.edit();
        editor.putString("name", name);
        editor.putString("age",  age);
        editor.putString("married",married);
        editor.commit();//提交当前数据 （editor.apply更快，是暂时读入内存，适合的时候再读入磁盘）
    }

    public void get(View view){
        SharedPreferences record = getSharedPreferences("record", MODE_PRIVATE);
        String a1 = record.getString("name", "");
        String b1 = record.getString("age",  "");
        String c1 = record.getString("married","");
        Toast.makeText(this, "name is "+a1+"\n"+"age is "+b1+"\n"+"married is "+c1,Toast.LENGTH_LONG).show();
    }
}