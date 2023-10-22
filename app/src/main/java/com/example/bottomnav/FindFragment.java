package com.example.bottomnav;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.R.layout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.findlist.GuessGameFragment;
import com.example.findlist.TodoListFragment;
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
            R.drawable.ic_launcher_background
    };

    private String[] titles = {
            "猜拳游戏","TODO 清单",""
    };

    private String[] descriptions = {
            "描述1","描述2"
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
                Fragment fragment;
                switch (position) {
                    case 0:
                        fragment = new GuessGameFragment();
                        break;
                    case 1:
                        fragment = new TodoListFragment();
                        break;
                    default:
                        Toast.makeText(getContext(), "功能尚未实现", Toast.LENGTH_SHORT).show();
                        return;
                }

                // 切换Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)  // 注意: 'fragment_container' 是您的FrameLayout的ID，您可能需要根据自己的ID进行修改
                        .addToBackStack(null)  // 如果您想允许用户通过返回按钮返回到上一个Fragment，添加这一行
                        .commit();
            }
        });




        return view;
    }
}