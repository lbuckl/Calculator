package com.vados.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView_result;
    private TextView textView_info;
    private Button buttons[] = new Button[11]; // храним кнопки 0-9 и .
    private Button signs[] = new Button[4]; // храним массив с мат. действиями
    private String result = "";
    private Button button_result;
    boolean exit = false;
    CalcActions calcActions01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            initialization();
            clickListener();

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

        signs[0] = findViewById(R.id.button_plus);
        signs[1] = findViewById(R.id.button_minus);
        signs[2] = findViewById(R.id.button_mult);
        signs[3] = findViewById(R.id.button_dif);
        button_result = findViewById(R.id.button_result);

        textView_info = findViewById(R.id.textView_info);
        textView_info = findViewById(R.id.textView_result);

        calcActions01 = new CalcActions();

    }

    public void clickListener(){
        //запускаем подслушку на основные кнопки
        for (Button e:buttons) {
            e.setOnClickListener(v -> {
                result += e.getText();
                printEnters();
            });
        }

        //Операция сложения
        signs[0].setOnClickListener(v -> {
            calcActions01.setMathSigns("+");
            result += signs[0].getText();
            printEnters();
        });
        //Операция вычитания
        signs[1].setOnClickListener(v -> {
            calcActions01.setMathSigns("-");
            result += signs[1].getText();
            printEnters();
        });
        //Операция умножения
        signs[2].setOnClickListener(v -> {
            calcActions01.setMathSigns("*");
            result += signs[2].getText();
            printEnters();
        });
        //Операция деления
        signs[3].setOnClickListener(v -> {
            calcActions01.setMathSigns("/");
            result += signs[3].getText();
            printEnters();
        });

        //Выводим результат
        button_result.setOnClickListener(v -> {
            // разделяем текст на элементы для мат операций
            String[] stringNums = result.split("\\+|\\-|\\*|\\/");

            //переводим string во float
            float[] floatNums = new float[stringNums.length];
            for (int i = 0;i < stringNums.length;i++) {
                calcActions01.setNums(Float.parseFloat(stringNums[i]));
                //floatNums[i] = Float.parseFloat(stringNums[i]);
            }
            result = String.valueOf(calcActions01.getResult());
            printEnters();
        });
    }

    private void printEnters(){
        textView_info.setText(result);
    }

}