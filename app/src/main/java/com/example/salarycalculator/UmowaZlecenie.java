package com.example.salarycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UmowaZlecenie extends AppCompatActivity {
    private double REQUEST_CODE = 2;
    List<Double> salaryItems = new ArrayList<>();
    private Button mBtn_Calculate;
    private EditText mSalary;
    private TextView mResult;
    private TextView mDetails;
    private Button mBack;
    private Button mHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umowa_zlecenie);

        Intent intent = new Intent(getApplicationContext(), Details.class);

        mBtn_Calculate = (Button) findViewById(R.id.calculate);
        mSalary = findViewById(R.id.salary);
        mResult = findViewById(R.id.result);
        mDetails = (TextView) findViewById(R.id.details);
        mBack = findViewById(R.id.back);
        mHome = findViewById(R.id.home);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String userInput = mSalary.getText().toString().trim();

                mBtn_Calculate.setEnabled(!userInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtn_Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getS= mSalary.getText().toString();
                double salary = Double.parseDouble(getS);
                if(salary <= 0) {
                    Message.message(getApplicationContext(), "Ustaw wartość większą od 0!");
                } else {
                    DecimalFormat df = new DecimalFormat("#.##");
                    double tmp = CalculateSalary(salary);
                    tmp = Double.parseDouble(df.format(tmp));
                    String result = Double.toString(tmp);
                    mResult.setText("Otrzymasz " + result + "PLN na rękę!");
                    String text = "Zobacz szczegóły";
                    SpannableString ss = new SpannableString(text);
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View widget) {
                            Toast.makeText(getApplicationContext(), "One", Toast.LENGTH_SHORT);
                        }
                    };
                    ss.setSpan(clickableSpan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mDetails.setText(ss);
                    mDetails.setMovementMethod(LinkMovementMethod.getInstance());
                    for (int i = 0; i < salaryItems.size(); i++) {
                        intent.putExtra("number"+i, salaryItems.get(i));
                    }
                }
            }
        });

        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent, (int)REQUEST_CODE);
            }
        });
    }

    private double CalculateSalary(double salary) {
        salaryItems.add(salary);
        double netto;
        double emerytalna = salary * 0.0976;
        emerytalna = zaokraglenie(emerytalna);
        salaryItems.add(emerytalna);
        double rentowa = salary * 0.015;
        rentowa = zaokraglenie(rentowa);
        salaryItems.add(rentowa);
        double chorobowa = 0;
        salaryItems.add(chorobowa);
        double spoleczne = emerytalna + rentowa + chorobowa;
        spoleczne = zaokraglenie(spoleczne);
        double zdrowotna = (salary - spoleczne) * 0.09;
        zdrowotna = zaokraglenie(zdrowotna);
        salaryItems.add(zdrowotna);
        double zdrowotna775 = (salary - spoleczne) * 0.0775;
        zdrowotna775 = zaokraglenie(zdrowotna775);
        double kup = (salary - spoleczne) * 0.2;
        kup = (int) (kup + 0.5);
        double podstawa = salary - spoleczne - kup;
        int pod_zaokraglenie = (int) (podstawa + 0.5);
        double zaliczka;
        double tmp;
        int zal_zaokraglenie;
        zaliczka = (((pod_zaokraglenie * 0.17)) - zdrowotna775);
        zal_zaokraglenie = (int) (zaliczka + 0.5);
        netto = salary - spoleczne - zdrowotna - zal_zaokraglenie;
        salaryItems.add((double)zal_zaokraglenie);
        netto = zaokraglenie(netto);
        salaryItems.add(netto);
        salaryItems.add(REQUEST_CODE);
        return netto;
    }

    private double zaokraglenie(double number) {
        number *= 100;
        number = Math.round(number);
        number /= 100;

        return number;
    }
}