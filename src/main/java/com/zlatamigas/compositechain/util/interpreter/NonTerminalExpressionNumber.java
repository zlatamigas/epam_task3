package com.zlatamigas.compositechain.util.interpreter;

import java.util.Stack;

public class NonTerminalExpressionNumber extends AbstractArithmeticExpression {

    private double number;

    public NonTerminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Stack<Double> valuesStack) {
        valuesStack.push(number);
    }
}
