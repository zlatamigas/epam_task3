package com.zlatamigas.compositechain.util.impl;

import com.zlatamigas.compositechain.util.ArithmeticOperationType;
import com.zlatamigas.compositechain.util.CountArithmeticExpression;

import java.util.ArrayList;
import java.util.Stack;

public class CountArithmeticExpressionImpl implements CountArithmeticExpression {

    private static final String DIGITS_REGEX = "\\d+";

    @Override
    public double count(String arithmeticExpressionStr) {

        ArrayList<String> polNotation = convertToPolNotation(arithmeticExpressionStr);

        return countPolNotation(polNotation);
    }

    private ArrayList<String> convertToPolNotation(String arithmeticExpressionStr) {
        ArrayList<String> polNotation = new ArrayList<>();
        Stack<ArithmeticOperationType> operationsStack = new Stack<>();

        StringBuffer currentNumber = new StringBuffer();
        boolean isNumber = false;

        String stackOperation;
        ArithmeticOperationType type;
        int priority;

        char charAtIndex;
        for (int i = 0; i < arithmeticExpressionStr.length(); i++) {
            charAtIndex = arithmeticExpressionStr.charAt(i);

            if (Character.isDigit(charAtIndex)) {
                int digit = Integer.parseInt(Character.toString(charAtIndex));
                currentNumber.append(digit);
                isNumber = true;
                continue;
            }

            if (isNumber) {
                polNotation.add(currentNumber.toString());
                currentNumber.delete(0, currentNumber.length());
                isNumber = false;
            }

            type = ArithmeticOperationType.getOperationType(charAtIndex);
            priority = type.getPriority();

            if (type == ArithmeticOperationType.SUBTRACT) {

                boolean isUnaryMinus = false;

                if (i > 0) {
                    char charBefore = arithmeticExpressionStr.charAt(i - 1);

                    isUnaryMinus = !(Character.isDigit(charBefore)
                            || charBefore != ArithmeticOperationType.BRACKET_OPEN.getOperation());

                } else if (i == 0) {
                    isUnaryMinus = true;
                }

                if (isUnaryMinus) {
                    type = ArithmeticOperationType.UNARY_MINUS;
                    operationsStack.push(type);
                    continue;
                }

            }

            if (priority == 1) {
                operationsStack.push(type);
            } else if (priority > 1) {
                while (!operationsStack.empty()) {
                    if (operationsStack.peek().getPriority() >= priority) {
                        stackOperation = Character.toString(operationsStack.pop().getOperation());
                        polNotation.add(stackOperation);
                    } else {
                        break;
                    }
                }
                operationsStack.push(type);
            } else if (priority == -1) {
                while (operationsStack.peek() != ArithmeticOperationType.BRACKET_OPEN) {
                    stackOperation = Character.toString(operationsStack.pop().getOperation());
                    polNotation.add(stackOperation);
                }
                operationsStack.pop();
            }

        }

        if (isNumber) {
            polNotation.add(currentNumber.toString());
        }

        while (!operationsStack.empty()) {
            stackOperation = Character.toString(operationsStack.pop().getOperation());
            polNotation.add(stackOperation);
        }

        return polNotation;
    }

    private double countPolNotation(ArrayList<String> polNotation) {
        Stack<Double> valuesStack = new Stack<>();

        ArithmeticOperationType type;
        String currentValue;
        double value1, value2, valueResult = 0;

        for (int i = 0; i < polNotation.size(); i++) {
            currentValue = polNotation.get(i);

            if (currentValue.matches(DIGITS_REGEX)) {
                valuesStack.push(Double.parseDouble(currentValue));
            } else {

                type = ArithmeticOperationType.getOperationType(currentValue.charAt(0));

                if (type == ArithmeticOperationType.UNARY_MINUS) {
                    valueResult = valuesStack.pop();
                    valuesStack.push(-valueResult);
                } else {
                    value1 = valuesStack.pop();
                    value2 = valuesStack.pop();
                    switch (type) {
                        case ADD:
                            valueResult = value2 + value1;
                            break;
                        case SUBTRACT:
                            valueResult = value2 - value1;
                            break;
                        case MULTIPLY:
                            valueResult = value2 * value1;
                            break;
                        case DIVIDE:
                            valueResult = value2 / value1;
                            break;
                    }
                    valuesStack.push(valueResult);
                }
            }
        }


        return valuesStack.pop();
    }
}
