package com.vados.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Constants{

    //region глобальные данные
    private TextView textView_result;
    private TextView textView_info;
    private TextView textView_lastValue;
    private String result = "";
    private String lastVal = "";
    private String styleName = "11111111";
    private Button button_result;
    private Button button_reset;
    private Button button_settings;
    private CalcActions calcActions01;
    //endregion

    //Сохраняем данные перед пересозданием активити
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putString("result", result); // сохраняем результат
        instanceState.putString("enters", lastVal); //Сохраняем ввод
        instanceState.putStringArrayList("sig",calcActions01.getMathSigns()); //Сохраняем массив с введёнными мат. знаками
        instanceState.putString("info", (String) textView_info.getText()); // сохраняем результат
        instanceState.putString("styleName", (String) styleName); // сохраняем результат
    }

    //Возвращаем данные после пересоздания активити
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Возвращаем результат
        result = savedInstanceState.getString("result");
        textView_result.setText(result);

        //Возвращаем предыдущий ввод
        lastVal = savedInstanceState.getString("enters");
        textView_lastValue.setText(lastVal);

        //Возвращаем данные с введёнными мат. символами
        ArrayList<String> mathSigns = savedInstanceState.getStringArrayList("sig");
        for (int i = 0; i < mathSigns.size();i++){
            calcActions01.setMathSigns(mathSigns.get(i));
        }

        //Возвращаем инфо
        textView_info.setText(savedInstanceState.getString("info"));
        //Возвращаем стиль
        styleName = savedInstanceState.getString("styleName");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, styleName, Toast.LENGTH_SHORT).show();
        setTheme(codeStyleToStyleId(styleName));
        setContentView(R.layout.activity_main);
        initialization();
        clickListener();
    }

    //Возвращаем тему
    private int codeStyleToStyleId(String styleName) {
        switch (styleName) {
            case ("Light"):
                return R.style.Theme_Calculator;
            case ("Dark"):
                return R.style.Theme_CalculatorDark;
            default:
                return R.style.Theme_Calculator_Italian;
        }
    }

    //Преобразуем

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
        button_settings = findViewById(R.id.button_settings);

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

        //region математические операции и кнопка res
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
        //endregion

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

        //Кнопка настроек
        button_settings.setOnClickListener(v -> {
            Intent button_settings = new Intent(MainActivity.this,Settings.class);
            startActivityForResult(button_settings,1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        styleName = data.getStringExtra("styleName");
        textView_info.setText(styleName);
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
}