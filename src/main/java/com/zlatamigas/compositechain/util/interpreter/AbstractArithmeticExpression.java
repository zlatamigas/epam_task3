package com.zlatamigas.compositechain.util.interpreter;

import java.util.Stack;

public abstract class AbstractArithmeticExpression {
    public abstract void interpret(Stack<Double> valuesStack);
}

