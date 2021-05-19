package com.example.salarycalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {

    AnyChartView mAnyChartView;
    private Button mBack;
    private Button mHome;
    private TextView test;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        double brutto = intent.getDoubleExtra("number0", 0);
        double emerytalna = intent.getDoubleExtra("number1", 0);
        double rentowa = intent.getDoubleExtra("number2", 0);
        double chorobowa = intent.getDoubleExtra("number3", 0);
        double zdrowotna = intent.getDoubleExtra("number4", 0);
        double zaliczka = intent.getDoubleExtra("number5", 0);
        double netto = intent.getDoubleExtra("number6", 0);
        double requestCode = intent.getDoubleExtra("number7", 0);

        String[] contributionsName = {"Ubezpieczenie emerytalne", "Ubezpieczenie rentowe",
                "Ubezpieczenie chorobowe", "Ubezpieczenie zdrowotne", "Zaliczka na podatek", "Twoja wypłata (kwota netto)"};
        double[] salary = {emerytalna, rentowa, chorobowa, zdrowotna, zaliczka, netto};

        mAnyChartView = findViewById(R.id.any_chart_view);
        mBack = findViewById(R.id.back);
        mHome = findViewById(R.id.home);
        test = findViewById(R.id.test);
        test.setText("Kwota brutto: " + Double.toString(brutto));

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1;
                if (requestCode == 1) {
                    intent1 = new Intent(Details.this, UmowaPraca.class);
                    startActivity(intent1);
                } else if (requestCode == 2) {
                    intent1 = new Intent(Details.this, UmowaZlecenie.class);
                    startActivity(intent1);
                } else if (requestCode == 3) {
                    intent1 = new Intent(Details.this, UmowaDzielo.class);
                    startActivity(intent1);
                }
            }
        });

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for (int i = 0; i < contributionsName.length; i++) {
            dataEntries.add(new ValueDataEntry(contributionsName[i], salary[i]));
        }
        pie.data(dataEntries);
        mAnyChartView.setChart(pie);
    }
}