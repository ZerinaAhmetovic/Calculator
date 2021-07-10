package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    public void updateText(String strToAdd) {
        String oldString = display.getText().toString();
        int cursorPos = display.getSelectionStart();

        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            String ptOneString = oldString.substring(0, cursorPos);
            String ptTwoString = oldString.substring(cursorPos);
            display.setText(String.format("%s%s%s", ptOneString, strToAdd, ptTwoString));
            display.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN(View view) {
        updateText("0");
    }

    public void oneBTN(View view) {
        updateText("1");
    }

    public void twoBTN(View view) {
        updateText("2");
    }

    public void threeBTN(View view) {
        updateText("3");
    }

    public void fourBTN(View view) {
        updateText("4");
    }

    public void fiveBTN(View view) {
        updateText("5");
    }

    public void sixBTN(View view) {
        updateText("6");
    }

    public void sevenBTN(View view) {
        updateText("7");
    }

    public void eightBTN(View view) {
        updateText("8");
    }

    public void nineBTN(View view) {
        updateText("9");
    }

    public void clearBTN(View view) {
        display.setText("");
    }

    public void parenthesesBTN(View view) {
        int closedPar = 0;
        int openPar = 0;
        int cursorPos = display.getSelectionStart();
        int strLen = display.getText().length();

        for(int i=0; i<cursorPos; i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar+=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closedPar+=1;
            }
        }

        if(closedPar==openPar || display.getText().toString().substring(strLen-1,strLen).equals("(")){
            updateText("(");
        }
        else if(closedPar<openPar && !display.getText().toString().substring(strLen-1,strLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);
    }

    public void exponentBTN(View view) {
        updateText("^");
    }

    public void divideBTN(View view) {
        updateText("÷");
    }

    public void addBTN(View view) {
        updateText("+");
    }

    public void subtractBTN(View view) {
        updateText("-");
    }

    public void multiplyBTN(View view) {
        updateText("×");
    }

    public void pointBTN(View view) {
        updateText(".");
    }

    public void plusMinusBTN(View view) {
        updateText("+/-");
    }

    public void equalsBTN(View view) {

        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int txtLength = display.getText().length();

        if (cursorPos != 0 && txtLength != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

}