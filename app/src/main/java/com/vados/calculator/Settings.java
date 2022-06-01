package com.vados.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements Constants{

    private TextView textView_appName;
    private TextView textView_appVersion;
    private TextView textView_style;
    private TextView textView_lang;
    private Spinner spinner_lang;
    private Spinner spinner_style;
    int Style = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initialization();
        clickListener();
    }

    //Инициализация объектов
    private void initialization(){
        textView_appName = findViewById(R.id.textView_AppName);
        textView_appVersion = findViewById(R.id.textView_AppVersion);
        textView_style = findViewById(R.id.textView_Style);
        textView_lang = findViewById(R.id.textView_Lang);
        spinner_lang = findViewById(R.id.spinner_Lang);
        spinner_style = findViewById(R.id.spinner_Style);

        textView_appName.setText(appName);
        textView_appVersion.setText(appVersion);
    }

    public void clickListener(){

        spinner_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Style = spinner_style.getSelectedItemPosition();
                textView_style.setText(String.valueOf(Style));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Style = 0;
                textView_style.setText(String.valueOf(Style));
            }
        });

    }

}