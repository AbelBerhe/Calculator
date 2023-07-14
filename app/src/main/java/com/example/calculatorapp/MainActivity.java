package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button clear, zero, one, two, three, four, five, six, seven, eight, nine, add, sub, mul, div, dot, backSpace, negPos, equals, bracket;
    TextView inputText, solution;
    boolean operator = false;
    boolean isOpNegative = false;
    boolean isParentheses = false;
    boolean equalOperator = false;
    String expression = "";
    String validPattern = "^(-{0,1})[(]{0,1}[-]{0,1}(\\d+(\\.\\d+)?[)]{0,1}[\\+\\-\\*\\/]{1}[(]{0,1}[-]{0,1})+\\d+(\\.\\d+)?[)]{0,1}$";//regex pattern to check for a valid expression
    DecimalFormat df = new DecimalFormat("0.0");
    Calculator calc = new Calculator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hook member variables to the id's in the GUI
        clear = findViewById(R.id.btn_c);
        inputText = findViewById(R.id.d_input);
        solution = findViewById(R.id.textView_out);
        zero = findViewById(R.id.btn_0);
        one = findViewById(R.id.btn_1);
        two = findViewById(R.id.btn_2);
        three = findViewById(R.id.btn_3);
        four = findViewById(R.id.btn_4);
        five = findViewById(R.id.btn_5);
        six = findViewById(R.id.btn_6);
        seven = findViewById(R.id.btn_7);
        eight = findViewById(R.id.btn_8);
        nine = findViewById(R.id.btn_9);
        add = findViewById(R.id.btn_addition);
        sub = findViewById(R.id.btn_subtration);
        mul = findViewById(R.id.btn_multiplication);
        div = findViewById(R.id.btn_division);
        backSpace = findViewById(R.id.btn_backSpace);
        dot = findViewById(R.id.btn_dot);
        negPos = findViewById(R.id.btn_pn);
        equals = findViewById(R.id.btn_equals);
        bracket = findViewById(R.id.btn_bracket);
    }


    //build expression and set the input text
    public void buildExpression(String character) {
        if (!operator || isParentheses || isOpNegative) {
            expression += character;
            inputText.setText(expression);
            isParentheses = false;
            isOpNegative = false;
        } else {
            expression = expression.substring(0, expression.length() - 1) + character;
            inputText.setText(expression);
        }
    }

    public void buttonEquals(View view) {
        equalOperator = true;
        validateAndDoCalculation();
    }

    public void clearButton(View view) { // clear button
        inputText.setText("");
        solution.setText("");
        expression = "";
        operator = true;
    }

    public void backSpace(View view) { // backspace button
        if (expression.length() > 0) {
            expression = expression.substring(0, expression.length() - 1);
            inputText.setText(expression);
            validateAndDoCalculation();
        }
    }


    public void buttonZero(View view) { // button zero
        operator = false;
        buildExpression(zero.getText().toString());

    }

    public void buttonOne(View view) { // button one
        operator = false;
        buildExpression(one.getText().toString());

    }

    public void buttonTwo(View view) { // button two
        operator = false;
        buildExpression(two.getText().toString());
    }

    public void buttonThree(View view) { // button three
        operator = false;
        buildExpression(three.getText().toString());

    }

    public void buttonFour(View view) { // button four
        operator = false;
        buildExpression(four.getText().toString());
    }

    public void buttonFive(View view) { // button five
        operator = false;
        buildExpression(five.getText().toString());
    }

    public void buttonSix(View view) { // button six
        operator = false;
        buildExpression(six.getText().toString());
    }

    public void buttonSeven(View view) { // button seven
        operator = false;
        buildExpression(seven.getText().toString());
    }

    public void buttonEight(View view) { // button eight
        operator = false;
        buildExpression(eight.getText().toString());
    }

    public void buttonNine(View view) { // button nine
        operator = false;
        buildExpression(nine.getText().toString());
    }


    //operator buttons
    public void buttonAdd(View view) {
        validateAndDoCalculation();
        //add operator to the expression
        if (!expression.equals("")) {
            buildExpression(add.getText().toString());
        }
        operator = true;
    }


    public void buttonSub(View view) {
        validateAndDoCalculation();
        if (!expression.equals("")) {
            buildExpression(sub.getText().toString());
        }
        operator = true;
    }

    public void buttonMultiplication(View view) {
        validateAndDoCalculation();
        if (!expression.equals("")) {
            buildExpression(mul.getText().toString());
        }
        operator = true;
    }

    public void division(View view) {
        validateAndDoCalculation();
        if (!expression.equals("")) {
            buildExpression(div.getText().toString());
        }
        operator = true;
    }


    //Not well implemented
    public void buttonNegative(View view) {
        isOpNegative = true;
        if (expression.equals("") || (expression.charAt(expression.length() - 1) == '(')) {
            buildExpression("-");
        } else {
            buildExpression("(" + "-");
        }
    }

    public void buttonDot(View view) {
        if (!expression.equals("")) {
            buildExpression(".");
        }


    }

    public void addBracket(View view) {
        isParentheses = true;
        if (operator) {
            buildExpression("(");
        } else {
            buildExpression(")");
        }
    }


    public void validateAndDoCalculation() {
        Pattern pattern = Pattern.compile(validPattern);
        Matcher matcher = pattern.matcher(expression);
        boolean isValid = matcher.find();
        if (isValid) {
            double result = calc.evaluateExpression(expression);
            String formattedResult = df.format(result);
            solution.setText(formattedResult);
        } else {
            solution.setText("");
            if (equalOperator) {
                Toast.makeText(getApplicationContext(), "Invalid format used.", Toast.LENGTH_SHORT).show();
                equalOperator = false;
            }
        }
    }


}



