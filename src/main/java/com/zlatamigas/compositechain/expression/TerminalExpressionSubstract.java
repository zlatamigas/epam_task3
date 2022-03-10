package com.zlatamigas.compositechain.expression;

import java.util.Stack;

public class TerminalExpressionSubstract extends AbstractArithmeticExpression {
    @Override
    public void interpret(Stack<Double> valuesStack) {
        double value1 = valuesStack.pop();
        double value2 = valuesStack.pop();
        double valueResult = value2 - value1;

        valuesStack.push(valueResult);
    }
}