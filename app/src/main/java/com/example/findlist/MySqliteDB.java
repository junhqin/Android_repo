package com.example.findlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.findlist.bean.TODO;

public class MySqliteDB extends SQLiteOpenHelper {
//    public MySqliteDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
    private static final String DB_NAME = "mySQLite.db";
    private static  Context cont;
    //这里把数据库写死了
    public MySqliteDB(Context context){
        super(context,DB_NAME,null,1);
        cont = context;
        Toast.makeText(context,"成功创建代办事项", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todos("+
                        "id integer primary key autoincrement,"+
                        "content text,"+
                        "date text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(TODO todo){
        SQLiteDatabase db = getWritableDatabase();
        //存取数据键值对
        ContentValues values = new ContentValues();
        values.put("content", todo.getContent());
        values.put("date", todo.getDate());

        db.insert("todos", null, values);
        Toast.makeText(cont,"代办事项创建成功",Toast.LENGTH_SHORT).show();
    }

    public void delete(String taskId){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos", "id = ?", new String[]{taskId});
        Toast.makeText(cont, "该任务已完成",Toast.LENGTH_SHORT).show();
    }

}
