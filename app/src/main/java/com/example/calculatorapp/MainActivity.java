package com.example.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    List<String> historyOfEntries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textView = findViewById(R.id.IO);
        historyOfEntries = new ArrayList<>();
    }

    public void buttonOnClick(View v){
        Button button = (Button) v;
        textView.append((button.getText()));
    }

    public void addOnClick(View v){
        String string = textView.getText().toString();
        historyOfEntries.add(string);
        historyOfEntries.add("+");
        textView.setText("");
    }

    public void minusOnClick(View v){
        String string = textView.getText().toString();
        historyOfEntries.add(string);
        historyOfEntries.add("-");
        textView.setText("");
    }

    public void timesOnClick(View v){
        String string = textView.getText().toString();
        historyOfEntries.add(string);
        historyOfEntries.add("*");
        textView.setText("");
    }

    public void divideOnClick(View v){
        String string = textView.getText().toString();
        historyOfEntries.add(string);
        historyOfEntries.add("/");
        textView.setText("");
    }

    public void equalsOnClick(View v){
        historyOfEntries.add(textView.getText().toString());
        double answer = calculateAnswer(historyOfEntries);
        textView.setText(String.valueOf(answer));
    }

    private double calculateAnswer(List<String> historyOfEntries){
        double answer = 0;
        boolean readyToCalc = false;
        String operator = "";
        boolean i = false;
        for(String element : historyOfEntries){
            if(!i){
                answer = Double.parseDouble(element);
                i = true;
            } else {
                if(element.equals("+")){
                    readyToCalc = true;
                    operator = "+";

                } else if(element.equals("-")){
                    readyToCalc = true;
                    operator = "-";

                } else if (element.equals("*")){
                    readyToCalc = true;
                    operator = "*";

                } else if (element.equals("/")) {
                    readyToCalc = true;
                    operator = "/";

                } else {
                    if (readyToCalc){
                        answer = operation(element, operator, answer);
                    }
                    readyToCalc = false;
                    operator = "";
                }
            }
        }

        return answer;

    }

    private double operation(String element, String operator, double currentAnswer){
        double operand = Double.parseDouble(element);
        double answer;
        if(operator.equals("+")){
            answer = currentAnswer + operand;
            historyOfEntries = new ArrayList<>();
            historyOfEntries.add(String.valueOf(answer));
        } else if (operator.equals("-")){
            answer = currentAnswer - operand;
            historyOfEntries = new ArrayList<>();
            historyOfEntries.add(String.valueOf(answer));
        } else if (operator.equals("*")){
            answer = currentAnswer * operand;
            historyOfEntries = new ArrayList<>();
            historyOfEntries.add(String.valueOf(answer));
        } else if (operator.equals("/")){
            answer = currentAnswer / operand;
            historyOfEntries = new ArrayList<>();
            historyOfEntries.add(String.valueOf(answer));
        } else {
            answer = 0;
        }

        return answer;
    }

    public void clearScreen(View v){
        textView.setText("");
    }

    public void clearAllAndMemory(View v){
        clearScreen(v);
        this.historyOfEntries = new ArrayList<>();
    }
}
