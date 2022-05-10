package com.vados.calculator;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

//
public class CalcActions implements Serializable{
    private final ArrayList<String> mathSigns = new ArrayList<>(); // массив хранящий знаки между цифрами
    private final ArrayList<Float> nums = new ArrayList<>(); // массив хранящий цифры
    private float bufResult = 0f;

    public void setMathSigns(String sign) {
        this.mathSigns.add(sign);
    }


    public ArrayList<String> getMathSigns(){
        return mathSigns;
    }

    //Очищаем данные от введённых чисел и знаков
    public void remove(){
        mathSigns.clear();
        nums.clear();
    }

    public float getResult(@NonNull String res){
        String errorMessage = "";


        String[] stringNums = res.split("\\+|\\-|\\*|\\/");

        int k = 0; // начало отсчёта

        //Если нечайно ввели спереди символ, то обрезаем его
        if (res.indexOf("-") == 0 || res.indexOf("+") == 0||
                res.indexOf("*") == 0 || res.indexOf("/") == 0){
            k = 1;
        }

        //переводим string во float и добавляем в
        for (int i = k; i < stringNums.length;i++) {
            nums.add(Float.parseFloat(stringNums[i]));
        }
        //__________________________________________

        //Если первый символ "минус", то преобразуем число
        if (res.indexOf('-') == 0){
            bufResult = nums.get(0)*(-1);
            mathSigns.remove(0);
        }
        else bufResult = nums.get(0);

        //На вход должны подаваться корректные массивы (mathSigns < nums на 1)
        try{
            for (int i = 0;i < mathSigns.size();i++){
                if(mathSigns.get(i).equals("+")){
                    bufResult = bufResult + nums.get(i + 1);
                }

                if(mathSigns.get(i).equals("-")){
                    bufResult = bufResult - nums.get(i + 1);
                }

                if(mathSigns.get(i).equals("*")){
                    bufResult = bufResult * nums.get(i + 1);
                }

                if(mathSigns.get(i).equals("/")){
                    try{
                        bufResult = bufResult / nums.get(i + 1);
                    }catch (ArithmeticException e){
                        e.printStackTrace();
                        errorMessage = e.getMessage();
                    }
                    finally {
                        System.out.println(errorMessage);
                    }
                }

            }
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return bufResult;
    }


}
