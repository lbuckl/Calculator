package com.vados.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private TextView textView_appName;
    private TextView textView_appVersion;
    private TextView textView_style;
    private TextView textView_lang;
    private Spinner spinner_lang;
    private Spinner spinner_style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    //Инициализация объектов
    private void initialization(){
        textView_appName = findViewById(R.id.textView_AppName);
        textView_appName = findViewById(R.id.textView_AppVersion);
        textView_appName = findViewById(R.id.textView_Style);
        textView_appName = findViewById(R.id.textView_Lang);
        spinner_lang = findViewById(R.id.spinner_Lang);
        spinner_style = findViewById(R.id.spinner_Style);
    }
}