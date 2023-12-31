package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bottomnav.ChatFragment;
import com.example.bottomnav.FindFragment;
import com.example.bottomnav.MineFragment;

import com.example.fragment_static.viewpager_test.adpater.MyFragmentStateVpAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class anotherActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private BottomNavigationView mbottomNavigationView;
    private MyFragmentStateVpAdapter mStateVpAdapter;
    private List<Fragment> mFragmentList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        mViewPager = findViewById(R.id.vp);
        mbottomNavigationView = findViewById(R.id.bottom_menu);
        initData();

        mStateVpAdapter = new MyFragmentStateVpAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mStateVpAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onPagerScrolled(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mbottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if( item.getItemId()==R.id.menu_chat){
                    mViewPager.setCurrentItem(0);
                }

                else if (item.getItemId()==R.id.menu_find)
                {
                    mViewPager.setCurrentItem(1);
                }

                else if (R.id.menu_mine== item.getItemId()) {
                    mViewPager.setCurrentItem(2);
                }


                return true;

            }
        });
    }


    private void onPagerScrolled(int position) {
        switch (position) {
            case 0:
                mbottomNavigationView.setSelectedItemId(R.id.menu_chat);
                break;
            case 1:
                mbottomNavigationView.setSelectedItemId(R.id.menu_find);
                mbottomNavigationView.removeBadge(R.id.menu_find);
                break;
            case 2:
                mbottomNavigationView.setSelectedItemId(R.id.menu_mine);
                break;
            default:
                break;
        }
    }
    private void initData(){
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new ChatFragment());
        mFragmentList.add(new FindFragment());
        mFragmentList.add(new MineFragment());

    };
}