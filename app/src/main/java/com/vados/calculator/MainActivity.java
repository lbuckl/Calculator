package com.vados.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView_result;
    private TextView textView_info;
    private Button buttons[] = new Button[15];
    private String result = "";
    private Button button_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        clickListener();


        //textView_info.setText("Привет");

    }

    private void initialization(){
        buttons[0] = findViewById(R.id.button0);
        buttons[1] = findViewById(R.id.button1);
        buttons[2] = findViewById(R.id.button2);
        buttons[3] = findViewById(R.id.button3);
        buttons[4] = findViewById(R.id.button4);
        buttons[5] = findViewById(R.id.button5);
        buttons[6] = findViewById(R.id.button6);
        buttons[7] = findViewById(R.id.button7);
        buttons[8] = findViewById(R.id.button8);
        buttons[9] = findViewById(R.id.button9);
        buttons[10] = findViewById(R.id.button_point);
        buttons[11] = findViewById(R.id.button_plus);
        buttons[12] = findViewById(R.id.button_minus);
        buttons[13] = findViewById(R.id.button_mult);
        buttons[14] = findViewById(R.id.button_dif);
        button_result = findViewById(R.id.button_result);

        textView_info = findViewById(R.id.textView_info);
        textView_info = findViewById(R.id.textView_result);

    }

    public void clickListener(){
        //запускаем подслушку на основные кнопки
        for (Button e:buttons) {
            e.setOnClickListener(v -> {
                result += e.getText();
                printEnters();
            });
        }

        button_result.setOnClickListener(v -> {
            String[] nums = result.split("\\+|\\-|\\*|\\/");

        });
    }

    private void printEnters(){
        textView_info.setText(result);
    }
}