package com.vados.calculator;

import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

//
public class CalcActions {
    private final ArrayList<String> mathSigns = new ArrayList<>(); // массив хранящий знаки между цифрами
    private final ArrayList<Float> nums = new ArrayList<>(); // массив хранящий цифры
    private float bufResult = 0f;

    public void setMathSigns(String sign) {
        this.mathSigns.add(sign);
    }

    public void setNums(float num) {
        this.nums.add(num);
    }

    public int getSignsSize(){
        return mathSigns.size();
    }

    public int getNumsSize(){
        return nums.size();
    }

    //Очищаем данные от введённых чисел и знаков
    public void remove(){
        mathSigns.clear();
        nums.clear();
    }

    public float getResult(@NonNull String res){
        String errorMessage = "";

        if (res.indexOf('-') == 0){
            bufResult = nums.get(0)*(-1);
            mathSigns.remove(0);
        }
        else bufResult = nums.get(0);

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

    void removeFirstSign(){
        mathSigns.remove(0);
    }

}
