package com.zlatamigas.compositechain.util;

import com.zlatamigas.compositechain.util.interpreter.*;

import java.util.ArrayList;
import java.util.Stack;

public class CalculateArithmeticExpression {

    private static final String DIGITS_REGEX = "\\d+";
    private static final String VALUES_DELIMITER = " ";

    private ArrayList<AbstractArithmeticExpression> listExpressions;

    public CalculateArithmeticExpression(String arithmeticExpressionStr) {
        listExpressions = new ArrayList<>();
        String polNotation = convertToPolishNotation(arithmeticExpressionStr);
        parsePolishNotation(polNotation);
    }

    public double calculate() {

        Stack<Double> valuesStack = new Stack<>();
        for (AbstractArithmeticExpression expression : listExpressions) {
            expression.interpret(valuesStack);
        }

        return valuesStack.pop();
    }

    private String convertToPolishNotation(String arithmeticExpressionStr) {

        StringBuilder polNotation = new StringBuilder("");
        Stack<ArithmeticOperationType> operationsStack = new Stack<>();

        StringBuilder currentNumber = new StringBuilder();
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
                polNotation.append(currentNumber).append(VALUES_DELIMITER);
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
                        polNotation.append(stackOperation).append(VALUES_DELIMITER);
                    } else {
                        break;
                    }
                }
                operationsStack.push(type);
            } else if (priority == -1) {
                while (operationsStack.peek() != ArithmeticOperationType.BRACKET_OPEN) {
                    stackOperation = Character.toString(operationsStack.pop().getOperation());
                    polNotation.append(stackOperation).append(VALUES_DELIMITER);
                }
                operationsStack.pop();
            }
        }

        if (isNumber) {
            polNotation.append(currentNumber).append(VALUES_DELIMITER);
        }

        while (!operationsStack.empty()) {
            stackOperation = Character.toString(operationsStack.pop().getOperation());
            polNotation.append(stackOperation).append(VALUES_DELIMITER);
        }

        return polNotation.toString();
    }

    private void parsePolishNotation(String polNotation) {

        ArithmeticOperationType type;
        polNotation = polNotation.trim();

        for (String currentValue : polNotation.split(VALUES_DELIMITER)) {

            if (currentValue.matches(DIGITS_REGEX)) {
                listExpressions.add(new NonTerminalExpressionNumber(Double.parseDouble(currentValue)));
            } else {

                type = ArithmeticOperationType.getOperationType(currentValue.charAt(0));

                switch (type) {
                    case ADD:
                        listExpressions.add(new TerminalExpressionAdd());
                        break;
                    case SUBTRACT:
                        listExpressions.add(new TerminalExpressionSubstract());
                        break;
                    case MULTIPLY:
                        listExpressions.add(new TerminalExpressionMultiply());
                        break;
                    case DIVIDE:
                        listExpressions.add(new TerminalExpressionDivide());
                        break;
                    case UNARY_MINUS:
                        listExpressions.add(new TerminalExpressionUnaryMinus());
                        break;
                }
            }
        }

    }
}
