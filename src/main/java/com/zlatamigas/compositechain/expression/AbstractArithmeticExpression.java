package com.zlatamigas.compositechain.expression;

import java.util.Stack;

public abstract class AbstractArithmeticExpression {
    public abstract void interpret(Stack<Double> valuesStack);
}

