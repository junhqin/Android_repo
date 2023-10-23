package com.example.findlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

public class GuessActivity extends AppCompatActivity {
    private RadioGroup TomChoices;
    private RadioGroup jerryChoices;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TomChoices = findViewById(R.id.choices);
        jerryChoices = findViewById(R.id.choices2);
        resultTextView = findViewById(R.id.result);
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int player1ChoiceId = TomChoices.getCheckedRadioButtonId();
                int player2ChoiceId = jerryChoices.getCheckedRadioButtonId();

                String player1Choice = getChoiceFromId(player1ChoiceId);
                String player2Choice = getChoiceFromId(player2ChoiceId);

                String result = determineWinner(player1Choice, player2Choice);

                resultTextView.setText("游戏结果: " + result);
            } // 添加括号来结束OnClickListener的匿名内部类
        }); // 添加括号来结束setOnClickListener方法
    }

    private String getChoiceFromId(int choiceId) {
        RadioButton radioButton = findViewById(choiceId);
        return radioButton.getText().toString();
    }
    private String determineWinner(String choice1, String choice2) {

         if (choice1.equals("剪刀") && choice2.equals("石头")) {
             return "Jerry获胜";
         }
         else if (choice1.equals("剪刀") && choice2.equals("布")) {
             return "Tom获胜";
         }
         else if (choice1.equals("石头") && choice2.equals("布")) {
             return "Jerry获胜";
         }
         else if (choice1.equals("石头") && choice2.equals("剪刀")) {
             return "Tom获胜";
         }
         else if (choice1.equals("布") && choice2.equals("剪刀")) {
             return "Jerry获胜";
         }
         else if (choice1.equals("布") && choice2.equals("石头")) {
             return "Tom获胜";
         }


        // 默认情况下，平局
        return "平局";
    }

}
