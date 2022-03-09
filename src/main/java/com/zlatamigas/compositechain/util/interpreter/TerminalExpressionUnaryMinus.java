package com.zlatamigas.compositechain.util.interpreter;

import java.util.Stack;

public class TerminalExpressionUnaryMinus extends AbstractArithmeticExpression {
    @Override
    public void interpret(Stack<Double> valuesStack) {
        double valueResult = -valuesStack.pop();

        valuesStack.push(valueResult);
    }
}