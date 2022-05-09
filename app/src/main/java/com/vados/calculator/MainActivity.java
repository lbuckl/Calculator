package com.vados.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView_result;
    private TextView textView_info;
    private String result = "";
    private Button buttons[] = new Button[11]; // храним кнопки 0-9 и .
    private Button signs[] = new Button[4]; // храним массив с мат. действиями
    private Button button_result;
    private Button button_reset;
    boolean exit = false;
    CalcActions calcActions01;


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
        });


        //Выводим результат
        button_result.setOnClickListener(v -> {
            // разделяем текст на элементы для мат операций
            String[] stringNums = result.split("\\+|\\-|\\*|\\/");

            int k = 0; // начало отсчёта

            //Если нечайно ввели спереди символ, то обрезаем его
            if (result.indexOf("-") == 0 || result.indexOf("+") == 0||
                    result.indexOf("*") == 0 || result.indexOf("/") == 0){
                k = 1;
            }

            //переводим string во float
            for (int i = k; i < stringNums.length;i++) {
                calcActions01.setNums(Float.parseFloat(stringNums[i]));
            }

            //выводим результат
            if (!printLastError()){
                result = String.valueOf(calcActions01.getResult(result));
                printEnters();
            }
            else{
                result = "";
                printEnters();
            }

        });
    }

    //Функция для вывода результата на ТекстВью
    private void printEnters(){
        textView_result.setText(result);
    }

    //Функция обработки ошибок
    /**
     *  обрабатывает result на типичные ошибки, и не даёт завалиться программе
     *  1 - действий больше, чем чисел
     *  2 - деление на 0
     */
    private boolean printLastError(){
        boolean error = false;
        // проверяем на то 1 элемнт математичесий знак или нет, минус не берём в расчёт.
        if (result.indexOf("+") == 0|| result.indexOf("*") == 0 || result.indexOf("/") == 0){
            textView_info.setText("Вы ввели символ перед первой цифрой");
            calcActions01.removeFirstSign();
        }


        if (!error) textView_info.setText("");
        return error;
    }

    //при ошибке очищает массивы и сбрасывает результат
    void clearAll(){
        result = "";
        calcActions01.remove();
        printEnters();
    }
}