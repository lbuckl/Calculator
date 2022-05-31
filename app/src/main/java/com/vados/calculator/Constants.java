package com.vados.calculator;

import android.widget.Button;

public interface Constants {
    public final float appVersion = 1.5f;
    final Button[] buttons = new Button[11]; // храним кнопки 0-9 и .
    final Button[] signs = new Button[4]; // храним массив с мат. действиями
}
