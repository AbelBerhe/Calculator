package com.example.calculatorapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public  double evaluateExpression(String exp) {
      //  String[] tokens = exp.split("(?=[-+*/])|(?<=[-+*/])"); // Split the expression at operators while preserving the operators themselves
        List<String> expr = splitExpression(exp);
        double result  = Double.parseDouble(expr.get(0)); // Initialize the result with the first operand

        for (int i = 1; i < expr.size(); i += 2) {
            String operator = expr.get(i);
            double operand = Double.parseDouble(expr.get(i+1));

            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    result /= operand;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        return result;

    }

    private  List<String> splitExpression(String expression) {
        List<String> splitList = new ArrayList<>();

        // Pattern to match numbers, operators, and parentheses
        Pattern pattern = Pattern.compile("-?\\d+\\.?\\d*|[-+*/()]");

        // Matcher to find matches in the expression
        Matcher matcher = pattern.matcher(expression);

        // Find and process each match
        while (matcher.find()) {
            splitList.add(matcher.group());
        }

        // Split negative numbers inside parentheses
        splitNegativeNumbersInsideParentheses(splitList);

        // Remove brackets
        splitList.removeIf(token -> token.equals("(") || token.equals(")"));

        return splitList;
    }

    private  void splitNegativeNumbersInsideParentheses(List<String> splitList) {
        for (int i = 0; i < splitList.size(); i++) {
            String token = splitList.get(i);

            if (!token.equals("(") && i < splitList.size() - 1 && splitList.get(i + 1).startsWith("-")) {
                String negativeNumber = splitList.get(i + 1);
                splitList.set(i + 1, negativeNumber.substring(1)); // Remove the leading '-' sign
                splitList.add(i + 1, "-"); // Add the separate negative sign
            }
        }
    }
}
