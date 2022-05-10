package com.vados.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView_result;
    private TextView textView_info;
    private TextView textView_lastValue;
    private String result = "";
    private String lastVal = "";
    private final Button[] buttons = new Button[11]; // храним кнопки 0-9 и .
    private final Button[] signs = new Button[4]; // храним массив с мат. действиями
    private Button button_result;
    private Button button_reset;
    CalcActions calcActions01;

    //Сохраняем данные перед пересозданием активити
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putString("result", result); // сохраняем результат
        instanceState.putString("enters", lastVal); //Сохраняем ввод
        instanceState.putStringArrayList("sig",calcActions01.getMathSigns()); //Сохраняем массив с введёнными мат. знаками
    }

    //Возвращаем данные после пересоздания активити
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Возвращаем результат
        result = savedInstanceState.getString("result");
        textView_result.setText(result);

        //Возвращаем предыдущий ввод
        textView_lastValue.setText(savedInstanceState.getString("enters"));

        //Возвращаем данные с введёнными мат. символами
        //int count = savedInstanceState.getString("sig").length();
        ArrayList<String> mathSigns = savedInstanceState.getStringArrayList("sig");
        for (int i = 0; i < mathSigns.size();i++){
            calcActions01.setMathSigns(mathSigns.get(i));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            initialization();
            clickListener();
    }

    //Инициализация объектов
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
        button_reset = findViewById(R.id.button_reset);

        textView_info = findViewById(R.id.textView_info);
        textView_result = findViewById(R.id.textView_result);
        textView_lastValue = findViewById(R.id.textView_lastValue);

        calcActions01 = new CalcActions();

    }

    //Основная функция для действий по нажатию кнопок
    public void clickListener(){

        //Запускаем подслушку на основные кнопки 0-9 и .
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

        //Стираем данные
        button_reset.setOnClickListener(v -> {
            calcActions01.remove();
            result = "";
            printEnters();
            textView_info.setText("");
        });


        //Выводим результат
        button_result.setOnClickListener(v -> {
            findError(result);
            lastVal = result;
            textView_lastValue.setText(lastVal);


            float fResult = calcActions01.getResult(result);
            result = String.valueOf(fResult);
            calcActions01.remove();

            if (fResult < 0) calcActions01.setMathSigns("-");
            printEnters();
        });
    }

    //Функция для вывода результата на ТекстВью
    private void printEnters(){
        textView_result.setText(result);
    }


    //функция поиска ошибок
    private boolean findError(String str){
        boolean res = false;

        //Ищем деление на ноль
        if (str.contains("/0")){
            textView_info.setText("Возможно деление на 0");
            return true;
        }

        if (str.indexOf("-") == 0 || str.indexOf("+") == 0||
                str.indexOf("*") == 0 || str.indexOf("/") == 0){
            textView_info.setText("Первый символ игнор.");
            return true;
        }

        if (!res) textView_info.setText("");
        return res;
    }

    //при ошибке очищает массивы и сбрасывает результат
    void clearAll(){
        result = "";
        calcActions01.remove();
        printEnters();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}