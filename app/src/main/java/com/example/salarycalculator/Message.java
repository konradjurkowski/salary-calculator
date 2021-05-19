package com.example.salarycalculator;

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void message(Context context, String mesg) {
        Toast.makeText(context, mesg, Toast.LENGTH_SHORT).show();
    }
}
