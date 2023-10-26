package com.example.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.findlist.GuessActivity;
import com.example.findlist.MusicPlayerActivity;
import com.example.findlist.TodolistActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FindFragment extends Fragment {
    private ListView mlistView;
    private List<Map<String,Object>> mList;
    private SimpleAdapter mlistAdapter;

    private int[] imgs = {
            R.drawable.tom,
            R.drawable.todo,
            R.drawable.video,
            R.drawable.battery
    };

    private String[] titles = {
            "猜拳游戏","TODO 清单","用Service实现简单音乐播放器",
            "用BroadcastReceiver实时监听电量"
    };

    private String[] descriptions = {
            "任务要求 采用相对布局设计一个两人猜拳游戏的UI界面。"+
                    "玩家选择RadioButton后显示出玩家选择的结果。 点击确定按钮后输出游戏结果。",
            "本次实验的目的是使用DatePicker/Dialog（或者第三方日期控件）和ListView/ RecyclerView实现一个TODO 清单小功能。 \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "任务要求 \n" +
                    "\n" +
                    "页面构成：添加待办事项按钮，事项输入，日期输入，TODO列表 \n" +
                    "\n" +
                    "功能要求： 通过添加按钮将事项/日期组成一个待办事项条目并添加到下方的列表里。 列表每一项都有一个完成按键,点击之后使用对话框提醒是否删除，确认则将该事项从列表中删除。 所有事项按时间排序。 日期采用DatePicker或者第三方日期选择。 TODO列表使用ListView或者RecyclerView实现。",
                    "本次实验的目的是让读者熟悉service组件的使用。使用Service的典型的例子是，用户一边在手机上操作其他应用程序，一边在手机上听音乐。而播放音乐就可以使用Service组件在后台来实现。",
                    "任务描述：本次实验的目的是通过BroadcastReceiver组件实现手机电量的实时监控。掌握Con-text.registerReceiver（）方法进行动态注册，通过<Receiver>标签在androidmanifest . xml进行注册。"

    };

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        mlistView = view.findViewById(R.id.findlist);

        mList = new ArrayList<>();


        for (int i = 0; i < titles.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", imgs[i%imgs.length]);
            map.put("title", titles[i]);
            map.put("content", descriptions[i]);
            mList.add(map);
        }
        mlistAdapter = new SimpleAdapter(getContext(),
                mList,
                R.layout.list_item,new String[]{"img","title","content"},
                new int[]{
                        R.id.iv_img,R.id.tv_title,R.id.tv_content
                }
        );
        mlistView.setAdapter(mlistAdapter);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), GuessActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), TodolistActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), MusicPlayerActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(getContext(), "功能尚未实现", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });






        return view;
    }
}