package com.vados.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements Constants{

    private TextView textView_appName;
    private TextView textView_appVersion;
    private TextView textView_style;
    private TextView textView_lang;
    private Spinner spinner_lang;
    private Spinner spinner_style;
    private Button button_applySettings;
    int style = 0;
    String sStyle = "Light";
    int lang = 0;
    String sLang = "Ru";

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
        button_applySettings = findViewById(R.id.button_applySettings);

        textView_appName.setText(appName);
        textView_appVersion.setText(appVersion);
    }

    public void clickListener(){

        //Спинер изменения Стиля
        spinner_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                style = spinner_style.getSelectedItemPosition();
                switch (style){
                    case 0:
                        sStyle = "Light";
                        break;
                    case 1:
                        sStyle = "Dark";
                        break;
                }
                textView_style.setText(sStyle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                style = 0;
                textView_style.setText(String.valueOf(style));
            }
        });

        //Спинер изменения языка
        spinner_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lang = spinner_lang.getSelectedItemPosition();
                switch (lang){
                    case 0:
                        sLang = "Ru";
                        break;
                    case 1:
                        sLang = "En";
                        break;
                }
                textView_lang.setText(sLang);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lang = 0;
                textView_lang.setText(sLang);
            }
        });

        //Кнопка применить
        button_applySettings.setOnClickListener(v -> {
            Intent intent = new Intent();
            //intent.putExtra("styleName", textView_style.getText().toString());
            intent.putExtra("styleName", style);
            setResult(RESULT_OK, intent);
            finish();
        });

    }

}