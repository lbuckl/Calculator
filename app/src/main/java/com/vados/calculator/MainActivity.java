package com.vados.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView_result;
    private TextView textView_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();


        //textView_info.setText("Привет");

    }

    private void initialization(){
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        Button button_point = findViewById(R.id.button_point);
        Button button_result = findViewById(R.id.button_result);
        Button button_plus = findViewById(R.id.button_plus);
        Button button_minus = findViewById(R.id.button_minus);
        Button button_mult = findViewById(R.id.button_mult);
        Button button_dif = findViewById(R.id.button_dif);

        textView_info = findViewById(R.id.textView_info);
        textView_info = findViewById(R.id.textView_result);

    }
}