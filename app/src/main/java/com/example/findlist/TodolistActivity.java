package com.example.findlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodolistActivity extends AppCompatActivity {
    private TextView textView;
    private Calendar c;
    private Button addButton;
    private List<Map<String,Object>> mList;
    private SimpleAdapter taskListAdapter;
    private ListView taskListView;
    private EditText editTextTask;
    private int m_year,m_month,m_day;
    private Map<String, Object> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_todolist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textView = findViewById(R.id.date);
        editTextTask = findViewById(R.id.editTextTask);
        c = Calendar.getInstance();
        m_year = c.get(Calendar.YEAR);
        m_month = c.get(Calendar.MONTH);
        m_day = c.get(Calendar.DAY_OF_MONTH);
        map = new HashMap<>();
        textView.setText(m_year+"年"+(m_month+1)+"月"+m_day+"日");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });

        addButton = findViewById(R.id.buttonAddTask);
        taskListView = findViewById(R.id.taskList);
        mList = new ArrayList<>();

        taskListAdapter = new SimpleAdapter(this, mList, R.layout.todo_item,
                new String[]{"title", "date"},new int[]{R.id.tv_title, R.id.tv_date});



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = textView.getText().toString();
                String title = editTextTask.getText().toString();

                Log.d("todoList", title + date);

                Map<String, Object> newTaskMap = new HashMap<>();
                newTaskMap.put("title", title);
                newTaskMap.put("date", date);
                mList.add(newTaskMap);
                taskListAdapter.notifyDataSetChanged();
            }
        });

        taskListView.setAdapter(taskListAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mList.remove(i);
                taskListAdapter.notifyDataSetChanged();
                Toast.makeText(TodolistActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this,l1,m_year, m_month, m_day);
    }

    //设置日期监听器
    private OnDateSetListener l1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            m_year = year;
            m_month = monthOfYear;
            m_day = dayOfMonth;
            textView.setText(m_year+"年"+(m_month+1)+"月"+m_day+"日");//为TextView设置文本内容，重新显示日期
        }
    };
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
}