package com.vados.calculator;

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

    public ArrayList<String> getSigns(){
        return mathSigns;
    }

    public ArrayList<Float> getNums(){
        return nums;
    }

    public float getResult(){
        bufResult = nums.get(0);
        /*for (int i = 0;i < nums.size();i++){
            if(mathSigns.get(i).equals("+")){
                bufResult = nums.get(i)
            }

        }*/
        return bufResult;
    }
}
